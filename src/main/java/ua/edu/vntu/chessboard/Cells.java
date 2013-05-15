package ua.edu.vntu.chessboard;

import ua.edu.vntu.descriptions.Position;

public interface Cells {

    public Figures getFigures();

    public void restart();

    public Figures initFigures();

    public Cell getCellByPosition(Position position);

    public void paintFigure(Figure figure, Position position);

}
