package ua.edu.vntu.moving;

import ua.edu.vntu.gui.chessboard.Cells;
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
        try{
            for (int i = 0; i < (black.size() + white.size());i++){
                Thread.sleep(1000);
                System.out.println("hello");

            }

        }   catch (InterruptedException e){
            e.printStackTrace();
        }


    }

    public void setCells(Cells cells) {
        this.cells = cells;
    }
}
