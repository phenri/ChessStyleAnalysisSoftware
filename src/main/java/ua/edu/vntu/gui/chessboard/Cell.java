package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.Constants;
import ua.edu.vntu.moving.Position;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 04.11.12
 * Time: 13:29
 */
public class Cell extends JPanel implements Constants{
    private Position position;

    private boolean empty = true;
    private Figure figure;
    private Cells cells;

    public Cell(Cells cells, Position position){
        super(true);
        setLayout(null);
        this.cells = cells;
        this.position = position;

    }

    public void reset(){
        empty = true;
    }

    public Position getPosition(){
        return position;
    }

    public Figure getFigure(){
        return figure;
    }

    public boolean isEmpty(){
        return empty;
    }

    public void addFigure(Figure figure){
        if(!empty)
            remove(this.figure);
        add(figure);
        figure.setParent(this);
        figure.setPosition(this.position);
        this.figure = figure;
        empty = false;
        repaint();
    }


    @Override
    public String toString(){
        return "Cell: " + position.toString();
    }

}
