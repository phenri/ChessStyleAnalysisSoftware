package ua.edu.vntu.containers;

import ua.edu.vntu.chessboard.Figure;
import ua.edu.vntu.descriptions.Position;

import java.util.Map;

/**
 * Інтерфейс для доступу збережених позицій фігур
 */
public interface ContainerAllMoves {
    /**
     * Метод для доступу до фігур що на дошці за індексом
     * @param index індекс потрібного ходу
     * @return
     */
    Map<Figure,Position> getAction(int index);

    Map<Figure,Position> getNextAction();

    Map<Figure,Position> getPreviosAction();

    Map<Figure,Position> toBegin();

    Map<Figure,Position> toEnd();
}
