package ua.edu.vntu.descriptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.edu.vntu.chessboard.Cell;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.chessboard.figurs.Rook;

import java.util.List;

@Repository("logic")
public class Logic {

    @Autowired
    private Cells cells;

    public Logic() {
    }


    public Figure getWhiteFigureForMove(MovingDescription description){
        return getFigureForMove(cells.getFigures().getWhiteFigures(),description);
    }

    public Figure getBlackFigureForMove(MovingDescription description){
        return getFigureForMove(cells.getFigures().getBlackFigures(),description);
    }

    public void removeWhiteFigure(Position pos){
        removeFigure(pos, cells.getFigures().getWhiteFigures());
    }

    public void removeBlackFigure(Position pos){
        removeFigure(pos,cells.getFigures().getBlackFigures());
    }

    private void removeFigure(Position pos, List<Figure> fromList){
        Cell c = cells.getCellByPosition(pos);
        Figure f = c.getFigure();
        c.reset();
        System.err.println("\tRemoved " + f);
        fromList.remove(f);
    }

    private Figure getFigureForMove(List<Figure> figures, MovingDescription description){

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
                Cell c = cells.getCellByPosition(p);
                if (!c.isEmpty()){
                    if (c == cells.getCellByPosition(to)){
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
                    Cell c = cells.getCellByPosition(p);
                    if (!c.isEmpty()){
                        if (c == cells.getCellByPosition(to)){
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

    @Override
    public String toString() {
        return "Logic{" +
                "cells=" + cells +
                '}';
    }
}
