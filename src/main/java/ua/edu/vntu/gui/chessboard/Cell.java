package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.FormConstants;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 04.11.12
 * Time: 13:29
 */
public class Cell extends JPanel implements FormConstants,MouseListener,Runnable{
    private char letter;
    private byte number;

    boolean empty = true;
    private Figure figure;
    Chessboard board;

    public Cell(Chessboard board){
        super();
        setLayout(null);
        addMouseListener(this);
        this.board = board;
        new Thread(this).start();

    }
    public Cell(char letter, byte number){
        super(true);
        this.letter = letter;
        this.number = number;

    }
    public boolean isEmpty(){
        return empty;
    }

    public void reset(){
        empty = true;
    }

    public boolean addFigure(Figure figure){
        if (empty){
            add(figure);
            figure.setParent(this);
            this.figure = figure;
            empty = false;
            return true;
        }
        repaint();
        return false;
    }


    @Override
    public void run() {

    }

    public Figure getFigure(){
        if(!empty){
            remove(figure);
            empty = true;
            return figure;
        }
        repaint();
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(empty){
            System.out.println("Click on cell empty " + Thread.currentThread());
            board.Mover(this);
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
