package ua.edu.vntu.moving;

import ua.edu.vntu.chessboard.Cell;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.ContainerFigure;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.readparty.Parser;

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

                if(md.isCastling()){
                    moving.doCastling(md.getCastling(),true);
                    continue;
                }

                System.out.println(md);

                figure = containerFigure.getWhiteFigureForMove(md);

                System.out.println("Біла фігура для ходу: "+ figure);
                if(figure != null)
                    System.out.println("Хід: "+moving.move(figure,md));
                else {
                    System.err.println("\tФігура null ");
//                    Thread.sleep(1000*60);
                }

                System.out.println("Кінець ходу білих " + (i+1));

                cells.repaint();

                Thread.sleep(TIMEOUT);


                /**
                 * Переміщення чорних фігур
                 */
                System.out.println("\nХід чорних");

                md = blackMoves.get(i);
                System.out.println(md);
                figure = containerFigure.getBlackFigureForMove(md);

                System.out.println("Чорна фігура для ходу " +figure);
                if(figure != null)
                    System.out.println("Хід: "+moving.move(figure,md));
                else {
                    System.err.println("\tФігура null ");
//                    Thread.sleep(1000*60);
                }

                cells.repaint();
                System.out.println("Кінець ходу чорних\nКінець ходу.");
//
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
