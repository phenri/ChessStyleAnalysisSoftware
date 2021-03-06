package ua.edu.vntu.chessboard.figurs;

import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.Position;

import java.awt.*;

public class Rook extends Figure {

    public Rook(boolean isWhite) {
        super();
        setLayout(null);

        this.isWhite = isWhite;
        name = FigureName.ROOK;

        if (isWhite) {
            image = getToolkit().getImage("icons\\white\\rook.png");
        } else {
            image = getToolkit().getImage("icons\\black\\rook.png");
        }
    }

    public void paint(Graphics g) {
        g.drawImage(image, 5, 5, this);
    }

    @Override
    public boolean isAvailablePosition(Position pos, boolean isBeat) {
        return this.isAvailablePosition(pos);
    }

    @Override
    public boolean isAvailablePosition(Position pos) {
        if (this.getPosition().getX() == pos.getX() && getPosition().getY() != pos.getY()) {
            return true;
        } else if (this.getPosition().getX() != pos.getX() && getPosition().getY() == pos.getY()) {
            return true;
        }
        return false;
    }

}
