package ua.edu.vntu.gui.chessboard.figurs;

import ua.edu.vntu.gui.chessboard.Cells;
import ua.edu.vntu.gui.chessboard.Figure;
import ua.edu.vntu.gui.chessboard.moving.Position;

import java.awt.*;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class Queen extends Figure {
    public Queen(Cells chessboard, boolean isWhite){
        super(chessboard);
        setLayout(null);
        this.isWhite = isWhite;
        this.board = chessboard;
        if (isWhite){
            image = getToolkit().getImage("res\\white\\queen.png");
        }
        else{
            image = getToolkit().getImage("res\\black\\queen.png");
        }
    }

    public void paint(Graphics g){
        g.drawImage(image,5,5,this);
    }

    @Override
    public boolean isAvailablePosition(Position pos) {
        return true;
    }

    @Override
    public String toString(){
        return "Queen: " + parent.getPosition().toString();
    }
}
