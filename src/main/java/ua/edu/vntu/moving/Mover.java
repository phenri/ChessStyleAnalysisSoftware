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
        Party party = ContainerPartiesService.getInstance().getPartyById(partyId);
        whiteMoves = party.getWhiteMoves();
        blackMoves = party.getBlackMoves();
        exec();
    }

    public void exec(){
        MovingDescription md;
        Figure figure;

        int len = whiteMoves.size() > blackMoves.size() ? whiteMoves.size() : blackMoves.size();

        try{
            for (int i = 0; i < len ;i++){
                Thread.sleep(TIMEOUT);
//                System.out.println("\nПочаток ходу " + (i+1));

//                System.out.println("Хід білих");
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

//                    System.out.println(md);

                    figure = logic.getWhiteFigureForMove(md);

//                    System.out.println("\tБіла фігура для ходу: "+ figure);

                    if(figure != null)
                        moving.move(figure,md);
                    else
                        throw new NullPointerException("figure cannot be null");

//                    System.out.println("\tФігура після ходу:" + figure);

//                    System.out.println("Кінець ходу білих " + (i+1));

                    cells.repaint();
                }


                Thread.sleep(TIMEOUT);


                /**
                 * Переміщення чорних фігур
                 */
//                System.out.println("\nХід чорних" + (i+1));
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
//                    System.out.println(md);
                    figure = logic.getBlackFigureForMove(md);

//                    System.out.println("\tЧорна фігура для ходу " +figure);
                    if(figure != null)
                        moving.move(figure,md);
                    else
                    throw new NullPointerException("figure cannot be null");
//                    System.out.println("\tФігура після ходу:" + figure);
                }

                cells.repaint();
//                System.out.println("Кінець ходу чорних\nКінець ходу "+ (i+1));
            }

        }   catch (InterruptedException | NullPointerException e){
            e.printStackTrace();
        }


    }

    public void setCells(Cells cells) {
        this.cells = cells;
    }

    public void setMovingInterface(MoveFigure movingInterface) {
        this.moving = movingInterface;
    }

    public void setFigures(Logic figures) {
        this.logic = figures;
    }
}
