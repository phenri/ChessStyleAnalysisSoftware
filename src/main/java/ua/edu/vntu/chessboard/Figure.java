package ua.edu.vntu.chessboard;

import ua.edu.vntu.descriptions.Position;
import ua.edu.vntu.gui.Constants;

import javax.swing.*;
import java.awt.*;


public abstract class Figure extends JPanel implements Constants, Cloneable {
    protected Cell parent;
    protected Image image;
    protected boolean isWhite;
    protected FigureName name;
    protected Position position;

    protected Figure() {
        super(true);
        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, CELL_SIZE, CELL_SIZE);
    }

    public abstract boolean isAvailablePosition(Position pos);

    public boolean isAvailablePosition(Position pos, boolean isBeat) {
        return true;
    }

    public void setParent(Cell cell) {
        position = cell.getPosition();
        parent = cell;
    }

    public Cell getParentCell() {
        return parent;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public FigureName getFigureName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Figure)) {
            return false;
        }

        Figure f = (Figure) obj;

        return (f.isWhite() == this.isWhite) && f.getPosition().equals(this.getPosition());
    }

    @Override
    public String toString() {
        String color = isWhite ? "White" : "Black";
        return color + " " + name + ":" + getPosition();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
