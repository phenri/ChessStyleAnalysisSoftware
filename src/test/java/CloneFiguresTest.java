import static junit.framework.Assert.*;
import org.junit.Test;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.Figures;
import ua.edu.vntu.chessboard.figurs.Pawn;
import ua.edu.vntu.descriptions.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 12.05.13
 * Time: 15:44
 */

public class CloneFiguresTest {

    @Test
    public void test() throws CloneNotSupportedException {
        List<Figure> white = new ArrayList<>();
        List<Figure> black = new ArrayList<>();

        Figure f  = new Pawn(false);
        f.setPosition(new Position('a',6));
        black.add(f);

        f = new Pawn(true);
        f.setPosition(new Position('a',2));
        white.add(f);


        Figures figures = new Figures(white,black);

        System.out.println(figures.getWhiteFigures()+ "\n" + figures.getBlackFigures());

        Figures clone = (Figures) figures.clone();

        System.out.println(clone.getWhiteFigures()+"\n"+clone.getBlackFigures());

        assertFalse(figures == figures.clone());
        assertTrue(figures.equals(figures.clone()));

    }
}
