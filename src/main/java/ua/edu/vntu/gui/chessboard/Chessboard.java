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

    private Cell[][] cells = new Cell[8][8];

    private Figure buffer;

    private boolean pressed = false;

    public Chessboard(){
        super(true);
        setLayout(null);
        setBackground(new Color(255,255, 255));
        initBoard();
        Cells c = new Cells(this);
        cells = c.getCells();

        initFigures();



        add(c);
        setBounds(30, 30, CELL_SIZE*8+60, CELL_SIZE*8+60);

    }


    private void initBoard(){
        int start = 30;

        int x = start, y = start + 15, c = 0;

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
    }

    private void initFigures(){
        Figure figure = new  Figure(this);
        System.out.println("Add figure "+cells[7][0].addFigure(figure));

    }

    public void Listener(Figure figure){
        if (!pressed){
            pressed = true;
//            System.out.println("pressed " +pressed);
            buffer = figure;
        }
        else pressed = false;
        repaint();
    }
    public void Mover(Cell cell){
        if(pressed && buffer!=null){
            pressed = false;
            cell.addFigure(buffer);
        }
        else pressed = true;
        repaint();
//        else System.out.println("not pressed ");
    }


}
