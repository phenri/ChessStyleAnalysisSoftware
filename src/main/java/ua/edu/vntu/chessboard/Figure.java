package ua.edu.vntu.chessboard;

import ua.edu.vntu.gui.Constants;
import ua.edu.vntu.descriptions.Position;

import javax.swing.*;
import java.awt.*;


public abstract class Figure extends JPanel implements Constants{
    protected Cell parent;
    protected Image image;
    protected boolean isWhite;
    protected Position position;
    protected FigureName name;

    protected Figure(){
        super(true);
        setLayout(null);
        setOpaque(false);
        setBounds(0,0,CELL_SIZE,CELL_SIZE);
    }

    public abstract boolean isAvailablePosition(Position pos);

    public boolean isAvailablePosition(Position pos,boolean isBeat){
        return true;
    }

    protected void setParent(Cell cell){
        parent = cell;
    }
    public Cell getParent(){
        return parent;
    }

    protected boolean isWhite(){
        return isWhite;
    }

    protected void setPosition(Position position){
        this.position = position;
    }

    protected Position getPosition(){
        return position;
    }

    public FigureName getFigureName(){
        return name;
    }

}
