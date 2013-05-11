package ua.edu.vntu.gui.table;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 11.05.13
 * Time: 15:11
 */


import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.descriptions.Party;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

public class MyTable extends JPanel implements Table{

    private static final MyTable INSTANCE = new MyTable();

    public static MyTable getInstance() {
        return INSTANCE;
    }

    private DefaultTableModel model;

    private MyTable() {
        super(new GridLayout(1, 0));

        final String[] columnNames = {"№",
                "Білі",
                "Чорні"
        };

        model = new DefaultTableModel();


        for (String s : columnNames) {
            model.addColumn(s);
        }

        final JTable table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        table.setEnabled(false);
        //Add the scroll pane to this panel.
        add(scrollPane);
    }

//    public void addTerminalToRow(Terminal terminal) {
//        if (terminal == null)
//            return;
//
//        String tTime = "";
//        String dTime = "";
//
//        int id = terminal.getId();
//        int number = terminal.getTrainNumber();
//        String station = terminal.getDestinationStation();
//        Date date = terminal.getDepartureTime();
//        Date travelTime = terminal.getTravelTime();
//        tTime += date.getHours();
//        tTime += ":" + date.getMinutes();
//        tTime += ":" + date.getSeconds();
//
//        dTime += travelTime.getHours();
//        dTime += ":" + travelTime.getMinutes();
//        dTime += ":" + travelTime.getSeconds();
//
//        int freePlaces = terminal.getFreePlace();
//        int ticketPrice = terminal.getPriceTicket();
//
//        Object[] target = {id, number, station, tTime, dTime, freePlaces, ticketPrice};
//        model.addRow(target);
//    }


    @Override
    public void addProgress(Party party) {
        System.out.println(party);
        List<MovingDescription> white = party.getWhiteMoves();
        List<MovingDescription> black = party.getBlackMoves();

        int length = white.size() < black.size() ? white.size() : black.size();

        for (int i = 0; i < length; i++){
            MovingDescription md1 = i < white.size() ? white.get(i): null;
            MovingDescription md2 = i < black.size() ? black.get(i): null;

            String s1 = md1 != null ? md1.getTextNotation(): "";
            String s2 = md2 != null ? md2.getTextNotation(): "";

            Object [] target = {(i + 1),s1,s2};
            model.addRow(target);
        }
    }

    public void clear() {
        int count = model.getRowCount();
        for (int i = count - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
}