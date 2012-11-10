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
public class Cell extends JPanel implements FormConstants,MouseListener{
    private char letter;
    private byte number;

    private boolean empty = true;
    private Figure figure;
    private Cells cells;

    public Cell(Cells cells, char letter, byte number){
        super(true);
        setLayout(null);
        addMouseListener(this);
        this.cells = cells;
        this.letter = letter;
        this.number = number;

    }

    public void reset(){
        empty = true;
    }

    public String getAddress(){
        return  "" + letter+ ""+number;
    }

    public void addFigure(Figure figure){
        if(!empty) remove(this.figure);
        add(figure);
        figure.setParent(this);
        this.figure = figure;
        empty = false;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(empty){
            System.out.println(this);
            cells.putFigure(this);
        }
//
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

    @Override
    public String toString(){
        return "Cell: " + getAddress();
    }

}
