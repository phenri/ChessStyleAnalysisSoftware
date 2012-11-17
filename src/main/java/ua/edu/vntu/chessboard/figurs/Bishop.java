package ua.edu.vntu.chessboard.figurs;

import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.Position;

import java.awt.*;

public class Bishop extends Figure {
    public Bishop(Cells chessboard, boolean isWhite){
        super(chessboard);
        setLayout(null);

        this.isWhite = isWhite;
        this.board = chessboard;
        name = FigureName.BISHOP;

        if (isWhite){
            image = getToolkit().getImage("icons\\white\\bishop.png");
        }
        else{
            image = getToolkit().getImage("icons\\black\\bishop.png");
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
        return "Bishop: " + parent.getPosition().toString();
    }
}
