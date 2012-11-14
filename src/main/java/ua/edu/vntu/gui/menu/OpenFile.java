package ua.edu.vntu.gui.menu;

import ua.edu.vntu.Main;
import ua.edu.vntu.gui.Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 13.11.12
 * Time: 20:14
 */
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

        System.out.println(form);
    }
}
