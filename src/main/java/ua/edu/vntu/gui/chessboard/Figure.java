package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.Constants;
import ua.edu.vntu.moving.Position;

import javax.swing.*;
import java.awt.*;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public abstract class Figure extends JPanel implements Constants{
    protected Cell parent;
    protected Cells board;
    protected Image image;
    protected boolean isWhite;
    protected Position position;
    protected Figures name;

    protected Figure(Cells board){
        super(true);
        setLayout(null);
        this.board = board;
        setOpaque(false);
        setBounds(0,0,CELL_SIZE,CELL_SIZE);
    }

    public abstract boolean isAvailablePosition(Position pos);

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

    protected Figures getFigureName(){
        return name;
    }

}
