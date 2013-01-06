package ua.edu.vntu.descriptions;

import java.util.List;
import java.util.Map;

public class Party {
    private Map<String,String> tags;
    private int id;
    private List<MovingDescription> whiteMoves, blackMoves;

    public Party(Map<String, String> tags, int id, List<MovingDescription> whiteMoves, List<MovingDescription> blackMoves) {
        this.tags = tags;
        this.id = id;
        this.whiteMoves = whiteMoves;
        this.blackMoves = blackMoves;
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

