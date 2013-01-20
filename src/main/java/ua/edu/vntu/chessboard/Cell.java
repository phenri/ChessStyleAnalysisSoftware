package ua.edu.vntu.chessboard;

import ua.edu.vntu.descriptions.Position;

public interface Cell {

    public void reset();

    public void addFigure(Figure figure);

    public Position getPosition();

    public boolean isEmpty();

    public Figure getFigure();

}
