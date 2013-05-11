package ua.edu.vntu.gui.menu;

import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.vntu.gui.Form;
import ua.edu.vntu.parsing.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenFile extends JMenuItem implements ActionListener{

    @Autowired
    private Form form;

    public OpenFile(){
        super("Open file...");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hello");

        JFileChooser fileChooser = new JFileChooser();
        File file = new File("D:\\VNTU\\bachelor thesis\\ChessStyleAnalysisSoftware\\tmp");
        fileChooser.setCurrentDirectory(file);
        fileChooser.showOpenDialog(form);

        File f =  fileChooser.getSelectedFile();

        new Parser(f);

    }
}
