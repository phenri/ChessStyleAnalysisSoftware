package ua.edu.vntu.moving;

import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.descriptions.ContainerFigure;
import ua.edu.vntu.descriptions.EndParty;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.readparty.Parser;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Mover implements Runnable {

    @Autowired
    private Cells cells;

    private MoveFigure moving;

    public static final long TIMEOUT = 500;

    private ArrayList<MovingDescription> blackMoves, whiteMoves;

    private ContainerFigure containerFigure;

    public Mover(){

    }

    public void startParty(File f) {
        cells.restart();
        Parser parser = new Parser(f);
//        blackMoves = parser.getBlackMoves();
//        whiteMoves = parser.getWhiteMoves();
        new Thread(this).start();
    }

    @Override
    public void run() {
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

                    figure = containerFigure.getWhiteFigureForMove(md);

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
                    figure = containerFigure.getBlackFigureForMove(md);

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

    public void setFigures(ContainerFigure figures) {
        this.containerFigure = figures;
    }
}
