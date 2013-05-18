package ua.vntu.edu.analysis;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.edu.vntu.analysis.Analysible;
import ua.edu.vntu.analysis.MainAnalysis;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.descriptions.Party;
import ua.edu.vntu.descriptions.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 18.05.13
 * Time: 19:11
 */
public class MainAnalisysTest {
    private Party party;

    @Before
    public void init(){
        List<MovingDescription> white = new ArrayList<>();
        List<MovingDescription> black = new ArrayList<>();

        Position p = new Position('e',4);
        MovingDescription md = new MovingDescription(p, FigureName.PAWN);
        white.add(md);

        p = new Position('c',3);
        md = new MovingDescription(p, FigureName.KNIGHT);
        white.add(md);

        p = new Position('e',2);
        md = new MovingDescription(p, FigureName.BISHOP);
        white.add(md);

        p = new Position('e',6);
        md = new MovingDescription(p, FigureName.PAWN);
        black.add(md);

        p = new Position('c',6);
        md = new MovingDescription(p, FigureName.KNIGHT);
        black.add(md);

        p = new Position('c',6);
        md = new MovingDescription(p, FigureName.KNIGHT);
        black.add(md);
    }

    @Test
    public void test(){


        Analysible analysible = new MainAnalysis();
        analysible.analyze(new Party(null,0,null,null));

        Assert.assertTrue(true);
    }
}
