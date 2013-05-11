package ua.edu.vntu.gui.menu;

import ua.edu.vntu.containers.ContainerPartiesService;
import ua.edu.vntu.parsing.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenFile extends JMenuItem implements ActionListener{

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
        fileChooser.showOpenDialog(null);

        File f =  fileChooser.getSelectedFile();
        ContainerPartiesService.getInstance().clear();
        new Parser(f);

    }
}
