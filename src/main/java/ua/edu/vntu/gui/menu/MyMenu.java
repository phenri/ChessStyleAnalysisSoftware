package ua.edu.vntu.gui.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
