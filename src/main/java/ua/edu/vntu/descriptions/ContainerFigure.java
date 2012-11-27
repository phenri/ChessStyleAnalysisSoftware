package ua.edu.vntu.descriptions;

import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.chessboard.figurs.Rook;

import java.util.ArrayList;

public class ContainerFigure{
    private ArrayList<Figure> white,black;

    private Cells board;

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

    public static int countMove = 0;

    private Figure getFigureForMove(ArrayList<Figure> figures, MovingDescription description){

        FigureName name = description.getFigureName();
        for (Figure f: figures){

            if (f.getFigureName() != name)
                continue;

            if (name == FigureName.PAWN){
                boolean state1 = description.isBeat();
                boolean state2 = description.getPosition().getX() == f.getPosition().getX();
                if(!state1 && state2) {
                    return f;
                }
                else {
                    if (f.getPosition().getX() == description.getFromVertical()) {
                        return f;
                    }
                }
            }

//            if (name == FigureName.ROOK && f.getFigureName() == FigureName.ROOK){
//                System.out.println("Хід турою на позицію "+description );
//                if (f.isAvailablePosition(description.getPosition()) && isEmptyPath((Rook)f,description.getPosition())){
//                    return f;
//                }
//                else continue;
//            }

            if (name == FigureName.QUEEN && f.getFigureName() == FigureName.QUEEN)
                return f;

            if (name == FigureName.KING && f.getFigureName() == FigureName.KING)
                return f;

            if (name == FigureName.BISHOP && f.getFigureName() == FigureName.BISHOP && f.isAvailablePosition(description.getPosition()))
                return f;

            if (name == FigureName.KNIGHT && f.getFigureName() == FigureName.KNIGHT){
                if (f.isAvailablePosition(description.getPosition()) && description.getFromHorizontal() == 0 && description.getFromVertical() == '0'){
                    return f;
                }
                boolean b1 = description.getFromHorizontal() != 0;
                boolean b2 = f.getPosition().getY() == description.getFromHorizontal();

                if (b1 && b2){
                    return f;
                }
                else{
                    b1 = description.getFromVertical() != 0;
                    b2 = f.getPosition().getX() == description.getFromVertical();

                    if (b1 && b2){
                        return f;
                    }
                }


            }


    //            System.out.println("Фігура в контейнері " + f);

//                if (description.getFromHorizontal() != 0 && description.getFromHorizontal() == f.getY()){
//                        System.out.println("fromHorizontal != 0 return " +f);
//                        return f;
//                }
//
//                if (description.getFromVertical() != '0' && description.getFromVertical() == f.getX()){
//                        System.out.println("fromVertical != 0 return  " +f);
//                        return f;
//                }
//
//                if (f.isAvailablePosition(description.getPosition())) {
//                    System.out.println("Контейнер повертає " +f);
//                    return f;
//                }
            }
            System.err.println("Немає фігури для ходу." );
//        }
        return null;
    }

    public boolean isEmptyPath(Rook rook, Position to){
        boolean result = false;
        if (!rook.isAvailablePosition(to)){
            System.err.println("не доступний хід");
            return false;
        }


        if(rook.getPosition().getX() == to.getX()){
            int begin = rook.getPosition().getY() < to.getY() ? rook.getPosition().getY() : to.getY();
            System.out.println("початок координат " + begin);

            int end = rook.getPosition().getY() > to.getY() ? rook.getPosition().getY() : to.getY();
            System.out.println("кінець координат " + end);

            for(int i = begin; i <= end; i++){
                Position p = new Position(to.getX(),i);
                System.out.print("статус позиції " +p + ": "+ board.getCellByPosition(p).isEmpty());
                if (!board.getCellByPosition(p).isEmpty()){

                    return false;
                }

            }

        }
        else {
            if(rook.getPosition().getY() == to.getY()){
                char begin = rook.getPosition().getX() < to.getX() ? rook.getPosition().getX() : to.getX();
                System.out.println("початок координат " + begin);

                char end = rook.getPosition().getX() > to.getX() ? rook.getPosition().getX() : to.getX();
                System.out.println("кінець координат " + end);

                for(char i = begin; i <= end; i++){
                    Position p = new Position(i,to.getY());
                    System.out.print("статус позиції " +p + ": "+ board.getCellByPosition(p).isEmpty());
                    if (!board.getCellByPosition(p).isEmpty()){
                        return false;
                    }

                }

            }

        }

        return true;
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

    public void setCells(Cells cells) {
        this.board = cells;
    }
}
