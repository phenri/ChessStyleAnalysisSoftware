package ua.edu.vntu.moving;

import ua.edu.vntu.chessboard.Cell;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.ContainerFigure;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.readparty.Parser;

import javax.swing.*;
import java.util.ArrayList;

public class Mover implements Runnable {

    private Cells cells;

    private MoveFigure moving;

    public static final long TIMEOUT = 1000;

    private ArrayList<MovingDescription> blackMoves, whiteMoves;

    private ContainerFigure containerFigure;

    public Mover(){
        Parser parser = new Parser("tmp\\file.pgn");
        blackMoves = parser.getBlackMoves();
        whiteMoves = parser.getWhiteMoves();
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("start");
        exec();
    }

    public void exec(){
        MovingDescription md;
        FigureName figureName;
        Figure figure;
        Cell cell;
        try{
            for (int i = 0; i < whiteMoves.size();i++){
                Thread.sleep(TIMEOUT);
                System.out.println("\nПочаток ходу " + (i+1));

                System.out.println("Хід білих");
                md = whiteMoves.get(i);

                if (md.isEndParty()){
                    String res;
                    if (md.getEndParty().isWhiteWin())
                        res = "Виграли білі";
                    else
                        res = "Виграли  чорні";
                    JOptionPane.showMessageDialog(null,res,"Кінець партії",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                if(md.isCastling()){
                    moving.doCastling(md.getCastling(),true);
                }
                else {

                    System.out.println(md);

                    figure = containerFigure.getWhiteFigureForMove(md);

                    if (md.isBeat()){
                        containerFigure.removeBlackFigure(md.getPosition());
                    }
                    System.out.println("\tБіла фігура для ходу: "+ figure);

                    if(figure != null)
                        moving.move(figure,md);

                    System.out.println("\tФігура після ходу:" + figure);

                    System.out.println("Кінець ходу білих " + (i+1));

                    cells.repaint();
                }


                Thread.sleep(TIMEOUT);


                /**
                 * Переміщення чорних фігур
                 */
                System.out.println("\nХід чорних");
                md = blackMoves.get(i);

                if (md.isEndParty()){
                    String res;
                    if (md.getEndParty().isWhiteWin())
                        res = "Виграли білі";
                    else
                        res = "Виграли  чорні";
                    JOptionPane.showMessageDialog(null,res,"Кінець партії",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                if(md.isCastling()){
                    moving.doCastling(md.getCastling(),true);
                }
                else {
                    System.out.println(md);
                    figure = containerFigure.getBlackFigureForMove(md);

                    if (md.isBeat()){
                        containerFigure.removeWhiteFigure(md.getPosition());
                    }

                    System.out.println("\tЧорна фігура для ходу " +figure);
                    if(figure != null)
                        moving.move(figure,md);
                    System.out.println("\tФігура після ходу:" + figure);
                }

                cells.repaint();
                System.out.println("Кінець ходу чорних\nКінець ходу.");
//
            }

        }   catch (InterruptedException e){
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
