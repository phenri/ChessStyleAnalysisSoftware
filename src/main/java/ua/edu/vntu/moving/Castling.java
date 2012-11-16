package ua.edu.vntu.moving;

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

    @Override
    public String toString() {
        return "Рокіровка " + (isLong ? "через велику сторону.": "через малу сторону.");
    }
}
