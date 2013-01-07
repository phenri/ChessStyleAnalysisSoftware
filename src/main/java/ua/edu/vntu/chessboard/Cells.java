package ua.edu.vntu.chessboard;

import org.springframework.stereotype.Repository;
import ua.edu.vntu.chessboard.figurs.*;
import ua.edu.vntu.descriptions.Position;
import ua.edu.vntu.gui.Constants;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Repository("cells")
public class Cells extends JPanel implements Constants {

    private Cell[][] cells = new Cell[8][8];

    private Figures figures;

    public Cells(){
        super();
        setLayout(null);
        initCells();
        setBounds(30, 30, CELL_SIZE * 8, CELL_SIZE * 8);

    }

    public Figures getFigures() {
        return figures;
    }

    /**
     * Метод для ініціалізації клітинок
     */

    private void initCells(){

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

    }

    public void restart(){
        for (Cell[] c: cells)
            for (Cell c1: c){
                c1.reset();
            }
        initFigures();
    }

    /**
     * Метод для ініціалізації фігур для нової партії
     * @return поертає обєкт що містить білі і чорні фігури
     */
    public Figures initFigures(){
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


        for(int i = 0; i < 8; i++){
            f = new Pawn(true);
            cells[6][i].addFigure(f);
            whiteFigures.add(f);

            f = new Pawn(false);
            cells[1][i].addFigure(f);
            blackFigures.add(f);
        }
        figures = new Figures(blackFigures,whiteFigures);
        return figures;
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

    public void paintFigure(Figure figure,Position position){
        if(figure.getParent() != null)
            figure.getParent().reset();
        getCellByPosition(position).addFigure(figure);
        this.repaint();
    }

}
