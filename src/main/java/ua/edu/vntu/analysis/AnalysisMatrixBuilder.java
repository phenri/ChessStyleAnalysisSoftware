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
        List<RatingMove> ratingMoves = new ArrayList<>();

        for (Figures i : figuresList) {
            int power = getStatePower(i, isWhite);
            System.out.println("Power " + (isWhite ? "white: " : "black: ") + power);
        }
        return null;
    }

    /**
     * Метод підраховує суму очків, за фігури що містяться на дошці за відповідними матрицями.
     * Очки підраховуються з заданих константних матриць для білих, для чорних є метод для конвертації цих матриць
     *
     * @param figures положення всіх фігур на дошці
     * @param isWhite які матриці очків використовувати, якщо true для білих, якщо false для чорних
     * @return повертає суму очків для всіх фігур
     */
    private int getStatePower(Figures figures, boolean isWhite) {
        int result = 0;

        List<Figure> target = isWhite ? figures.getWhiteFigures() : figures.getBlackFigures();

        boolean beginForKing = true;
        int i = 0;
        for (Figure f : target) {
            if (i > target.size() / 3) {
                beginForKing = false;
            }

            switch (f.getFigureName()) {
                case KING:
                    result += beginForKing ?
                            getPositionPower(f.getPosition(), isWhite ? matrixForKingBegin : convertMatrixForBlack(matrixForKingBegin)) :
                            getPositionPower(f.getPosition(), isWhite ? matrixForKingEnd : convertMatrixForBlack(matrixForKingEnd));
                    break;

                case ROOK:
                    result += getBonusForRook(figures, f);
                    break;
                // Бонус для ферзя 1.5 бонуса тури, плюс третина бонуса слона
                case QUEEN:
                    result += 1.5 * getBonusForRook(figures, f) + getPositionPower(f.getPosition(), (isWhite ? matrixForBishop : convertMatrixForBlack(matrixForBishop))) / 3;
                    break;
                default:
                    result += getFigurePower(f);
            }

            i++;
        }

        return result;
    }

    /**
     * Обчислення очків за положення фігури фігури
     * Значення береться з відповідних константних матриць, відповідно до положення фігури
     *
     * @param f фігура для якої потрібно обчислити очки
     * @return набраних кількість очків
     */
    private int getFigurePower(Figure f) {

        switch (f.getFigureName()) {
            case PAWN:
                return getPositionPower(f.getPosition(), f.isWhite() ? matrixFoPawn : convertMatrixForBlack(matrixFoPawn));
            case BISHOP:
                return getPositionPower(f.getPosition(), f.isWhite() ? matrixForBishop : convertMatrixForBlack(matrixForBishop));
            case KNIGHT:
                return getPositionPower(f.getPosition(), f.isWhite() ? matrixFoKnight : convertMatrixForBlack(matrixFoKnight));
            default:
                return Integer.MIN_VALUE;
        }
    }

    /**
     * Метод ставить у відповідність положення фігури на дошці у індекси матриці
     *
     * @param p      положення для якого потрібно визначити кількість очок
     * @param matrix матриця з якої брати значення
     * @return повертає кількість очків з матриці відповідно до положення фігури на дошці
     */
    public int getPositionPower(Position p, int[][] matrix) {
        int i = 7 - p.getY() + 1;
        int j = indexAccordance.get(p.getX());

        return matrix[i][j];
    }

    /**
     * Метод для конвертації матриці очок для чорних фігур. Для білих значення задані константами, для чорних будуть
     * ті самі значення тільки рядки потрібно поміняти місцями
     *
     * @param input вхідна матриця
     * @return повертає конвертовані рядки матриці
     */
    private int[][] convertMatrixForBlack(int[][] input) {
        int[][] output = new int[8][8];

        for (int i = 0, j = input.length - 1; i < input.length; i++, j--) {
            output[j] = input[i];
        }

        return output;
    }

    /**
     * Метод для визначення бонусу для тури, оскільки для тури немає заданих константної матриці.
     * Якщо тура займає пустий рядок або стовпць бонус більший, якщо є своя пишка в ряді бонус менший
     *
     * @param figures положення всіх фігур
     * @param rook    тура для якої потрібно порахувати бонус
     * @return кількість набраних очків для тури.
     */
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

    /**
     * Метод перевіряє чи містить вказана горизонталь фігуру зі своєї команди
     * Використовується для підрахунку бонусу для тури і ферзя
     *
     * @param p       позиція для якої потрібно вирахувати
     * @param figures положення всіх фігур
     * @return true якщо є фігури зі своєї команди у вертикалі або горизонталі, інакше false
     */
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

    /**
     * Перевіряє наявність власної пишки у вертикалі
     *
     * @param p       положення для якого потрібно вирахувати
     * @param figures положення всіх фігур
     * @return true якщо є власна пишка, інакше false
     */
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

    public void print(int[][] data) {
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
