package ua.edu.vntu.chessboard.figurs;

import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.Position;

import java.awt.*;

public class Pawn extends Figure {
    public Pawn(boolean isWhite){
        super();
        setLayout(null);

        this.isWhite = isWhite;
        name = FigureName.PAWN;

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
