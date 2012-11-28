package ua.edu.vntu.chessboard.figurs;

import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.Position;

import java.awt.*;

public class King extends Figure {
    public King(boolean isWhite){
        super();
        setLayout(null);

        this.isWhite = isWhite;
        name = FigureName.KING;

        if (isWhite){
            image = getToolkit().getImage("icons\\white\\king.png");
        }
        else{
            image = getToolkit().getImage("icons\\black\\king.png");
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
    public boolean isAvailablePosition(Position pos){
        int i = (int)pos.getX() - position.getX();
        boolean x = ((i == 1)||(i == -1)||(i == 0));
        int j = (int)pos.getY() - position.getY();
        boolean y = ((j == 1)||(j == -1)||(j == 0));

        return x && y;
    }

}
