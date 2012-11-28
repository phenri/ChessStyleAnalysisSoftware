package ua.edu.vntu.chessboard.figurs;

import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.Position;

import java.awt.*;

public class Bishop extends Figure {
    public Bishop(boolean isWhite){
        super();
        setLayout(null);

        this.isWhite = isWhite;
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
    public boolean isAvailablePosition(Position pos, boolean isBeat) {
        return this.isAvailablePosition(pos);
    }

    @Override
    public boolean isAvailablePosition(Position pos) {
        int k = Math.abs(position.getX() -(int)pos.getX());
        int n = Math.abs(position.getY() - pos.getY());
        if (k == n){
            return true;
        }
        return false;
    }
}
