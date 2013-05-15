package ua.edu.vntu.chessboard.virtual;

import org.springframework.stereotype.Repository;
import ua.edu.vntu.chessboard.Cell;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.Figures;
import ua.edu.vntu.chessboard.figurs.*;
import ua.edu.vntu.descriptions.Position;

import java.util.ArrayList;
import java.util.List;


@Repository("virtualCells")
public class VirtualCells implements Cells {

    private Cell cells[][] = new VirtualCell[8][8];

    private Figures figures;

    public VirtualCells() {
        initCells();
        initFigures();

    }

    private void initCells() {
        byte cellNumber = 8;
        char cellLetter = 'a';

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new VirtualCell(new Position(cellLetter, cellNumber));
                cellLetter++;
            }
            cellNumber--;
            cellLetter = 'a';
        }
    }

    @Override
    public Figures getFigures() {
        return figures;
    }

    @Override
    public void restart() {
        for (Cell[] c : cells)
            for (Cell c1 : c) {
                c1.reset();
            }
        initFigures();
    }

    @Override
    public Figures initFigures() {
        Figure f;
        List<Figure> blackFigures = new ArrayList<>(),
                whiteFigures = new ArrayList<>();
        /**
         * black figures
         */
        f = new Rook(false);
        cells[0][0].addFigure(f);
        blackFigures.add(f);

        f = new Rook(false);
        cells[0][7].addFigure(f);
        blackFigures.add(f);

        f = new Knight(false);
        cells[0][1].addFigure(f);
        blackFigures.add(f);

        f = new Knight(false);
        cells[0][6].addFigure(f);
        blackFigures.add(f);

        f = new Bishop(false);
        cells[0][2].addFigure(f);
        blackFigures.add(f);

        f = new Bishop(false);
        cells[0][5].addFigure(f);
        blackFigures.add(f);

        f = new Queen(false);
        cells[0][3].addFigure(f);
        blackFigures.add(f);

        f = new King(false);
        cells[0][4].addFigure(f);
        blackFigures.add(f);

        /**
         * white figures
         */

        f = new Rook(true);
        cells[7][0].addFigure(f);
        whiteFigures.add(f);

        f = new Rook(true);
        cells[7][7].addFigure(f);
        whiteFigures.add(f);

        f = new Knight(true);
        cells[7][1].addFigure(f);
        whiteFigures.add(f);

        f = new Knight(true);
        cells[7][6].addFigure(f);
        whiteFigures.add(f);

        f = new Bishop(true);
        cells[7][2].addFigure(f);
        whiteFigures.add(f);

        f = new Bishop(true);
        cells[7][5].addFigure(f);
        whiteFigures.add(f);

        f = new Queen(true);
        cells[7][3].addFigure(f);
        whiteFigures.add(f);

        f = new King(true);
        cells[7][4].addFigure(f);
        whiteFigures.add(f);


        for (int i = 0; i < 8; i++) {
            f = new Pawn(true);
            cells[6][i].addFigure(f);
            whiteFigures.add(f);

            f = new Pawn(false);
            cells[1][i].addFigure(f);
            blackFigures.add(f);
        }
        figures = new Figures(blackFigures, whiteFigures);
        return figures;
    }

    @Override
    public Cell getCellByPosition(Position p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (cells[i][j].getPosition().equals(p)) {
                    return cells[i][j];
                }
            }

        }
        return null;
    }

    public void paintFigure(Figure figure, Position position) {
        if (figure.getParentCell() != null)
            figure.getParentCell().reset();
        getCellByPosition(position).addFigure(figure);
    }


    class VirtualCell implements Cell {

        private Position position;

        private boolean empty;

        private Figure figure;

        VirtualCell(Position position) {
            this.position = position;
        }

        public void reset() {
            empty = true;
            if (figure != null)
                figure = null;
        }

        public void addFigure(Figure figure) {
            if (!empty)
                this.figure = null;
            figure.setParent(this);
            this.figure = figure;
            empty = false;
        }

        public Position getPosition() {
            return position;
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public boolean isEmpty() {
            return empty;
        }

        public Figure getFigure() {
            return figure;
        }

    }

}
