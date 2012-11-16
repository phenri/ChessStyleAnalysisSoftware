package ua.edu.vntu.gui.chessboard.figurs;

import ua.edu.vntu.gui.chessboard.Cells;
import ua.edu.vntu.gui.chessboard.Figure;
import ua.edu.vntu.gui.chessboard.Figures;
import ua.edu.vntu.moving.Position;

import java.awt.*;

/**
 * @author: Vyacheslav.Bychkovsk
 */
public class Pawn extends Figure {
    public Pawn(Cells chessboard, boolean isWhite){
        super(chessboard);
        setLayout(null);

        this.isWhite = isWhite;
        this.board = chessboard;
        name = Figures.PAWN;

        if (isWhite){
            image = getToolkit().getImage("icons\\white\\pawn.png");
        }
        else{
            image = getToolkit().getImage("icons\\black\\pawn.png");
        }
    }
    public void paint(Graphics g){
        g.drawImage(image,5,5,this);
    }

    @Override
    public boolean isAvailablePosition(Position pos) {
        if (!board.getCellByPosition(pos).isEmpty())
            return false;


        if(isWhite()){
            boolean b1;
            if(position.getY() == 2)  {
                b1 = (pos.getY() - position.getY() == 2) || (pos.getY() - position.getY() == 1);
            }else{
                b1 = (pos.getY() - position.getY() == 1);
            }
            boolean b2 = (pos.getX() - position.getX() == 0);
            return b1 && b2;
        }
        else {
            boolean b1;
            if(position.getY() == 6)  {
                b1 = (pos.getY() - position.getY() == -2) || (pos.getY() - position.getY() == -1);
            }else{
                b1 = (pos.getY() - position.getY() == -1);
            }
            boolean b2 = (pos.getX() - position.getX() == 0);
            return b1 && b2;
        }
    }

    @Override
    public String toString(){
        return "Pawn: " + parent.getPosition().toString();
    }
}
