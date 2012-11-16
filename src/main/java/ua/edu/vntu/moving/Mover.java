package ua.edu.vntu.moving;

import ua.edu.vntu.gui.chessboard.Cell;
import ua.edu.vntu.gui.chessboard.Cells;
import ua.edu.vntu.gui.chessboard.Figure;
import ua.edu.vntu.gui.chessboard.Figures;
import ua.edu.vntu.readparty.Parser;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 10.11.12
 * Time: 18:12
 */
public class Mover implements Runnable {

    private Cells cells;

    private ArrayList<MovingDescription> black,white;

    public Mover(){
        Parser parser = new Parser("tmp\\file.pgn");
        black = parser.getBlackMoves();
        white = parser.getWhiteMoves();
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("start");
        exec();
    }

    public void exec(){
        MovingDescription md;
        Figures figureName;
        Figure figure;
        Cell cell;
        try{
            for (int i = 0; i < (black.size() + white.size());i++){
                Thread.sleep(1000);
                System.out.println("hello");
                md = white.get(i);
                if (md.isCastling() || md.getFigureName() == null){
                    continue;
                }

                figureName = md.getFigureName();

                figure = cells.getFigureByName(figureName,true);
                cell = cells.getCellByPosition(md.getPosition());
                figure.getParent().reset();
                cell.addFigure(figure);
                cells.repaint();

                /**
                 * Переміщення чорних фігур
                 */
                md = black.get(i);
                if (md.isCastling() || md.getFigureName() == null){
                    continue;
                }

                figureName = md.getFigureName();

                figure = cells.getFigureByName(figureName,false);
                cell = cells.getCellByPosition(md.getPosition());
                figure.getParent().reset();
                cell.addFigure(figure);
                cells.repaint();


            }

        }   catch (InterruptedException e){
            e.printStackTrace();
        }


    }

    public void setCells(Cells cells) {
        this.cells = cells;
    }
}
