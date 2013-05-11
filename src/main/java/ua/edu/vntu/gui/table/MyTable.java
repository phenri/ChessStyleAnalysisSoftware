package ua.edu.vntu.gui.table;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 11.05.13
 * Time: 15:11
 */


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyTable extends JPanel {

    private static final MyTable INSTANCE = new MyTable();

    public static MyTable getInstance() {
        return INSTANCE;
    }

    DefaultTableModel model;

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

    public void clear() {
        int count = model.getRowCount();
        for (int i = count - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
}