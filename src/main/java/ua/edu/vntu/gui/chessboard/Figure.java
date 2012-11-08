package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.FormConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class Figure extends JPanel implements FormConstants,MouseMotionListener{
    public Figure(){
        super();
        setLayout(null);
        setDoubleBuffered(true);

        setBackground(Color.RED);
        setOpaque(true);
        setBounds(50,50,CELL_SIZE,CELL_SIZE);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
