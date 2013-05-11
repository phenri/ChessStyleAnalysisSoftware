package ua.edu.vntu.gui;

import ua.edu.vntu.gui.table.MyTable;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vyacheslav
 * Date: 20.01.13
 * Time: 13:03
 */
public class FieldCommand extends JPanel {

    private final int BUTTON_X = 0;
    private final int BUTTON_Y = 150;

    public FieldCommand(){
        super();
        setLayout(null);
        setBackground(Color.red);
        MyTable content = MyTable.getInstance();

        content.setBounds(0,0,150,500);
//        repaint();

        int x = 0,
                y =  510;

        JButton [] jButtons = new JButton[5];
        for(int i = 0; i < jButtons.length; i++){
            jButtons[i] = new JButton(i+"");
            jButtons[i].setBounds(x,y,30,30);
            add(jButtons[i]);
            x += 30;

        }

        add(content);
        setBounds(600,30,150,540);
    }
}
