package ua.edu.vntu.gui.chessboard.figurs;

import ua.edu.vntu.gui.chessboard.Chessboard;
import ua.edu.vntu.gui.chessboard.Figure;

import java.awt.*;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class King extends Figure {
    public King(Chessboard chessboard, boolean isWhite){
        super(chessboard);
        setLayout(null);
        this.board = chessboard;
        if (isWhite){
            image = getToolkit().getImage("res\\white\\king.png");
        }
        else{
            image = getToolkit().getImage("res\\black\\king.png");
        }
    }
    public void paint(Graphics g){
        g.drawImage(image,5,5,this);
    }
}
