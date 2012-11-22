package ua.edu.vntu.moving;

import ua.edu.vntu.descriptions.Position;
import ua.edu.vntu.chessboard.Figure;

public interface MoveFigure{

    void move(Figure figure,Position toPosition);

}
