package ua.edu.vntu.reading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.edu.vntu.analysis.Analysible;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.containers.ContainerParsedPartiesService;
import ua.edu.vntu.containers.ReadedParty;
import ua.edu.vntu.descriptions.EndParty;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.descriptions.Party;
import ua.edu.vntu.gui.table.MyTable;
import ua.edu.vntu.gui.table.Table;

import javax.swing.*;
import java.util.List;

@Repository("mover")
public class Mover implements Runnable {

    @Autowired
    @Qualifier("virtualCells")
    private Cells cells;

    @Autowired
    private MoveFigure moveFigure;

    @Autowired
    private Analysible analysis;

    public static final long TIMEOUT = 1;

    //    @Autowired
    private volatile ReadedParty saverParty = new ReadedParty();

    @Autowired
    private Logic logic;

    private int partyId;

    public ReadedParty getSaverParty() {
        return saverParty;
    }

    public Mover() {

    }

    public void startParty(int partyId) {
        cells.restart();
        this.partyId = partyId;
        new Thread(this).start();
    }

    @Override
    public void run() {
        exec();
    }

    private void exec() {
        Party party = ContainerParsedPartiesService.getInstance().getPartyById(partyId);

        analysis.analyze(party);

        Table table = MyTable.getInstance();
        table.clear();
        table.addProgress(party);

        List<MovingDescription> blackMoves, whiteMoves;

        whiteMoves = party.getWhiteMoves();
        blackMoves = party.getBlackMoves();

        MovingDescription md;
        int len = whiteMoves.size() > blackMoves.size() ? whiteMoves.size() : blackMoves.size();

        saverParty.save(cells.getFigures());

        for (int i = 0; i < len; i++) {
            md = whiteMoves.get(i);
            if (this.isEnd(md)) {
                break;
            }
            doMove(md, true);
            md = blackMoves.get(i);
            if (this.isEnd(md))
                break;
            doMove(md, false);
        }
    }

    public void doMove(MovingDescription md, boolean isWhite) {
        Figure figure;

        if (md.isCastling()) {
            moveFigure.doCastling(md.getCastling(), isWhite);
            saverParty.save(cells.getFigures());
        } else {
            if (isWhite) {
                figure = logic.getWhiteFigureForMove(md);
                if (md.isBeat()) {
                    logic.removeBlackFigure(md.getPosition());
                }
            } else {
                figure = logic.getBlackFigureForMove(md);
                if (md.isBeat()) {
                    logic.removeWhiteFigure(md.getPosition());
                }
            }
            if (figure != null) {
                moveFigure.move(figure, md);
                saverParty.save(cells.getFigures());
            } else
                throw new NullPointerException("Figure cannot be null " + md);
        }

    }

    private boolean isEnd(MovingDescription md) {
        if (md.isEndParty()) {
            String res = "";
            if (md.getEndParty() == EndParty.WHITE_WIN)
                res = "Виграли білі";
            else if (md.getEndParty() == EndParty.BLACK_WIN)
                res = "Виграли  чорні";
            else if (md.getEndParty() == EndParty.NOBODY)
                res = "Нічия";

//            JOptionPane.showMessageDialog(null, res, "Кінець партії", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }

}
