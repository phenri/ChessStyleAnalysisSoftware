package ua.edu.vntu.containers;

import ua.edu.vntu.chessboard.Figures;

public interface SaverParty {
    /**
     * Метод для збереження поточного зображення фігур на дошці
     * @param figures фігури що присутні на дошці
     */
    void save(Figures figures);

    void clear();

}
