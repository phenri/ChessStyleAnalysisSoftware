package ua.edu.vntu.descriptions;

import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ContainerFigure{
    private Map<FigureName,Figure> white,black;

    public ContainerFigure() {
    }

    public void setWhite(Map<FigureName, Figure> white) {
        this.white = white;
    }

    public void setBlack(Map<FigureName, Figure> black) {
        this.black = black;
    }

    public Figure getWhiteFigureForMove(FigureName name, MovingDescription description){
        return getFigureForMove(white,name,description);
    }

    public Figure getBlackFigureForMove(FigureName name,  MovingDescription description){
        return getFigureForMove(black,name,description);
    }

    private Figure getFigureForMove(Map<FigureName,Figure> figures,FigureName name, MovingDescription description){
        for (Figure f : figures.values()) {
            if (f.getFigureName() == name && name == FigureName.PAWN && f.isAvailablePosition(description.getPosition(), description.isBeat())) {
                System.out.println("фігура для ходу " +f);
                return f;
            }

            if (f.getFigureName() == name && f.isAvailablePosition(description.getPosition())) {
                System.out.println(" фігура для ходу " +f);
                return f;
            }
        }
        System.out.println("фігура для ходу null" );

        return null;
    }
}
