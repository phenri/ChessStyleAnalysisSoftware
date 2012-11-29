package ua.edu.vntu.gui.menu;

import ua.edu.vntu.Main;
import ua.edu.vntu.gui.Form;
import ua.edu.vntu.moving.Mover;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenFile extends JMenuItem implements ActionListener{
    private JFrame form;

    public OpenFile(){
        super("Open file...");
        addActionListener(this);
    }

    public void setForm(JFrame form){
        this.form = form;
        repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hello");

        form = (Form) Main.context.getBean("form");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(form);

        File f =  fileChooser.getSelectedFile();

        Mover mover = (Mover) Main.context.getBean("mover");
        mover.startParty(f);
    }
}
