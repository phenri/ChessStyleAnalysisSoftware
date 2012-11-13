package ua.edu.vntu.gui.menu;

import javax.swing.*;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class MyMenu extends JMenuBar {
    public MyMenu(){
        super();

        JMenu file = new JMenu("File");
        JMenuItem open = new OpenFile();
        JMenuItem exit = new JMenuItem("Exit");

        JMenu about = new JMenu("About");

        file.add(open);
        file.add(new JSeparator());
        file.add(exit);
        add(file);
        add(about);
    }
}
