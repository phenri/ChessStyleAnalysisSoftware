package ua.edu.vntu.gui.menu;

import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.vntu.Main;
import ua.edu.vntu.reading.Mover;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JMenuItem implements ActionListener {

    @Autowired
    Mover mover;

    public Start() {
        super("Start party");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Mover mover = (Mover) Main.context.getBean("mover");
        mover.startParty(0);
    }
}
