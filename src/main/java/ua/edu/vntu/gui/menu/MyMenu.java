package ua.edu.vntu.gui.menu;

import ua.edu.vntu.handlers.MenuActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenu extends JMenuBar {

    private MenuActions menuActions;

    public MyMenu(){
        super();

        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open file...");
        JMenuItem start = new JMenuItem("Select first");
        JMenuItem exit = new JMenuItem("Exit");

        JMenu game = new JMenu("Game");

        JMenu property = new JMenu("Options");

        JMenu about = new JMenu("About");

        file.add(open);
        file.add(start);
        file.add(new JSeparator());
        file.add(exit);

        property.add(new Property());

        add(file);
        add(game);
        add(property);
        add(about);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuActions.open();
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuActions.select();
            }
        });
    }

    public void setMenuActions(MenuActions menuActions) {
        this.menuActions = menuActions;
    }
}
