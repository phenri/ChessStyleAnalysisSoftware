package ua.edu.vntu.containers;

import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.Position;


/**
 * Клас що містись положення і назву однієї фігури
 */
public class FigurePosition {

    private final FigureName name;

    private final Position position;

    public FigurePosition(FigureName name, Position position) {
        this.position = position;
        this.name = name;
    }

    public FigureName getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return name + position.toString();
    }
}
