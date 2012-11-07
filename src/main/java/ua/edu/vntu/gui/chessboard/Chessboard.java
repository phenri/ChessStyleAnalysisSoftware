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
//        int start = 30;
//        int x = start, y = start, c = 0;

//        for (int i = 0; i < 8; i++){
//            for(int j = 0; j < 8; j++){
//                cells[i][j] = new Cell();
//                if (c % 2 == 0){
//                    cells[i][j].setBackground(LIGHT);
//                }
//                else {
//                    cells[i][j].setBackground(DARK);
//                }
//                cells[i][j].setBounds(x,y,CELL_SIZE,CELL_SIZE);
//                add(cells[i][j]);
//                x +=CELL_SIZE;
//                c++;
//            }
//            y+=CELL_SIZE;
//            x = start;
//            if (i%2 == 1 ){
//                c = 0;
//            }else {
//                c = 1;
//            }
//        }
//
//        y = 45;
//        for(int i = 8; i > 0; i--){
//            JLabel leftVertical = new JLabel(i+"");
//            JLabel rightVertical = new JLabel(i+"");
//            leftVertical.setBounds(10, y, 20, 20);
//            rightVertical.setBounds(520, y, 20, 20);
//            add(leftVertical);
//            add(rightVertical);
//            y += CELL_SIZE;
//        }
//
//        x = 50;
//        for(int i = 'A'; i <= 'H'; i++){
//            JLabel leftVertical = new JLabel((char)i+"");
//            JLabel rightVertical = new JLabel((char)i+"");
//            leftVertical.setBounds(x, 10, 20, 20);
//            rightVertical.setBounds(x, 510, 20, 20);
//            add(leftVertical);
//            add(rightVertical);
//            x += CELL_SIZE;
//        }

//        image = getToolkit().getImage("res\\board.gif");
//        im = getToolkit().getImage("WhiteIcons\\wq.gif");
//        getToolkit().
//        System.out.println(image);

        setBounds(30, 30, 540, 540);

    }
    public void paint(Graphics g){
        drawBoard(g);
//        g.drawImage(image,10,10,this);
//        g.drawImage(im,10,10,this);

    }
    private void drawBoard(Graphics g){
        int start = 30;
        int x = start, y = start, c = 0;

        for (int i = 8; i > 0; i--){
            for(int j = 8; j > 0; j--){
//                cells[i][j] = new Cell();
                if (c % 2 == 0){
//                    cells[i][j].setBackground(LIGHT);
                    g.setColor(LIGHT);
                    g.fillRect(x,y,CELL_SIZE,CELL_SIZE);
                }
                else {
//                    cells[i][j].setBackground(DARK);
                    g.setColor(DARK);
                    g.fillRect(x,y,CELL_SIZE,CELL_SIZE);
                }
//                cells[i][j].setBounds(x,y,CELL_SIZE,CELL_SIZE);
//                add(cells[i][j]);
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
