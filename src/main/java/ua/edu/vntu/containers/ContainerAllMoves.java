package ua.edu.vntu.containers;

import java.util.List;

/**
 * Інтерфейс для доступу збережених позицій фігур
 */
public interface ContainerAllMoves {
    /**
     * Метод для доступу до фігур що на дошці за індексом
     * @param index індекс потрібного ходу
     * @return
     */
    List<FigurePosition> getAction(int index);

    List<FigurePosition> getNextAction();

    List<FigurePosition> getPreviosAction();

    List<FigurePosition> toBegin();

    List<FigurePosition> toEnd();
}
