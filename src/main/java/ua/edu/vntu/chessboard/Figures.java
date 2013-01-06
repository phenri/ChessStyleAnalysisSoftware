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
}
