package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.Constants;
import ua.edu.vntu.gui.chessboard.figurs.*;
import ua.edu.vntu.moving.Position;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class Cells extends JPanel implements Constants {

    private Cell[][] cells = new Cell[8][8];

    public Map<Figures, Figure> getWhiteFigures() {
        return whiteFigures;
    }

    public Map<Figures, Figure> getBlackFigures() {
        return blackFigures;
    }

    private Map<Figures, Figure> blackFigures = new HashMap<Figures, Figure>(),
                                whiteFigures = new HashMap<Figures, Figure>();

//    private boolean pressed = false;

    public Cells(){
        super();
        setLayout(null);

        int start = 0;

        int x = start, y = start, c = 0;
        byte cellNumber = 8;
        char cellLetter = 'a';

        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                cells[i][j] = new Cell(new Position(cellLetter,cellNumber));
                if (c % 2 == 0){
                    cells[i][j].setBackground(LIGHT);
                }
                else {
                    cells[i][j].setBackground(DARK);
                }
                cells[i][j].setBounds(x,y,CELL_SIZE,CELL_SIZE);
                add(cells[i][j]);
                x +=CELL_SIZE;
                c++;
                cellLetter++;
            }
            y+=CELL_SIZE;
            x = start;
            if (i%2 == 1 ){
                c = 0;
            }else {
                c = 1;

            }
            cellNumber--;
            cellLetter = 'a';

        }

        initFigures();
        setBounds(30,30,CELL_SIZE*8,CELL_SIZE*8);

    }

    private void initFigures(){
        Figure f;
        /**
         * black figures
         */
        f = new Rook(this, false);
        cells[0][0].addFigure(f);
        blackFigures.put(Figures.ROOK,f);

        f = new Rook(this, false);
        cells[0][7].addFigure(f);
        blackFigures.put(Figures.ROOK,f);

        f = new Knight(this,false);
        cells[0][1].addFigure(f);
        blackFigures.put(Figures.KNIGHT, f);

        f = new Knight(this,false);
        cells[0][6].addFigure(f);
        blackFigures.put(Figures.KNIGHT,f);

        f = new Bishop(this,false);
        cells[0][2].addFigure(f);
        blackFigures.put(Figures.BISHOP, f);

        f = new Bishop(this,false);
        cells[0][5].addFigure(f);
        blackFigures.put(Figures.BISHOP,f);

        f = new Queen(this,false);
        cells[0][3].addFigure(f);
        blackFigures.put(Figures.QUEEN,f);

        f = new King(this,false);
        cells[0][4].addFigure(f);
        blackFigures.put(Figures.KING,f);

        /**
         * white figures
         */

        f = new Rook(this, true);
        cells[7][0].addFigure(f);
        whiteFigures.put(Figures.ROOK,f);

        f = new Rook(this, true);
        cells[7][7].addFigure(f);
        whiteFigures.put(Figures.BISHOP,f);

        f = new Knight(this,true);
        cells[7][1].addFigure(f);
        whiteFigures.put(Figures.KNIGHT,f);

        f = new Knight(this,true);
        cells[7][6].addFigure(f);
        whiteFigures.put(Figures.KNIGHT,f);

        f = new Bishop(this,true);
        cells[7][2].addFigure(f);
        whiteFigures.put(Figures.BISHOP,f);

        f = new Bishop(this,true);
        cells[7][5].addFigure(f);
        whiteFigures.put(Figures.BISHOP,f);

        f = new Queen(this,true);
        cells[7][3].addFigure(f);
        whiteFigures.put(Figures.QUEEN,f);

        f = new King(this,true);
        cells[7][4].addFigure(f);
        whiteFigures.put(Figures.KING,f);


        for(int i = 0; i < 8; i++){
            f = new Pawn(this,true);
            cells[6][i].addFigure(f);
            whiteFigures.put(Figures.PAWN,f);

            f = new Pawn(this, false);
            cells[1][i].addFigure(f);
            blackFigures.put(Figures.PAWN,f);
        }

    }


    public Cell[][] getCells(){
        return cells;
    }

    public Cell getCellByPosition(Position p){
        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                if(cells[i][j].getPosition().equals(p)){
                    return cells[i][j];
                }
            }

        }
        return null;
    }
    public Figure getFigureByName(Figures name, boolean isWhite){
        Figure figure = null;
        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                Figure f = cells[i][j].getFigure();
                System.out.println(f.isWhite() == isWhite);
                if (f.isWhite() != isWhite)
                    continue;
                if(f.getFigureName() == name ){
                    figure = f;
                    return figure;
                }
            }
        }

        return null;
    }

}
