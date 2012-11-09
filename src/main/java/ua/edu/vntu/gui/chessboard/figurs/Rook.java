package ua.edu.vntu.gui.chessboard.figurs;

import ua.edu.vntu.gui.chessboard.Cells;
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

    /**
     *
     * @param chessboard reference on chessboard
     * @param isWhite    if true figure white else figure black
     *
     */
    public Rook(Cells chessboard, boolean isWhite){
        super(chessboard);
        setLayout(null);
        this.isWhite = isWhite;
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
    @Override
    public String toString(){
        return "Rook: " + parent.getAddress();
    }

}
