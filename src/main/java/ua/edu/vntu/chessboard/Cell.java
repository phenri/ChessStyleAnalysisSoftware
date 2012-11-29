package ua.edu.vntu.chessboard;

import ua.edu.vntu.gui.Constants;
import ua.edu.vntu.descriptions.Position;

import javax.swing.*;

public class Cell extends JPanel implements Constants{
    private Position position;

    private boolean empty = true;
    private Figure figure;

    public Cell(Position position){
        super(true);
        setLayout(null);
        this.position = position;

    }

    public void reset(){
        empty = true;
        if (figure != null)
            this.remove(figure);
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
        this.figure = figure;
        empty = false;
        repaint();
    }


    @Override
    public String toString(){
        return "Cell: " + position.toString();
    }

}
