package ua.edu.vntu.descriptions;

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
