package ua.edu.vntu.gui;

import javax.swing.*;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class MyMenu extends JMenuBar {
    public MyMenu(){
        super();

        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open file");
        JMenuItem exit = new JMenuItem("Exit");

        JMenu about = new JMenu("About");

        file.add(open);
        file.add(new JSeparator());
        file.add(exit);
        add(file);
        add(about);
    }
}
