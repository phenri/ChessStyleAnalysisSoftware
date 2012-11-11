package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 11.11.12
 * Time: 17:32
 */
public class MovingDescription {

    private Position position;

    private String figure;

    private boolean isBeat;

    private Castling castling;

    private int code; //для описання додаткової інформації про хід

    public MovingDescription(Castling castling){
        this.castling = castling;

    }

    public boolean isCastling(){
        return castling != null;
    }

    public MovingDescription(Position position, String figure){
        this.position = position;
        this.figure = figure;

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

    public String getFigureName(){
        return figure;
    }

    @Override
    public String toString(){
        String res = isBeat ? " x:" : "";
        String result = isCastling() ? "Castling" : "Moving figure: " + figure + ":" + res + position;
        return result;
    }


}
