package ua.edu.vntu.gui.chessboard;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 10.11.12
 * Time: 19:33
 */
public class Position {
    private char letter;
    private byte number;
    public Position(char letter, byte number){
        this.letter = letter;
        this.number = number;
    }
    public char getX(){
        return letter;
    }
    public byte getY(){
        return number;
    }

    @Override
    public String toString() {
        return ""+letter+number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        try{
            Position position = (Position) obj;

            if ((position.getX() == this.getX())&&(position.getY() == this.getY())){
                return true;
            }
            else {
                return false;
            }
        }
        catch (Throwable e){
            return false;
        }
    }
}
