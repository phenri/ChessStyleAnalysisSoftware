package ua.edu.vntu.gui;

import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.vntu.chessboard.Chessboard;

import javax.swing.*;

public class MainField extends JPanel implements Constants {
    @Autowired
    private Chessboard chessboard;


    public MainField(){
        super(true);
        setLayout(null);
        setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

    }
    public void setChessboard(Chessboard chessboard){
        this.chessboard = chessboard;
        add(this.chessboard);
    }

}
