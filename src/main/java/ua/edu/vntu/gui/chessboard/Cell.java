package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.gui.FormConstants;

import javax.swing.*;
import java.security.PublicKey;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 04.11.12
 * Time: 13:29
 */
public class Cell extends JPanel implements FormConstants {
    private char letter;
    private byte number;

    public Cell(){
        super(true);
        setLayout(null);

    }
    public Cell(char letter, byte number){
        super(true);
        this.letter = letter;
        this.number = number;

    }
    public boolean isEmpty(){
        return true;
    }
}
