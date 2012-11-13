package ua.edu.vntu.gui;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 03.11.12
 * Time: 21:58
 */
public class Form extends JFrame implements Constants {
    @Autowired
    private MainField field;

    public Form(){
        super("ChessStyleAnalysisSoftware");

        this.setJMenuBar(new MyMenu());

        setLayout(null);
        setBounds(200,50,FRAME_WIDTH,FRAME_HEIGHT);
        setVisible(true);
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT + 40));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setField(MainField mainField){
        this.field = mainField;
        getContentPane().add(field);
        repaint();
    }
}
