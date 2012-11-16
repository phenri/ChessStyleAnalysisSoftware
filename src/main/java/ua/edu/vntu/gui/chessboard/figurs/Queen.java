package ua.edu.vntu.gui.chessboard.figurs;

import ua.edu.vntu.gui.chessboard.Cells;
import ua.edu.vntu.gui.chessboard.Figure;
import ua.edu.vntu.gui.chessboard.Figures;
import ua.edu.vntu.moving.Position;

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

        name = Figures.QUEEN;

        if (isWhite){
            image = getToolkit().getImage("res\\icons\\white\\queen.png");
        }
        else{
            image = getToolkit().getImage("res\\icons\\black\\queen.png");
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
