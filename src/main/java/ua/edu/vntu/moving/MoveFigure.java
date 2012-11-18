package ua.edu.vntu.moving;

import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.chessboard.Figure;

public interface MoveFigure{

    boolean move(Figure figure,MovingDescription toPosition);

}
