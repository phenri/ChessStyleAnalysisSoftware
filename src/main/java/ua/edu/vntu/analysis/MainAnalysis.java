package ua.edu.vntu.analysis;

import org.springframework.stereotype.Component;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.descriptions.Party;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 18.05.13
 * Time: 18:33
 */
@Component
public class MainAnalysis implements Analysible, Runnable {
    private Party party;

    @Override
    public void analyze(Party party) {
        this.party = party;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        List<RatingMove> ratingMoves = ratingAnalysis(party.getWhiteMoves(), party.getBlackMoves());

    }



    /**
     * Метод для підрахунку сили ходу. Сила ходу визначається різницею у силі між фігурами якими здійснено хід
     * @param white    білі фігури
     * @param black     чорні фігури
     * @return
     */
    private List<RatingMove> ratingAnalysis(List<MovingDescription> white, List<MovingDescription> black) {

        List<RatingMove> result = new ArrayList<>();

        for (int i = 0; (i < white.size() || i < black.size()); i++) {
            MovingDescription w = null, b = null;

            if (i < white.size()) {
                w = white.get(i);
            }
            if (i < black.size()) {
                b = black.get(i);
            }

            if (w == null || b == null) {
                break;
            }

            int movingValue = getFigureRating(w) - getFigureRating(b);

            RatingMove value = new RatingMove(movingValue);

            result.add(value);
        }
        return result;
    }

    private int getFigureRating(MovingDescription description) {
        int result = 0;
        if (description.isCastling()) {
            result = 5;
        }

        if (description.getFigureName() == null) {
            return result;
        }
        switch (description.getFigureName()) {
            case PAWN:
                result += PAWN;
                break;
            case BISHOP:
                result += BISHOP;
                break;
            case KNIGHT:
                result += KNIGHT;
                break;
            case ROOK:
                result += ROOK;
                break;
            case QUEEN:
                result += QUEEN;
                break;
            case KING:
                result = 0;
                break;
            default:
                result = 0;
                break;
        }

        if (description.isBeat()){
            result++;
        }

        return result;
    }
}
