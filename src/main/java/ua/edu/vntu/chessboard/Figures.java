package ua.edu.vntu.chessboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Містить в собі ссилки на всі фігури
 */
public class Figures {

    private List<Figure> blackFigures = new ArrayList<>(),
            whiteFigures = new ArrayList<>();

    public Figures(List<Figure> blackFigures, List<Figure> whiteFigures) {
        this.blackFigures = blackFigures;
        this.whiteFigures = whiteFigures;
    }

    public List<Figure> getBlackFigures() {
        return blackFigures;
    }

    public List<Figure> getWhiteFigures() {
        return whiteFigures;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<Figure> white = new ArrayList<>();

        for (Figure f : whiteFigures) {
            white.add((Figure) f.clone());
        }

        List<Figure> black = new ArrayList<>();
        for (Figure f : blackFigures) {
            black.add((Figure) f.clone());
        }
        return new Figures(black, white);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Figure)) {
            return false;
        }
        Figures f = (Figures) obj;

        return f.getBlackFigures().equals(blackFigures) && f.getWhiteFigures().equals(whiteFigures);
    }
}
