package ua.edu.vntu.chessboard.figurs;

import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.Position;

import java.awt.*;

public class Queen extends Figure {
    public Queen(Cells chessboard, boolean isWhite){
        super(chessboard);
        setLayout(null);

        this.isWhite = isWhite;
        this.board = chessboard;

        name = FigureName.QUEEN;

        if (isWhite){
            image = getToolkit().getImage("icons\\white\\queen.png");
        }
        else{
            image = getToolkit().getImage("icons\\black\\queen.png");
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
