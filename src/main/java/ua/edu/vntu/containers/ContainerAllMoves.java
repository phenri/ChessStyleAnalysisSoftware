package ua.edu.vntu.containers;

import ua.edu.vntu.chessboard.Figures;

/**
 * Інтерфейс для доступу збережених позицій фігур
 */
public interface ContainerAllMoves {
    /**
     * Метод для доступу до фігур що на дошці за індексом
     *
     * @param index індекс потрібного ходу
     * @return
     */
    Figures getAction(int index);

    Figures getNextAction();

    Figures getPreviosAction();

    Figures toBegin();

    Figures toEnd();
}
