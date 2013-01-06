package ua.edu.vntu.gui.menu;

import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.vntu.Main;
import ua.edu.vntu.gui.Form;
import ua.edu.vntu.moving.Mover;
import ua.edu.vntu.readparty.Parser;

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
        File file = new File("D:\\VNTU\\bachelor thesis\\ChessStyleAnalysisSoftware\\tmp");
        fileChooser.setCurrentDirectory(file);
        fileChooser.showOpenDialog(form);

        File f =  fileChooser.getSelectedFile();

        Parser parser = new Parser(f);

//        if (f != null){
//            Mover mover = (Mover) Main.context.getBean("mover");
//            mover.startParty(1);
//        }
    }
}
