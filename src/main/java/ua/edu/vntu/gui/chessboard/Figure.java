package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.FormConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class Figure extends JPanel implements FormConstants{
    public Figure(){
        super();
        System.out.println("figure");
        setLayout(null);
        setDoubleBuffered(true);

        setBackground(Color.RED);
        setOpaque(true);
//        setLocation(50,50);
//        setSize(CELL_SIZE,CELL_SIZE);
        setBounds(50,50,CELL_SIZE,CELL_SIZE);
    }
}
