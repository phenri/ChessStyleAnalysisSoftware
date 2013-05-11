package ua.edu.vntu.gui.table;

import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.descriptions.Party;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 11.05.13
 * Time: 15:56
 */
public interface Table {
    /**
     * Метод що записує в таблицю у графічному інтерфейсі нотацію партії
     * @param party
     */
    public void addProgress(Party party);
    public void clear();
}
