package ua.edu.vntu.handlers;

import org.springframework.stereotype.Service;
import ua.edu.vntu.chessboard.Chessboard;
import ua.edu.vntu.chessboard.Figures;
import ua.edu.vntu.containers.ReadedParty;
import ua.edu.vntu.gui.MainField;
import ua.edu.vntu.gui.Result;
import ua.edu.vntu.gui.table.MyTable;

import javax.swing.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 12.05.13
 * Time: 12:37
 */
@Service
public class CommandActionsHandler implements CommandActions {

    private Chessboard chessboard = MainField.getChessboard();

    private List<Figures> states = ReadedParty.states;

    private int currentIndex = 0;

    public synchronized void toEnd() {
        currentIndex = states.size() - 1;
        select(currentIndex);
    }

    @Override
    public synchronized void previous() {
        if (currentIndex > -1) {
            select(--currentIndex);
        }
    }

    @Override
    public synchronized void play() {
        JDialog result =  new Result();
        result.setLocationRelativeTo(null);
        result.setVisible(true);
        System.out.println("Play");
    }

    @Override
    public synchronized void pause() {
        System.out.println("Pause");
    }

    @Override
    public synchronized void next() {
        if (currentIndex < states.size() - 1) {
            select(++currentIndex);
        }

    }

    private void select(int index) {
        if (index == 0) {
            MyTable.table.changeSelection(0, 0, false, false);
            chessboard.paintFigures(states.get(index));
            return;
        }
        int selected = index / 2;

        System.out.println(selected);

        if ((index % 2 == 1)) {
            MyTable.table.changeSelection(selected, 0, false, false);
        }

        chessboard.paintFigures(states.get(index));
    }

    @Override
    public synchronized void toBegin() {
        currentIndex = 0;
        select(currentIndex);
    }

}