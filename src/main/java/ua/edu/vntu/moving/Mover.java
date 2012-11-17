package ua.edu.vntu.moving;

import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.chessboard.Cell;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.readparty.Parser;

import java.util.ArrayList;
import java.util.Map;

public class Mover implements Runnable {

    private Cells cells;

    private ArrayList<MovingDescription> blackMoves, whiteMoves;

    private Map<FigureName,Figure> whiteFigures,blackFigures;

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
            for (int i = 0; i < (blackMoves.size() < whiteMoves.size()?blackMoves.size():whiteMoves.size() );i++){
                Thread.sleep(1000);
                System.out.println("hello");
                md = whiteMoves.get(i);
                if (md.isCastling() || md.getFigureName() == null){
                    continue;
                }

                figureName = md.getFigureName();

                figure = whiteFigures.get(figureName);
                cell = cells.getCellByPosition(md.getPosition());
                figure.getParent().reset();
                cell.addFigure(figure);
                cells.repaint();

                Thread.sleep(1000);

                /**
                 * Переміщення чорних фігур
                 */
                md = blackMoves.get(i);
                if (md.isCastling() || md.getFigureName() == null){
                    continue;
                }

                figureName = md.getFigureName();

                figure = blackFigures.get(figureName);
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
        whiteFigures = cells.getWhiteFigures();
        blackFigures = cells.getBlackFigures();
    }
}
