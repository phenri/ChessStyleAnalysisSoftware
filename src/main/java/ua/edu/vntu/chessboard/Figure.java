package ua.edu.vntu.chessboard;

import ua.edu.vntu.gui.Constants;
import ua.edu.vntu.descriptions.Position;

import javax.swing.*;
import java.awt.*;


public abstract class Figure extends JPanel implements Constants{
    protected Cell parent;
    protected Image image;
    protected boolean isWhite;
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

    public void setParent(Cell cell){
        parent = cell;
    }

    public Cell getParentCell(){
        return parent;
    }

    public boolean isWhite(){
        return isWhite;
    }

    public Position getPosition(){
        return parent.getPosition();
    }

    public FigureName getFigureName(){
        return name;
    }

    @Override
    public String toString(){
        String color = isWhite ? "White": "Black";
        return color +" "+ name +":" + getPosition();
    }

}
