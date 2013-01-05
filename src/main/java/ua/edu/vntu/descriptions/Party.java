package ua.edu.vntu.descriptions;

import java.util.Map;

public class Party {
    private Map<String,String> tags;
    private ContainerFigure containerFigure;
    private int id;

    public Party(Map<String, String> tags, ContainerFigure containerFigure, int id) {
        this.tags = tags;
        this.containerFigure = containerFigure;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public ContainerFigure getContainerFigure() {
        return containerFigure;
    }

    @Override
    public String toString() {
        return "Party{" +
                "tags=" + tags +
                ", containerFigure=" + containerFigure +
                ", id=" + id +
                '}';
    }
}

