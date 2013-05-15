package ua.edu.vntu.chessboard.figurs;

import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.Position;

import java.awt.*;

public class Knight extends Figure {
    public Knight(boolean isWhite) {
        super();
        setLayout(null);

        this.isWhite = isWhite;
        name = FigureName.KNIGHT;

        if (isWhite) {
            image = getToolkit().getImage("icons\\white\\knight.png");
        } else {
            image = getToolkit().getImage("icons\\black\\knight.png");
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
        int x = Math.abs(getPosition().getX() - pos.getX());
        int y = Math.abs(getPosition().getY() - pos.getY());

        if (x > y) {
            return (x == 2) && (y == 1);
        } else {
            return (x == 1) && (y == 2);
        }

    }

}