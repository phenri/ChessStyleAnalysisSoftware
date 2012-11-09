package ua.edu.vntu.gui.chessboard.figurs;

import ua.edu.vntu.gui.chessboard.Cells;
import ua.edu.vntu.gui.chessboard.Chessboard;
import ua.edu.vntu.gui.chessboard.Figure;

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
        if (isWhite){
            image = getToolkit().getImage("res\\white\\pawn.png");
        }
        else{
            image = getToolkit().getImage("res\\black\\pawn.png");
        }
    }
    public void paint(Graphics g){
        g.drawImage(image,5,5,this);
    }
    @Override
    public String toString(){
        return "Pawn: " + parent.getAddress();
    }
}