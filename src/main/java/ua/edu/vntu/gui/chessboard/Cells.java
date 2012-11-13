package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.Constants;
import ua.edu.vntu.gui.chessboard.figurs.*;
import ua.edu.vntu.gui.chessboard.moving.Mover;
import ua.edu.vntu.gui.chessboard.moving.Position;

import javax.swing.*;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class Cells extends JPanel implements Constants {

    private Cell[][] cells = new Cell[8][8];

    private Figure buffer;

    private boolean pressed = false;

    public Cells(){
        super();
        setLayout(null);

        int start = 0;

        int x = start, y = start, c = 0;
        byte cellNumber = 8;
        char cellLetter = 'a';

        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                cells[i][j] = new Cell(this,new Position(cellLetter,cellNumber));
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

        new Mover(this);
        initFigures();
        setBounds(30,30,CELL_SIZE*8,CELL_SIZE*8);

    }

    private void initFigures(){

        for(int i = 0; i < 8; i++){
            cells[6][i].addFigure(new Pawn(this,true));
            cells[1][i].addFigure(new Pawn(this, false));
            switch (i){
                case 0:
                    cells[0][i].addFigure(new Rook(this, false));
                    cells[7][i].addFigure(new Rook(this, true));
                    cells[0][i+7].addFigure(new Rook(this, false));
                    cells[7][i+7].addFigure(new Rook(this, true));
                    break;
                case 1:
                    cells[0][i].addFigure(new Knight(this,false));
                    cells[7][i].addFigure(new Knight(this,true));
                    cells[0][i+5].addFigure(new Knight(this,false));
                    cells[7][i+5].addFigure(new Knight(this,true));
                    break;
                case 2:
                    cells[0][i].addFigure(new Bishop(this,false));
                    cells[7][i].addFigure(new Bishop(this,true));
                    cells[0][i+3].addFigure(new Bishop(this,false));
                    cells[7][i+3].addFigure(new Bishop(this,true));
                    break;
                case 3:
                    cells[0][i].addFigure(new Queen(this,false));
                    cells[0][i+1].addFigure(new King(this,false));
                    cells[7][i].addFigure(new Queen(this,true));
                    cells[7][i+1].addFigure(new King(this,true));
                    break;
            }
        }

    }

    public void moveMy(Figure figure){
        if (!pressed){
            pressed = true;
            buffer = figure;
        }
    }


    public boolean isPressed(){
        return pressed;
    }

    public void resetPressed(){
        pressed = false;
    }

    public Figure getBuffer(){
        return buffer;
    }

    public void putFigure(Cell cell){
        if(buffer != null)
//            System.out.println(buffer.isAvailablePosition(cell.getPosition()));
        if(pressed){
            cell.addFigure(buffer);
        }
        pressed = false;
        repaint();
    }

    public Cell[][] getCells(){
        return cells;
    }

    public Cell getCellByPosition(Position p){
        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                if(cells[i][j].getPosition().equals(p)){
//                    System.out.println("get cell by position"+cells[i][j]);
                    return cells[i][j];
                }
            }

        }
        return null;
    }

}
