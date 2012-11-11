package ua.edu.vntu.gui.chessboard;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 11.11.12
 * Time: 18:06
 */
public class Castling {
    private boolean isLong = false;
    public Castling(boolean isLong){
        this.isLong = isLong;

    }

    public boolean isLong(){
        return isLong;
    }
}
