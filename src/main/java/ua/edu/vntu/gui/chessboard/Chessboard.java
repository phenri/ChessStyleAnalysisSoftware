package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.FormConstants;
import ua.edu.vntu.gui.chessboard.figurs.Rook;

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
    Image image;
    private boolean pressed = false;

    public Chessboard(){
        super(true);
        setLayout(null);
        setBackground(new Color(255,255, 255));
//        initBoard();
//        Cells c = new Cells(this);
//        cells = c.getCells();

//        initFigures();

        image = getToolkit().getImage("res\\I_Rook.png");
        System.out.println(image.getWidth(this));

//        add(c);
        setBounds(30, 30, CELL_SIZE*8+60, CELL_SIZE*8+60);

    }

    public void paint (Graphics g){
        g.drawImage(image,0,0,this);
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
        Figure figure = new Rook(this);
//        new Thread(figure).start();
        cells[7][0].addFigure(figure);

    }

    public void Listener(Figure figure){
        if (!pressed){
            pressed = true;
            buffer = figure;
        }
    }
    public void Mover(Cell cell){
        if(pressed){
            if(cell.addFigure(buffer)){
                pressed = false;
            }
        }
        repaint();
    }


}
