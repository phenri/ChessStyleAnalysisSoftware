package ua.edu.vntu.gui.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuProperty extends JMenuItem implements ActionListener {

    public MenuProperty() {
        super("Properties");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Prop");
    }
}
