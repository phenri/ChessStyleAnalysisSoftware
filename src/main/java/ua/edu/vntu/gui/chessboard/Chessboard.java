package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.FormConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 04.11.12
 * Time: 12:45
 */
public class Chessboard extends JPanel implements FormConstants{

    Cell[][] cells = new Cell[8][8];
    public Chessboard(){
        super(true);
        setLayout(null);
        int start = 30;
        int x = start, y = start, c = 0;

        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                cells[i][j] = new Cell();
                if (c % 2 == 0){
                    cells[i][j].setBackground(lIGHT);
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

        y = 45;
        for(int i = 1; i <= 8; i++){
            JLabel leftVertical = new JLabel(i+"");
            JLabel rightVertical = new JLabel(i+"");
            leftVertical.setBounds(10, y, 20, 20);
            rightVertical.setBounds(520, y, 20, 20);
            add(leftVertical);
            add(rightVertical);
            y += CELL_SIZE;
        }

        x = 50;
        for(int i = 'A'; i <= 'H'; i++){
            JLabel leftVertical = new JLabel((char)i+"");
            JLabel rightVertical = new JLabel((char)i+"");
            leftVertical.setBounds(x, 10, 20, 20);
            rightVertical.setBounds(x, 510, 20, 20);
            add(leftVertical);
            add(rightVertical);
            x += CELL_SIZE;
        }

        setBounds(30,30,540,540);
        setBackground(new Color(255,255, 255));
    }
}
