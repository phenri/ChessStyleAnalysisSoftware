package ua.edu.vntu.descriptions;

import ua.edu.vntu.chessboard.FigureName;

public class MovingDescription {

    private Position position;
    private FigureName figure;
    private boolean isBeat;

    public Castling getCastling() {
        return castling;
    }

    private Castling castling;

    private char from;

    public MovingDescription(Position position, FigureName figure){

        this.position = position;
        this.figure = figure;
    }

    public MovingDescription(Castling castling){
        this.castling = castling;
    }

    public boolean isCastling(){
        return castling != null;
    }

    public void setFrom(char from) {
        this.from = from;
    }



    public void setBeat(boolean b){
        isBeat = b;
    }

    public boolean isBeat(){
        return isBeat;
    }

    public Position getPosition(){
        return position;
    }

    public FigureName getFigureName(){
        return figure;
    }

    @Override
    public String toString(){
        String res = isBeat ? " x:" : "";
        String result = isCastling() ? "Рокіровка" : "Переміщення фігури: " + figure + ":" + res + position;
        return result;
    }


}