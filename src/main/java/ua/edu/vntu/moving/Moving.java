package ua.edu.vntu.moving;

import ua.edu.vntu.chessboard.Cell;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.descriptions.Position;

public class Moving implements MoveFigure{
    private Cells cells;

    public Moving(){

    }

    @Override
    public boolean move(Figure figure, MovingDescription description) {

        Position pos = description.getPosition();

        if(description.isCastling())
            System.out.println("Рокірока");

        Cell cell = cells.getCellByPosition(pos);

        if (figure.getFigureName() == FigureName.PAWN){
            if(figure.isAvailablePosition(pos,description.isBeat())){
                figure.getParent().reset();
                cell.addFigure(figure);
                return true;
            }
        }
        else {
            figure.getParent().reset();
            cell.addFigure(figure);
            return true;

        }

        return false;
    }
    public void setCells(Cells cells){
        this.cells = cells;
    }
}
