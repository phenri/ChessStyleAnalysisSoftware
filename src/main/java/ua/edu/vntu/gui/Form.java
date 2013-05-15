package ua.edu.vntu.gui;


import ua.edu.vntu.gui.menu.MyMenu;
import ua.edu.vntu.handlers.CommandActions;
import ua.edu.vntu.handlers.MenuActions;

import javax.swing.*;
import java.awt.*;

public class Form extends JFrame implements Constants {
    private MainField field = new MainField();
    private MyMenu menubar;

    public Form() {
        super("ChessStyleAnalysisSoftware");

        menubar = new MyMenu();
        setJMenuBar(menubar);

        setLayout(null);
        setBounds(200, 50, FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT + 40));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(field);

    }

    public void setActions(MenuActions actions) {
        menubar.setMenuActions(actions);
    }

    public void setCommandActions(CommandActions commandActions) {
        field.setCommandActions(commandActions);
    }
}
