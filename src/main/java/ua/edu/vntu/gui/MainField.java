package ua.edu.vntu.gui;

import ua.edu.vntu.gui.chessboard.Chessboard;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 04.11.12
 * Time: 12:40
 */
public class MainField extends JPanel implements FormConstants{
    public MainField(){
        super(true);
        setLayout(null);
        add(new Chessboard());
        setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);

    }
}
