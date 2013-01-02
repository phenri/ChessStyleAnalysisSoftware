package ua.edu.vntu.descriptions;

import ua.edu.vntu.chessboard.Cell;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.chessboard.figurs.Rook;

import java.util.ArrayList;

public class ContainerFigure{
    private ArrayList<Figure> white,black;

    private Cells board;

    public ContainerFigure() {
    }

    public void setWhite(ArrayList<Figure> white) {
        this.white = white;
    }

    public void setBlack(ArrayList<Figure> black) {
        this.black = black;
    }

    public Figure getWhiteFigureForMove(MovingDescription description){
        return getFigureForMove(white,description);
    }

    public Figure getBlackFigureForMove(MovingDescription description){
        return getFigureForMove(black,description);
    }

    public void removeWhiteFigure(Position pos){
        removeFigure(pos,white);
    }

    public void removeBlackFigure(Position pos){
        removeFigure(pos,black);
    }

    private void removeFigure(Position pos, ArrayList<Figure> fromList){
        Cell c = board.getCellByPosition(pos);
        Figure f = c.getFigure();
        c.reset();
        System.err.println("\tRemoved " + f);
        fromList.remove(f);
    }

    private Figure getFigureForMove(ArrayList<Figure> figures, MovingDescription description){

        FigureName name = description.getFigureName();
        for (Figure f: figures){

            if (f.getFigureName() != name)
                continue;

            if (name == FigureName.PAWN){
                boolean state1 = description.isBeat();
                boolean state2 = description.getPosition().getX() == f.getPosition().getX();
                if(!state1 && state2 && f.isAvailablePosition(description.getPosition())) {
                    return f;
                }
                else {
                    if (f.getPosition().getX() == description.getFromVertical() && f.isAvailablePosition(description.getPosition(),description.isBeat())) {
                        return f;
                    }
                }
            }

            if (name == FigureName.ROOK && f.getFigureName() == FigureName.ROOK){
                boolean nullVertical = description.getFromVertical() == '0';
                boolean nullHorizontal = description.getFromHorizontal() == 0;

                if (f.isAvailablePosition(description.getPosition()) && !(nullHorizontal && nullVertical)){
                    if (nullVertical && f.getPosition().getY() == description.getFromHorizontal())
                        return f;
                    else
                        if (nullHorizontal && f.getPosition().getX() == description.getFromVertical()){
                            return f;
                        }
                }
                else
                    if(f.isAvailablePosition(description.getPosition()) && isEmptyPath((Rook)f,description.getPosition())){
                        return f;
                    }
            }

            if (name == FigureName.QUEEN && f.getFigureName() == FigureName.QUEEN)
                return f;

            if (name == FigureName.KING && f.getFigureName() == FigureName.KING)
                return f;

            if (name == FigureName.BISHOP && f.getFigureName() == FigureName.BISHOP && f.isAvailablePosition(description.getPosition()))
                return f;

            if (name == FigureName.KNIGHT && f.getFigureName() == FigureName.KNIGHT){
                if (f.isAvailablePosition(description.getPosition()) && description.getFromHorizontal() == 0 && description.getFromVertical() == '0'){
                    return f;
                }
                boolean b1 = description.getFromHorizontal() != 0;
                boolean b2 = f.getPosition().getY() == description.getFromHorizontal();

                if (b1 && b2){
                    return f;
                }
                else{
                    b1 = description.getFromVertical() != 0;
                    b2 = f.getPosition().getX() == description.getFromVertical();

                    if (b1 && b2){
                        return f;
                    }
                }

            }

        }
        return null;
    }

    private boolean isEmptyPath(Rook rook, Position to){
        if (!rook.isAvailablePosition(to)){
            return false;
        }

        if(rook.getPosition().getX() == to.getX()){
            int begin = rook.getPosition().getY() < to.getY() ? rook.getPosition().getY() : to.getY();
            int end = rook.getPosition().getY() > to.getY() ? rook.getPosition().getY() : to.getY();
            if (rook.getPosition().getY() > to.getY()){
                end--;
            }
            else{
                begin++;
            }
            for(int i = begin; i <= end; i++){
                Position p = new Position(to.getX(),i);
                Cell c = board.getCellByPosition(p);
                if (!c.isEmpty()){
                    if (c == board.getCellByPosition(to)){
                        if(rook.isWhite() == c.getFigure().isWhite()){
                            return false;
                        }
                        else continue;

                    }
                    return false;
                }
            }

        }
        else {
            if(rook.getPosition().getY() == to.getY()){
                char begin = rook.getPosition().getX() < to.getX() ? rook.getPosition().getX() : to.getX();
                char end = rook.getPosition().getX() > to.getX() ? rook.getPosition().getX() : to.getX();
                if (rook.getPosition().getX() > to.getX()){
                    end--;
                }
                else{
                    begin++;
                }

                for(char i = begin; i <= end; i++){
                    Position p = new Position(i,to.getY());
                    Cell c = board.getCellByPosition(p);
                    if (!c.isEmpty()){
                        if (c == board.getCellByPosition(to)){
                            if(rook.isWhite() == c.getFigure().isWhite()){
                                return false;
                            }
                            else continue;

                        }
                        return false;
                    }
                }

            }
        }

        return true;
    }

    public void setCells(Cells cells) {
        this.board = cells;
    }
}
