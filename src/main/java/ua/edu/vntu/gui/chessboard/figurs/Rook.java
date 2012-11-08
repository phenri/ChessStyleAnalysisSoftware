package ua.edu.vntu.gui.chessboard.figurs;

import ua.edu.vntu.gui.chessboard.Chessboard;
import ua.edu.vntu.gui.chessboard.Figure;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 08.11.12
 * Time: 19:26
 */
public class Rook extends Figure {
    Image image;
    Chessboard board;
    public Rook(Chessboard chessboard){
        super(chessboard);
        this.board = chessboard;
        image = getToolkit().getImage("res\\I_Rook.gif");
        System.out.println(image);
//        setSize(200,200);
    }
    public void paint(Graphics g){
        g.drawImage(image,10,10,this);

    }

}
