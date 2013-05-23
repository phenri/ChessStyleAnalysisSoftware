package ua.edu.vntu.analysis;

import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.chessboard.Figures;
import ua.edu.vntu.descriptions.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 19.05.13
 * Time: 13:06
 */
public class AnalysisMatrixBuilder {

    private static Map<Character, Integer> indexAccordance;

    static {
        indexAccordance = new HashMap<>();
        indexAccordance.put('a', 0);
        indexAccordance.put('b', 1);
        indexAccordance.put('c', 2);
        indexAccordance.put('d', 3);
        indexAccordance.put('e', 4);
        indexAccordance.put('f', 5);
        indexAccordance.put('g', 6);
        indexAccordance.put('h', 7);
    }

    public List<RatingMove> analyzeFiguresPosition(List<Figures> figuresList, boolean isWhite) {
//        for (Figures i : figuresList) {
//            analyzePosition(i, isWhite);
//        }
        analyzePosition(figuresList.get(0), isWhite);
        return null;
    }

    private void analyzePosition(Figures positions, boolean isWhite) {
        buildMatrix(positions, isWhite);
    }

    /**
     * Для побудови матриці
     *
     * @param inFigures
     * @param isWhite
     * @return
     */
    private int[][] buildMatrix(Figures inFigures, boolean isWhite) {

        int[][] result = new int[8][8];

        List<Figure> target;

        /**
         * Вибір для якого кольору будувати матрицю
         */

        if (isWhite) {
            target = inFigures.getWhiteFigures();

            for (int i = 0; i < 8; i++) {
                result[0][i] += 8;
                result[1][i] += 7;
                result[2][i] += 4;
                result[3][i] += 4;
                result[4][i] += 3;
                result[5][i] += 3;
                result[6][i] += 1;
                result[7][i] += 1;

            }

        } else {
            target = inFigures.getBlackFigures();

            for (int i = 0; i < 8; i++) {
                result[7][i] += 7;
            }
        }

        Figure king = null;

        for (Figure f : target) {
            if (f.getFigureName() == FigureName.KING) {
                king = f;
                break;
            }
        }

        System.out.println(king);
        incValue(10, king.getPosition(), result);

        print(result);

        return null;
    }

    private int getBonusForRook(Figures figures, Figure rook) {

        boolean isWhite = rook.isWhite();

        int bonus = 0;

        Position p = rook.getPosition();

        boolean verticalCenter = p.getY() < 3 && p.getY() > 4;
        boolean horizontalCenter = p.getX() < 'c' && p.getX() > 'f';

        if (verticalCenter && horizontalCenter) {
            bonus += 18;
        }

        if (verticalCenter && !horizontalCenter) {
            bonus += 10;
        }

        if (!verticalCenter && horizontalCenter) {
            bonus += 10;
        }

        if (!verticalCenter && !horizontalCenter) {
            bonus += 4;
        }

        List<Figure> list = rook.isWhite() ? figures.getWhiteFigures() : figures.getBlackFigures();

        if (!isPawnInVertical(rook.getPosition(), list)) {
            bonus += 6;
        }

        if (!positionContainsOwnFigures(rook.getPosition(), list)) {
            bonus += 4;
        }


        ArrayList<Figure> allFigures = new ArrayList<>(figures.getBlackFigures());

        allFigures.addAll(figures.getWhiteFigures());

        return bonus;
    }

    private boolean positionContainsOwnFigures(Position p, List<Figure> figures) {
        for (Figure f : figures) {
            boolean vertical = p.getX() == f.getX();
            boolean horizontal = p.getY() == f.getY();

            if (!vertical && !horizontal) {
                return true;
            }
        }
        return false;
    }

    private boolean isPawnInVertical(Position p, List<Figure> figures) {
        for (Figure f : figures) {
            if (f.getPosition().getX() == p.getX() && f.getFigureName() == FigureName.PAWN) {
                return true;
            }
        }
        return false;
    }

    public static final int[][] matrixFoPawn = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {4, 4, 4, 0, 0, 4, 4, 4},
            {6, 8, 2, 10, 12, 2, 8, 6},
            {6, 8, 12, 16, 18, 12, 8, 6},
            {8, 12, 16, 24, 26, 16, 12, 8},
            {12, 15, 24, 32, 34, 24, 16, 12},
            {12, 16, 24, 32, 34, 24, 15, 12},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static final int[][] matrixFoKnight = {
            {0, 4, 8, 10, 10, 8, 4, 0},
            {4, 8, 16, 20, 20, 16, 8, 4},
            {8, 16, 24, 28, 28, 24, 16, 8},
            {10, 20, 28, 32, 32, 28, 20, 10},
            {10, 20, 28, 32, 32, 28, 20, 10},
            {8, 16, 24, 28, 28, 24, 16, 8},
            {4, 8, 16, 20, 20, 16, 8, 4},
            {0, 4, 8, 10, 10, 8, 4, 0},
    };

    public static final int[][] matrixForBishop = {
            {14, 14, 14, 14, 14, 14, 14, 14},
            {14, 22, 18, 18, 18, 18, 22, 14},
            {14, 18, 22, 22, 22, 22, 18, 14},
            {14, 18, 22, 22, 22, 22, 18, 14},
            {14, 18, 22, 22, 22, 22, 18, 14},
            {14, 18, 22, 22, 22, 22, 18, 14},
            {14, 22, 18, 18, 18, 18, 22, 14},
            {14, 14, 14, 14, 14, 14, 14, 14}
    };

    public static final int[][] matrixForKingEnd = {
            {0, 4, 8, 12, 12, 8, 4, 0},
            {4, 16, 20, 24, 24, 20, 16, 4},
            {8, 20, 28, 32, 32, 28, 20, 8},
            {12, 24, 32, 36, 36, 32, 24, 12},
            {12, 24, 32, 36, 36, 32, 24, 12},
            {8, 20, 28, 32, 32, 28, 20, 8},
            {4, 16, 20, 24, 24, 20, 16, 4},
            {0, 4, 8, 12, 12, 8, 4, 0},
    };

    public static final int[][] matrixForKingBegin = {
            {0, 0, -4, -10, -10, -4, 0, 0},
            {-4, -4, -8, -12, -12, -8, -4, -4},
            {-12, -16, -20, -20, -20, -20, -16, -12},
            {-16, -20, -24, -24, -24, -24, -20, -16},
            {-16, -20, -24, -24, -24, -24, -20, -16},
            {-12, -16, -20, -20, -20, -20, -16, -12},
            {-4, -4, -8, -12, -12, -8, -4, -4},
            {0, 0, -4, -10, -10, -4, 0, 0},
    };

    private void incValue(int value, Position p, int[][] target) {
        target[p.getY() - 1][indexAccordance.get(p.getX())] += value;
    }

    private void print(int[][] data) {
        System.out.println("__________________________________");
        for (int[] i : data) {
            for (int j : i) {
                System.out.print(" | " + j);
            }
            System.out.print(" | \n");
        }
        System.out.println("__________________________________");
    }

}
