package ua.edu.vntu.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.vntu.Application;
import ua.edu.vntu.Main;
import ua.edu.vntu.containers.ContainerParsedPartiesService;
import ua.edu.vntu.descriptions.Party;
import ua.edu.vntu.gui.SelectParty;
import ua.edu.vntu.parsing.Parser;
import ua.edu.vntu.reading.Mover;

import javax.swing.*;
import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 11.05.13
 * Time: 20:04
 */

@Service
public class MenuActionHandler implements MenuActions {

    @Autowired
    private Mover mover;

    @Override
    public void open() {
        System.out.println("Hello");

        JFileChooser fileChooser = new JFileChooser();
        File file = new File("D:\\VNTU\\bachelor thesis\\ChessStyleAnalysisSoftware\\tmp");
        fileChooser.setCurrentDirectory(file);
        fileChooser.showOpenDialog(null);
//
        File f = fileChooser.getSelectedFile();
//        File f = new File("D:\\VNTU\\bachelor thesis\\ChessStyleAnalysisSoftware\\tmp\\file.pgn");
        ContainerParsedPartiesService.getInstance().clear();
        new Parser(f);
    }

    @Override
    public void select() {
//        Mover mover = (Mover) Main.context.getBean("mover");
        List<Party> parties = ((ContainerParsedPartiesService)ContainerParsedPartiesService.getInstance()).getParties();

        SelectParty selector = new SelectParty(this,parties);

        selector.setLocationRelativeTo(null);
        selector.setResizable(false);
        selector.setVisible(true);
//        int i;
//        mover.startParty(0);
    }

    public void start(int id){
//        Mover mover = Application.ctx.getBean(Mover.class);
        mover.startParty(id);
    }
}
