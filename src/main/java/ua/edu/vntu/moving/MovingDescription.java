package ua.edu.vntu.moving;

import ua.edu.vntu.gui.chessboard.Figures;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 11.11.12
 * Time: 17:32
 */
public class MovingDescription {

    private Position position;
    private Figures figure;
    private boolean isBeat;
    private Castling castling;

    private char from;

    public MovingDescription(Position position, Figures figure){

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

    public Figures getFigureName(){
        return figure;
    }

    @Override
    public String toString(){
        String res = isBeat ? " x:" : "";
        String result = isCastling() ? "Рокіровка" : "Переміщення фігури: " + figure + ":" + res + position;
        return result;
    }


}