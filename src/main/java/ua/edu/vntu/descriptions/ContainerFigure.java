package ua.edu.vntu.descriptions;

import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;

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

//    public HashMap<FigureName, Figure> getBlack() {
//        return black;
//    }
//
//    public HashMap<FigureName, Figure> getWhite() {
//        return white;
//    }

    public Figure getWhiteFigureForMove(FigureName name, Position position){
        return null;
    }

    public Figure getBlackFigureForMove(FigureName name, Position position){
        return null;
    }
}
