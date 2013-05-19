package ua.edu.vntu.handlers;

import org.springframework.stereotype.Service;
import ua.edu.vntu.Main;
import ua.edu.vntu.containers.ContainerParsedPartiesService;
import ua.edu.vntu.parsing.Parser;
import ua.edu.vntu.reading.Mover;

import javax.swing.*;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 11.05.13
 * Time: 20:04
 */

@Service
public class MenuActionHandler implements MenuActions {

    @Override
    public void open() {
        System.out.println("Hello");

//        JFileChooser fileChooser = new JFileChooser();
//        File file = new File("D:\\VNTU\\bachelor thesis\\ChessStyleAnalysisSoftware\\tmp");
//        fileChooser.setCurrentDirectory(file);
//        fileChooser.showOpenDialog(null);

//        File f = fileChooser.getSelectedFile();
        File f = new File("D:\\VNTU\\bachelor thesis\\ChessStyleAnalysisSoftware\\tmp\\file.pgn");
        ContainerParsedPartiesService.getInstance().clear();
        new Parser(f);
    }

    @Override
    public void select() {
        Mover mover = (Mover) Main.context.getBean("mover");
        mover.startParty(0);
    }
}
