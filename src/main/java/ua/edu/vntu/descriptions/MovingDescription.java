package ua.edu.vntu.descriptions;

import ua.edu.vntu.chessboard.FigureName;

public class MovingDescription {

    private Position position;
    private FigureName figure;
    private boolean isBeat;
    private Castling castling;
    private char fromVertical = '0';
    private int fromHorizontal = 0;
    private boolean pawnToEnd = false;
    private FigureName newFigureName = null;

    public MovingDescription(EndParty endParty) {
        this.endParty = endParty;
    }

    public MovingDescription(Position position, FigureName figureToMove, boolean pawnToEnd, FigureName newFigureName) {
        this.position = position;
        this.figure = figureToMove;
        this.pawnToEnd = pawnToEnd;
        this.newFigureName = newFigureName;
    }

    public MovingDescription(Position position, FigureName figure){
        this.position = position;
        this.figure = figure;
    }

    public MovingDescription(Castling castling){
        this.castling = castling;
    }

    public FigureName getNewFigureName() {
        return newFigureName;
    }

    public boolean isPawnToEnd() {
        return pawnToEnd;
    }

    public EndParty getEndParty() {
        return endParty;
    }

    public boolean isEndParty(){
        return endParty != null;
    }

    private EndParty endParty;


    public Castling getCastling() {
        return castling;
    }

    public int getFromHorizontal() {
        return fromHorizontal;
    }

    public void setFromHorizontal(int fromHorizontal) {
        this.fromHorizontal = fromHorizontal;
    }

    public boolean isCastling(){
        return castling != null;
    }

    public char getFromVertical() {
        return fromVertical;
    }

    public void setFromVertical(char fromVertical) {
        this.fromVertical = fromVertical;
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
        String res2 = isPawnToEnd() ? ", пишка дійшла до кінця дошки перетворившись в " + getNewFigureName() : "";
        return isCastling() ? "Рокіровка" : "Переміщення фігури: " + figure + ":" + res + position + res2;
    }


}