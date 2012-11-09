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

    /**
     *
     * @param chessboard reference on chessboard
     * @param isWhite    if true figure white else figure black
     *
     */
    public Rook(Chessboard chessboard, boolean isWhite){
        super(chessboard);
        setLayout(null);
        this.board = chessboard;
        if (isWhite){
            image = getToolkit().getImage("res\\white\\rook.png");
        }
        else{
            image = getToolkit().getImage("res\\black\\rook.png");
        }
    }
    public void paint(Graphics g){
        g.drawImage(image,5,5,this);
    }

}
