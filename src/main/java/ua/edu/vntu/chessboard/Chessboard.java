package ua.edu.vntu.chessboard;

import ua.edu.vntu.gui.Constants;

import javax.swing.*;
import java.awt.*;


public class Chessboard extends JPanel implements Constants {

    private CellsImpl cells = new CellsImpl();

    public Chessboard(){
        super(true);
        setLayout(null);
        setBackground(new Color(255,255, 255));

        add(this.cells);
        initBoard();add(this.cells);
        initBoard();

        setBounds(30, 30, CELL_SIZE*8+60, CELL_SIZE*8+60);

    }

//    public void setCells(CellsImpl cells){
//        this.cells = cells;
//        add(this.cells);
//        initBoard();
//        repaint();
//    }


    private void initBoard(){
        int x = 30,
            y = x + 15,
            x2 = 50;

        int i = 8;
        char c = 'A';

        while (i > 0){
            JLabel leftVertical = new JLabel(i+"");
            JLabel rightVertical = new JLabel(i+"");
            leftVertical.setBounds(10, y, 20, 20);
            rightVertical.setBounds(520, y, 20, 20);
            add(leftVertical);
            add(rightVertical);
            y += CELL_SIZE;

            JLabel downLetter = new JLabel(c+"");
            JLabel upLetter = new JLabel(c+"");
            upLetter.setBounds(x2, 10, 20, 20);
            downLetter.setBounds(x2, 510, 20, 20);
            add(upLetter);
            add(downLetter);
            x2 += CELL_SIZE;

            c++;
            i--;
        }
    }

}
