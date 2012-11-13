package ua.edu.vntu.gui.menu;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileDescriptor;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 13.11.12
 * Time: 20:14
 */
public class OpenFile extends JMenuItem implements ActionListener{
    @Autowired
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
        FileDialog fd = new FileDialog(form);
    }
}
