package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.FormConstants;

import javax.swing.*;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class Cells extends JPanel implements FormConstants {
    Cell[][] cells = new Cell[8][8];
    public Cells(Chessboard board){
        super();
        setLayout(null);

        int start = 0;

        int x = start, y = start, c = 0;
        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                cells[i][j] = new Cell(board);
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
            }
            y+=CELL_SIZE;
            x = start;
            if (i%2 == 1 ){
                c = 0;
            }else {
                c = 1;
            }
        }
        setBounds(30,30,CELL_SIZE*8,CELL_SIZE*8);

    }
    public Cell[][] getCells(){
        return cells;
    }

}
