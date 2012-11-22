package ua.edu.vntu.gui;


import ua.edu.vntu.gui.menu.MyMenu;
import ua.edu.vntu.moving.Mover;

import javax.swing.*;
import java.awt.*;

public class Form extends JFrame implements Constants {
    private MainField field;

    public void setMover(Mover mover) {
        this.mover = mover;
    }

    private Mover mover;

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
