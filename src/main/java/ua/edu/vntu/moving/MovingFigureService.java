package ua.edu.vntu.moving;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.edu.vntu.chessboard.Cell;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.descriptions.*;

@Repository ("movingInterface")
public class MovingFigureService implements MoveFigure{

    @Autowired
    private Cells cells;

    public MovingFigureService(){

    }

    @Override
    public boolean move(Figure figure, MovingDescription description) {

        Position pos = description.getPosition();
        Cell cell = cells.getCellByPosition(pos);

        figure.getParent().reset();
        cell.addFigure(figure);
        return true;

    }

    @Override
    public void doCastling(Castling castling, boolean isWhite){
        Position pos[] = new Position[4];


        if (isWhite && castling.isLong()){
            pos[0] = new Position('e',1);
            pos[1] = new Position('a',1);
            pos[2] = new Position('c',1);
            pos[3] = new Position('d',1);
            replacement(pos);
            return;
        }
        if (isWhite && !castling.isLong()){
            pos[0] = new Position('e',1);
            pos[1] = new Position('h',1);
            pos[2] = new Position('g',1);
            pos[3] = new Position('f',1);
            replacement(pos);
            return;
        }
        if (!isWhite && castling.isLong()){
            pos[0] = new Position('e',8);
            pos[1] = new Position('a',8);
            pos[2] = new Position('c',8);
            pos[3] = new Position('d',8);
            replacement(pos);
            return;
        }
        if (!isWhite && !castling.isLong()){
            pos[0] = new Position('e',8);
            pos[1] = new Position('h',8);
            pos[2] = new Position('g',8);
            pos[3] = new Position('f',8);
            replacement(pos);
        }

    }

    private void replacement(Position pos[]){

        Figure king, rook;
        Cell cellRook,newCellRook,cellKing,newCellKing;

        cellKing = cells.getCellByPosition(pos[0]);
        cellRook = cells.getCellByPosition(pos[1]);
        newCellKing = cells.getCellByPosition(pos[2]);
        newCellRook = cells.getCellByPosition(pos[3]);

        king = cellKing.getFigure();
        rook = cellRook.getFigure();

        cellKing.reset();
        cellRook.reset();
        newCellKing.addFigure(king);
        newCellRook.addFigure(rook);

        cells.repaint();
    }
}
