package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.chessboard.Cell;
import ua.edu.vntu.gui.chessboard.Cells;
import ua.edu.vntu.readparty.Parser;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 10.11.12
 * Time: 18:12
 */
public class Mover  {

    private Cells cells;

    private String[] way;
    public Mover(Cells cells){
        this.cells = cells;
        Parser parser = new Parser("tmp\\file.pgn");
        way = parser.getPartyCode();
    }

}
