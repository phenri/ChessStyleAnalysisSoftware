package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.FormConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.PublicKey;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 04.11.12
 * Time: 13:29
 */
public class Cell extends JPanel implements FormConstants,MouseListener{
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


    }
    public Cell(char letter, byte number){
        super(true);
        this.letter = letter;
        this.number = number;

    }
    public boolean isEmpty(){
        return empty;
    }

    public boolean addFigure(Figure figure){
        System.out.println("Add figure");
        if (empty){
            add(figure);
            this.figure = figure;
            empty = false;
            return true;
        }
        repaint();
        return false;
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
        System.out.println("Click on cell empty " + empty);
        if(empty){
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
