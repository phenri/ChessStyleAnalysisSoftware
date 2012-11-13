package ua.edu.vntu.moving;

import ua.edu.vntu.gui.chessboard.Figure;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public interface MoveFigure{

    void move(Figure figure,Position toPosition);

}
