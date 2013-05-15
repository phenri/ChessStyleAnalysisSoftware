package ua.edu.vntu.descriptions;

import ua.edu.vntu.chessboard.Figures;

import java.util.List;
import java.util.Map;

public class Party {
    private Map<String, String> tags;
    private int id;
    private List<MovingDescription> whiteMoves, blackMoves;
    private Figures figures;

    public Party(Map<String, String> tags, int id, List<MovingDescription> whiteMoves, List<MovingDescription> blackMoves) {
        this.tags = tags;
        this.id = id;
        this.whiteMoves = whiteMoves;
        this.blackMoves = blackMoves;
    }

    public Figures getFigures() {
        return figures;
    }

    public void setFigures(Figures figures) {
        this.figures = figures;
    }

    public List<MovingDescription> getWhiteMoves() {
        return whiteMoves;
    }

    public List<MovingDescription> getBlackMoves() {
        return blackMoves;
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Party{" +
                "tags=" + tags +
                ", id=" + id +
                '}';
    }
}

