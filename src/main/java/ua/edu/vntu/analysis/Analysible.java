package ua.edu.vntu.analysis;

import ua.edu.vntu.chessboard.Figures;
import ua.edu.vntu.descriptions.Party;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 18.05.13
 * Time: 18:34
 */
public interface Analysible {
    /**
     * Цінність фігур
     */
    int PAWN = 1;
    int BISHOP = 3;
    int KNIGHT = BISHOP;
    int ROOK = 6;
    int QUEEN = 10;

    public void analyze(Party party, List<Figures> figures) ;
}
