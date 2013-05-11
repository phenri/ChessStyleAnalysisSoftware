package ua.edu.vntu.gui.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Property extends JMenuItem implements ActionListener {

    public Property() {
        super("Properties");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Prop");
    }
}
