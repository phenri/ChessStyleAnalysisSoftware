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

    private char fromVertical = '0';

    public int getFromHorizontal() {
        return fromHorizontal;
    }

    public void setFromHorizontal(int fromHorizontal) {
        System.out.println("Set fromHorizontal: " + fromHorizontal + " " + this);
        this.fromHorizontal = fromHorizontal;
    }

    private int fromHorizontal = 0;

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

    public char getFromVertical() {
        return fromVertical;
    }

    public void setFromVertical(char fromVertical) {
        this.fromVertical = fromVertical;
        System.out.println("Set fromVertical: " + fromVertical+ " " + this);
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
        return isCastling() ? "Рокіровка" : "Переміщення фігури: " + figure + ":" + res + position;
    }


}