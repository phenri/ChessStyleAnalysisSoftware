package ua.edu.vntu.gui;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 04.11.12
 * Time: 12:53
 * To change this template use File | Settings | File Templates.
 */
public interface Constants {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;
    public static final int CELL_SIZE = 60;
    public static final Color LIGHT = new Color(255,205,158);
    public static final Color DARK  = new Color(209,139,70);

    public static final int COMMON_MOVE = 1;
    public static final int LONG_CASTLING = 2;
    public static final int SHORT_CASTLING = 4;
}
