package ua.edu.vntu.descriptions;

import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;

import java.util.ArrayList;

public class ContainerFigure{
    private ArrayList<Figure> white,black;

    public ContainerFigure() {
    }

    public void setWhite(ArrayList<Figure> white) {
        this.white = white;
    }

    public void setBlack(ArrayList<Figure> black) {
        this.black = black;
    }

    public Figure getWhiteFigureForMove(MovingDescription description){
        return getFigureForMove(white,description);
    }

    public Figure getBlackFigureForMove(MovingDescription description){
        return getFigureForMove(black,description);
    }

    public void removeWhiteFigure(Figure figure){
        removeFigure(figure,white);
    }

    public void removeBlackFigure(Figure figure){
        removeFigure(figure,black);
    }

    private void removeFigure(Figure figure, ArrayList<Figure> fromList){
        fromList.remove(figure);
    }


    private Figure getFigureForMove(ArrayList<Figure> figures, MovingDescription description){

        if (description.getFigureName() == FigureName.PAWN){
            for (Figure f : figures) {
                if(f.getFigureName() != FigureName.PAWN)
                    continue;

                boolean state1 = !description.isBeat();
                boolean state2 = description.getPosition().getX() == f.getPosition().getX();

                if(state1 && state2)
                    return f;
            }

        }

        System.out.println("Хід фігурою "+ description.getFigureName()+" на позицію " + description.getPosition());

        for (Figure f : figures) {
            System.out.println("Фігура в контейнері " + f);
            if (f.getFigureName() == description.getFigureName() && f.isAvailablePosition(description.getPosition())) {
                System.out.println("Контейнер повертає " +f);
                return f;
            }
        }
        System.out.println("Немає фігури для ходу." );

        return null;
    }

    private void doCastling(Castling castling, boolean isWhite){
        Figure king = null,
               rook = null;
        if (isWhite){
            for(Figure f: white){
                if (f.getFigureName() == FigureName.KING){
                    king = f;
                }
                boolean b1 = castling.isLong();
                boolean b2 = (f.getPosition().getX() == 'a' && f.getFigureName() == FigureName.ROOK);
                boolean b3 = (f.getPosition().getX() == 'h' && f.getFigureName() == FigureName.ROOK);
                if (b1 && b2){
                    rook = f;
                }
                if (!b1 && b3){
                    rook = f;
                }
                System.out.println("Король для рокіровки: "+king+", тура для рокіровки: "+rook);
                break;
            }
        }
        else {

            for(Figure f: black){
                if (f.getFigureName() == FigureName.KING){
                    king = f;
                }
                boolean b1 = castling.isLong();
                boolean b2 = (f.getPosition().getX() == 'a' && f.getFigureName() == FigureName.ROOK);
                boolean b3 = (f.getPosition().getX() == 'h' && f.getFigureName() == FigureName.ROOK);
                if (b1 && b2){
                    rook = f;
                }
                if (!b1 && b3){
                    rook = f;
                }
                System.out.println("Король для рокіровки: "+king+", тура для рокіровки: "+rook);
                break;
            }

        }

    }
}
