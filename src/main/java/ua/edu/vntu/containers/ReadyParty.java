package ua.edu.vntu.containers;

import org.springframework.stereotype.Repository;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.Figures;
import ua.edu.vntu.descriptions.Position;

import java.util.ArrayList;
import java.util.List;

@Repository("containerMovedFigures")
public class ReadyParty implements SaverParty,ContainerAllMoves {

    private List<List<FigurePosition>> moves = new ArrayList<>();

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
//        System.out.println("Чорні = " + toSave);
        moves.add(toSave);
//        System.out.println(moves);
    }

    @Override
    public List<FigurePosition> getAction(int index) {
        return moves.get(index);
    }

    @Override
    public List<FigurePosition> getNextAction() {
        return moves.listIterator().next();
    }

    @Override
    public List<FigurePosition> getPreviosAction() {
        return moves.listIterator().previous();
    }

    @Override
    public List<FigurePosition> toBegin() {
        return moves.get(0);
    }

    @Override
    public List<FigurePosition> toEnd() {
        return moves.get(moves.size()-1);
    }

    @Override
    public void clear() {
        moves.clear();
    }
}
