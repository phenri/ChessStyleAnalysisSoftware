package ua.edu.vntu.moving;

import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.ContainerFigure;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.chessboard.Cell;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.readparty.Parser;

import java.util.ArrayList;
import java.util.Map;

public class Mover implements Runnable {

    private Cells cells;

    private MoveFigure moving;

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
            for (int i = 0; i < (blackMoves.size() < whiteMoves.size()?blackMoves.size():whiteMoves.size() );i++){
                Thread.sleep(1000);
                md = whiteMoves.get(i);
                System.out.println(md);
                figure = containerFigure.getWhiteFigureForMove(md.getFigureName(),md);
                if(figure != null)
                    moving.move(figure,md);

                cells.repaint();

                Thread.sleep(1000);

                /**
                 * Переміщення чорних фігур
                 */

                md = blackMoves.get(i);
                System.out.println(md);
                figure = containerFigure.getBlackFigureForMove(md.getFigureName(), md);
                if(figure != null)
                    moving.move(figure,md);
                cells.repaint();


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
