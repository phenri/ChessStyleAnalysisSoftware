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

    Image image, im;
    Cell[][] cells = new Cell[8][8];
    public Chessboard(){
        super(true);
        setLayout(null);
        this.setBackground(new Color(255,255, 255));

        image = getToolkit().getImage("res\\board.gif");

        setBounds(30, 30, 600, 600);
        setOpaque(false);

    }
    public void paint(Graphics g){
//        drawBoard(g);
        g.drawImage(image,10,10,this);
    }
    private void drawBoard(Graphics g){
        int start = 30;
        int x = start, y = start, c = 0;

        for (int i = 8; i > 0; i--){
            for(int j = 8; j > 0; j--){
                if (c % 2 == 0){
                    g.setColor(LIGHT);
                    g.fillRect(x,y,CELL_SIZE,CELL_SIZE);
                }
                else {
                    g.setColor(DARK);
                    g.fillRect(x,y,CELL_SIZE,CELL_SIZE);
                }
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
    }
}
