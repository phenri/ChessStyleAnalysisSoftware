package ua.edu.vntu.gui;

import ua.edu.vntu.chessboard.Chessboard;

import javax.swing.*;

public class MainField extends JPanel implements Constants {
//    @Autowired
    private Chessboard chessboard = new Chessboard();


    public MainField(){
        super(true);
        setLayout(null);
        add(new CommandPanel());
        add(this.chessboard);
        setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

    }
    public void setChessboard(Chessboard chessboard){
//        this.chessboard = chessboard;
//        add(this.chessboard);
    }

}
