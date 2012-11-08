package ua.edu.vntu.gui;

import ua.edu.vntu.gui.chessboard.Chessboard;
import ua.edu.vntu.gui.chessboard.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 04.11.12
 * Time: 12:40
 */
public class MainField extends JPanel implements FormConstants, MouseMotionListener{
    Figure f;
    public MainField(){
        super(true);
        setLayout(null);


        f = new Figure();

        add(f);

        add(new Chessboard());
        setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        addMouseMotionListener(this);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = f.getLocation();
        f.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
