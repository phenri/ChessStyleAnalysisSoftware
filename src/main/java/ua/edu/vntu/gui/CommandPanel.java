package ua.edu.vntu.gui;

import ua.edu.vntu.gui.table.MyTable;
import ua.edu.vntu.handlers.CommandActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Vyacheslav
 * Date: 20.01.13
 * Time: 13:03
 */
public class CommandPanel extends JPanel {

    private boolean isPlay = true;
    private CommandActions commandActions;

    public CommandPanel() {
        super();
        setLayout(null);
        MyTable content = MyTable.getInstance();

        content.setBounds(0, 0, 150, 500);

        int x = 0, y = 510;

        final JButton[] jButtons = new JButton[5];
        for (int i = 0; i < jButtons.length; i++) {
            jButtons[i] = new JButton();
            jButtons[i].setBounds(x, y, 30, 30);
            add(jButtons[i]);
            x += 30;
        }

        Icon icon = new ImageIcon("icons/buttons/toEnd.png");
        jButtons[0].setIcon(icon);
        jButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandActions.toBegin();
            }
        });

        icon = new ImageIcon("icons/buttons/previous.png");
        jButtons[1].setIcon(icon);
        jButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandActions.previous();
            }
        });

        icon = new ImageIcon("icons/buttons/play.png");
        jButtons[2].setIcon(icon);
        jButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPlay) {
                    Icon icon = new ImageIcon("icons/buttons/pause.png");
                    jButtons[2].setIcon(icon);
                    isPlay = false;
                    commandActions.play();
                } else {
                    Icon icon = new ImageIcon("icons/buttons/play.png");
                    jButtons[2].setIcon(icon);
                    isPlay = true;
                    commandActions.pause();
                }

            }
        });

        icon = new ImageIcon("icons/buttons/next.png");
        jButtons[3].setIcon(icon);
        jButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandActions.next();
            }
        });

        icon = new ImageIcon("icons/buttons/toBegin.png");
        jButtons[4].setIcon(icon);
        jButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandActions.toEnd();
            }
        });

        add(content);
        setBounds(600, 30, 150, 540);
    }

    public void setCommandActions(CommandActions commandActions) {
        this.commandActions = commandActions;
    }
}
