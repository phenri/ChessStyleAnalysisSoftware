package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.FormConstants;
import ua.edu.vntu.gui.chessboard.figurs.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 04.11.12
 * Time: 12:45
 */
public class Chessboard extends JPanel implements FormConstants{

    public Chessboard(){
        super(true);
        setLayout(null);
        setBackground(new Color(255,255, 255));
        initBoard();
        add(new Cells());

        setBounds(30, 30, CELL_SIZE*8+60, CELL_SIZE*8+60);

    }

    public void setCells(Cells cells){
        add(cells);
    }


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
