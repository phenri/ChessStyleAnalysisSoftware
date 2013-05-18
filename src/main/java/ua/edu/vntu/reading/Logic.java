package ua.edu.vntu.reading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.edu.vntu.chessboard.Cell;
import ua.edu.vntu.chessboard.Cells;
import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.chessboard.figurs.Rook;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.descriptions.Position;

import java.util.List;

@Repository("logic")
public class Logic {

    @Autowired
    @Qualifier("virtualCells")
    private Cells cells;

    public Logic() {
    }

    /**
     * Повертає білу фігуру для ходу
     *
     * @param description обєкт що описує хід
     * @return фігура для наступного ходу
     */
    public Figure getWhiteFigureForMove(MovingDescription description) {
        return getFigureForMove(cells.getFigures().getWhiteFigures(), description);
    }

    /**
     * Повертає чорну фігуру для ходу
     *
     * @param description обєкт що описує хід
     * @return фігура для наступного ходу
     */
    public Figure getBlackFigureForMove(MovingDescription description) {
        return getFigureForMove(cells.getFigures().getBlackFigures(), description);
    }

    /**
     * Метод для видалення білої фігури з поля
     *
     * @param pos позиція з якої потрібно видалити фігуру
     */
    public void removeWhiteFigure(Position pos) {
        removeFigure(pos, cells.getFigures().getWhiteFigures());
    }

    /**
     * Метод для видалення чорної фігури з поля
     *
     * @param pos позиція з якої потрібно видалити фігуру
     */
    public void removeBlackFigure(Position pos) {
        removeFigure(pos, cells.getFigures().getBlackFigures());
    }

    /**
     * Метод для видалення фігури зі списку, якщо фігура побита
     *
     * @param pos      позиція з якої потрібно видалити фігуру
     * @param fromList з якого списку потрбіно видалити фігуру
     */
    private void removeFigure(Position pos, List<Figure> fromList) {
        Cell c = cells.getCellByPosition(pos);
        Figure f = c.getFigure();
        c.reset();
//        System.err.println("\tRemoved " + f);
        fromList.remove(f);
    }

    /**
     * Метод шукає фігуру для якої доступна позиція що описана в @param description, аналізує доступні позиції і повертає фігуру
     *
     * @param figures     список фігур з якого буде братись фігура (білі або чорні)
     * @param description обєкт що описує переміщення. Містить інформацію про хід.
     * @return повертає фігуру для якої доступний хід
     */
    private Figure getFigureForMove(List<Figure> figures, MovingDescription description) {

        FigureName name = description.getFigureName();
        for (Figure f : figures) {

            if (f.getFigureName() != name)
                continue;

            if (name == FigureName.PAWN) {
                boolean state1 = description.isBeat();
                boolean state2 = description.getPosition().getX() == f.getPosition().getX();
                if (!state1 && state2 && f.isAvailablePosition(description.getPosition())) {
                    return f;
                } else {
                    if (f.getPosition().getX() == description.getFromVertical() && f.isAvailablePosition(description.getPosition(), description.isBeat())) {
                        return f;
                    }
                }
            }

            if (name == FigureName.ROOK && f.getFigureName() == FigureName.ROOK && isAvailable(description, f)) {
                return f;
            }

            if (name == FigureName.QUEEN && f.getFigureName() == FigureName.QUEEN)
                return f;
            //TODO: Зробити для ферзя перевірку доступного ходу як для тури по порожньому шляху для того, якщо на дошці знаходисть декілька ферзів

            if (name == FigureName.KING && f.getFigureName() == FigureName.KING)
                return f;

            if (name == FigureName.BISHOP && f.getFigureName() == FigureName.BISHOP && f.isAvailablePosition(description.getPosition()))
                return f;

            if (name == FigureName.KNIGHT && f.getFigureName() == FigureName.KNIGHT) {
                if (f.isAvailablePosition(description.getPosition()) && description.getFromHorizontal() == 0 && description.getFromVertical() == '0') {
                    return f;
                }
                boolean b1 = description.getFromHorizontal() != 0;
                boolean b2 = f.getPosition().getY() == description.getFromHorizontal();

                if (b1 && b2) {
                    return f;
                } else {
                    b1 = description.getFromVertical() != 0;
                    b2 = f.getPosition().getX() == description.getFromVertical();

                    if (b1 && b2) {
                        return f;
                    }
                }

            }

        }
        return null;
    }

    /**
     * Перевіряє чи немає на шляху у фігури що має рухитись до місця призначення, якоїсь іншої фігури
     * що може створити перешкоду
     * <p/>
     * Даний метод тільки для тури і ферзя
     *
     * @param figure Фігура що перевіряється
     * @param to     позиція на яку фігура має стати
     * @return якщо шлях вільний повертає true інакше false
     */
    private boolean isEmptyPath(Figure figure, Position to) {
        if (!figure.isAvailablePosition(to)) {
            return false;
        }

        if (figure.getPosition().getX() == to.getX()) {
            int begin = figure.getPosition().getY() < to.getY() ? figure.getPosition().getY() : to.getY();
            int end = figure.getPosition().getY() > to.getY() ? figure.getPosition().getY() : to.getY();
            if (figure.getPosition().getY() > to.getY()) {
                end--;
            } else {
                begin++;
            }
            for (int i = begin; i <= end; i++) {
                Position p = new Position(to.getX(), i);
                Cell c = cells.getCellByPosition(p);
                if (!c.isEmpty()) {
                    if (c == cells.getCellByPosition(to)) {
                        if (figure.isWhite() == c.getFigure().isWhite()) {
                            return false;
                        } else continue;

                    }
                    return false;
                }
            }

        } else {
            if (figure.getPosition().getY() == to.getY()) {
                char begin = figure.getPosition().getX() < to.getX() ? figure.getPosition().getX() : to.getX();
                char end = figure.getPosition().getX() > to.getX() ? figure.getPosition().getX() : to.getX();
                if (figure.getPosition().getX() > to.getX()) {
                    end--;
                } else {
                    begin++;
                }

                for (char i = begin; i <= end; i++) {
                    Position p = new Position(i, to.getY());
                    Cell c = cells.getCellByPosition(p);
                    if (!c.isEmpty()) {
                        if (c == cells.getCellByPosition(to)) {
                            if (figure.isWhite() == c.getFigure().isWhite()) {
                                return false;
                            } else continue;

                        }
                        return false;
                    }
                }

            }
        }

        return true;
    }

    private boolean isAvailable(MovingDescription description, Figure f) {
        boolean nullVertical = description.getFromVertical() == '0';
        boolean nullHorizontal = description.getFromHorizontal() == 0;

        if (f.isAvailablePosition(description.getPosition()) && !(nullHorizontal && nullVertical)) {
            if (nullVertical && f.getPosition().getY() == description.getFromHorizontal())
                return true;
            else if (nullHorizontal && f.getPosition().getX() == description.getFromVertical()) {
                return true;
            }
        } else if (f.isAvailablePosition(description.getPosition()) && isEmptyPath((Rook) f, description.getPosition())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Logic{" +
                "cells=" + cells +
                '}';
    }
}
