package ua.edu.vntu.moving;

import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.descriptions.*;

import javax.swing.*;
import java.util.List;

public class Mover implements Runnable {

    @Autowired
    private Cells cells;

    private MoveFigure moving;

    public static final long TIMEOUT = 500;

    private List<MovingDescription> blackMoves, whiteMoves;

    private Logic logic;

    private int partyId;

    public Mover(){

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

    public void exec(){
        Party party = ContainerPartiesService.getInstance().getPartyById(partyId);
        whiteMoves = party.getWhiteMoves();
        blackMoves = party.getBlackMoves();

        MovingDescription md;
        Figure figure;
        int len = whiteMoves.size() > blackMoves.size() ? whiteMoves.size() : blackMoves.size();

        try{
            for (int i = 0; i < len ;i++){
//                Thread.sleep(TIMEOUT);

                md = whiteMoves.get(i);

                if (md.isEndParty()){
                    String res = "";
                    if (md.getEndParty() == EndParty.WHITE_WIN)
                        res = "Виграли білі";
                    else
                    if (md.getEndParty() == EndParty.BLACK_WIN)
                        res = "Виграли  чорні";
                    else
                    if (md.getEndParty() == EndParty.NOBODY)
                        res = "Нічия";

                    JOptionPane.showMessageDialog(null,res,"Кінець партії",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                if(md.isCastling()){
                    moving.doCastling(md.getCastling(),true);
                }
                else {

                    figure = logic.getWhiteFigureForMove(party.getFigures().getWhiteFigures(), md);

                    if(figure != null)
                        moving.move(figure,md);
                    else
                        throw new NullPointerException("figure cannot be null");

                    cells.repaint();
                }

//                Thread.sleep(TIMEOUT);


                /**
                 * Переміщення чорних фігур
                 */
                md = blackMoves.get(i);

                if (md.isEndParty()){
                    String res = "";
                    if (md.getEndParty() == EndParty.WHITE_WIN)
                        res = "Виграли білі";
                    else
                    if (md.getEndParty() == EndParty.BLACK_WIN)
                        res = "Виграли  чорні";
                    else
                    if (md.getEndParty() == EndParty.NOBODY)
                        res = "Нічия";

                    JOptionPane.showMessageDialog(null,res,"Кінець партії",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                if(md.isCastling()){
                    moving.doCastling(md.getCastling(),false);
                }
                else {
                    figure = logic.getBlackFigureForMove(party.getFigures().getBlackFigures(), md);

                    if(figure != null)
                        moving.move(figure,md);
                    else
                    throw new NullPointerException("figure cannot be null");
                }

                cells.repaint();
            }

        }   catch (/*InterruptedException |*/ NullPointerException e){
            e.printStackTrace();
        }


    }

    public void setMovingInterface(MoveFigure movingInterface) {
        this.moving = movingInterface;
    }

    public void setFigures(Logic figures) {
        this.logic = figures;
    }
}
