package ua.edu.vntu.containers;

import org.springframework.stereotype.Repository;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.Figures;
import ua.edu.vntu.descriptions.Position;

import java.util.*;

@Repository("containerMovedFigures")
public class ReadyParty implements SaverParty,ContainerAllMoves {

    private List<List<FigurePosition>> moves = new ArrayList<>();

    private int currentIndex;

    @Override
    public void save(Figures figures){

        List<Figure> whiteFigures = figures.getWhiteFigures()
                , blackFigures = figures.getBlackFigures();

        List<FigurePosition> toSave = new ArrayList<>();

        for(Figure f: whiteFigures){
            FigurePosition figurePosition = new FigurePosition(f.getFigureName(),new Position(f.getPosition()));
            toSave.add(figurePosition);

        }

        moves.add(toSave);
        System.out.println("Білі  = " + toSave);
        toSave = new ArrayList<>();

        for(Figure f: blackFigures){
            FigurePosition figurePosition = new FigurePosition(f.getFigureName(),new Position(f.getPosition()));
            toSave.add(figurePosition);
        }
        System.out.println("Чорні = " + toSave);
        moves.add(toSave);
//        System.out.println(moves);
    }

    @Override
    public Map<Figure, Position> getAction(int index) {
        return null;
    }

    @Override
    public Map<Figure, Position> getNextAction() {
        return null;
    }

    @Override
    public Map<Figure, Position> getPreviosAction() {
        return null;
    }

    @Override
    public Map<Figure, Position> toBegin() {
        return null;
    }

    @Override
    public Map<Figure, Position> toEnd() {
        return null;
    }

    @Override
    public void clear() {
        moves.clear();
    }
}
