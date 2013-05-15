package ua.edu.vntu.gui;

import ua.edu.vntu.chessboard.Chessboard;
import ua.edu.vntu.handlers.CommandActions;

import javax.swing.*;

public class MainField extends JPanel implements Constants {

    private static Chessboard chessboard = new Chessboard();
    private CommandPanel commandPanel;


    public MainField() {
        super(true);
        setLayout(null);
        commandPanel = new CommandPanel();
        add(commandPanel);
        add(this.chessboard);
        setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

    }

    public void setCommandActions(CommandActions commandActions) {
        commandPanel.setCommandActions(commandActions);
    }

    public static Chessboard getChessboard() {
        return chessboard;
    }
}
