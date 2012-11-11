package ua.edu.vntu.gui.chessboard;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 11.11.12
 * Time: 15:41
 */
public class InvalidPositionException extends Exception {
    public InvalidPositionException(){
        super("Specified coordinates leave field boundary");
    }

    @Override
    public String getMessage() {
        return "Specified coordinates leave field boundary";
    }
}
