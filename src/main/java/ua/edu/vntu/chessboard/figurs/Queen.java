package ua.edu.vntu.chessboard.figurs;

import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.Position;

import java.awt.*;

public class Queen extends Figure {
    public Queen(boolean isWhite) {
        super();
        setLayout(null);

        this.isWhite = isWhite;

        name = FigureName.QUEEN;

        if (isWhite) {
            image = getToolkit().getImage("icons\\white\\queen.png");
        } else {
            image = getToolkit().getImage("icons\\black\\queen.png");
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

        int k = Math.abs(getPosition().getX() - (int) pos.getX());
        int n = Math.abs(getPosition().getY() - pos.getY());

        if (k == n) {
            return true;
        } else if (this.getPosition().getX() == pos.getX() && getPosition().getY() != pos.getY()) {
            return true;
        } else if (this.getPosition().getX() != pos.getX() && getPosition().getY() == pos.getY()) {
            return true;
        }
        return false;
    }

}
