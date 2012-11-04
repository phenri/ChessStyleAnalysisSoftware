package ua.edu.vntu.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 03.11.12
 * Time: 21:58
 */
public class Form extends JFrame implements FormConstants{

    public Form(){
        super("ChessStyleAnalysisSoftware");

        getContentPane().add(new MainField());
        setLayout(null);
        setBounds(200,50,FRAME_WIDTH,FRAME_HEIGHT);
        setVisible(true);
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT + 40));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
