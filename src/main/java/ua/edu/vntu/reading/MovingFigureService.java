package ua.edu.vntu.reading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.edu.vntu.chessboard.Cell;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.chessboard.figurs.Queen;
import ua.edu.vntu.descriptions.Castling;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.descriptions.Position;

import java.util.List;

@Repository("moveFigure")
public class MovingFigureService implements MoveFigure {

    @Autowired
    @Qualifier("virtualCells")
    private Cells cells;

    public MovingFigureService() {

    }

    @Override
    public boolean move(Figure figure, MovingDescription description) {

        if (description.isPawnToEnd()) {
            List<Figure> figures = figure.isWhite() ? cells.getFigures().getWhiteFigures() : cells.getFigures().getBlackFigures();
            figure.getParentCell().reset();
            figures.remove(figure);
            Figure f = getNewFigure(description.getNewFigureName(), figure.isWhite());
            figures.add(f);
            cells.paintFigure(f, description.getPosition());
            return true;
        }
        cells.paintFigure(figure, description.getPosition());

        return true;
    }

    @Override
    public void doCastling(Castling castling, boolean isWhite) {
        Position pos[] = new Position[4];


        if (isWhite && castling.isLong()) {
            pos[0] = new Position('e', 1);
            pos[1] = new Position('a', 1);
            pos[2] = new Position('c', 1);
            pos[3] = new Position('d', 1);
            replacement(pos);
            return;
        }
        if (isWhite && !castling.isLong()) {
            pos[0] = new Position('e', 1);
            pos[1] = new Position('h', 1);
            pos[2] = new Position('g', 1);
            pos[3] = new Position('f', 1);
            replacement(pos);
            return;
        }
        if (!isWhite && castling.isLong()) {
            pos[0] = new Position('e', 8);
            pos[1] = new Position('a', 8);
            pos[2] = new Position('c', 8);
            pos[3] = new Position('d', 8);
            replacement(pos);
            return;
        }
        if (!isWhite && !castling.isLong()) {
            pos[0] = new Position('e', 8);
            pos[1] = new Position('h', 8);
            pos[2] = new Position('g', 8);
            pos[3] = new Position('f', 8);
            replacement(pos);
        }

    }

    private void replacement(Position pos[]) {

        Figure king, rook;
        Cell cellRook, cellKing;

        cellKing = cells.getCellByPosition(pos[0]);
        cellRook = cells.getCellByPosition(pos[1]);

        king = cellKing.getFigure();
        rook = cellRook.getFigure();

        cells.paintFigure(king, pos[2]);
        cells.paintFigure(rook, pos[3]);

//        cells.repaint();

    }

    private Figure getNewFigure(FigureName name, boolean isWhite) {
        return new Queen(isWhite);
        //TODO: зробити щоб повертало фігуру за вказаним іменем
    }
}
