package ua.edu.vntu.gui.chessboard.figurs;

import ua.edu.vntu.gui.chessboard.Cells;
import ua.edu.vntu.gui.chessboard.Chessboard;
import ua.edu.vntu.gui.chessboard.Figure;
import ua.edu.vntu.gui.chessboard.Position;

import java.awt.*;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class King extends Figure {
    public King(Cells chessboard, boolean isWhite){
        super(chessboard);
        setLayout(null);
        this.isWhite = isWhite;
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

    @Override
    public boolean isAvailablePosition(Position pos){
        int i = (int)pos.getX() - position.getX();
        boolean x = ((i == 1)||(i == -1)||(i == 0));
        int j = (int)pos.getY() - position.getY();
        boolean y = ((j == 1)||(j == -1)||(j == 0));

        return x && y;
    }
    @Override
    public String toString(){
        return "King: " + parent.getPosition().toString();
    }
}
