package ua.edu.vntu.gui;

import ua.edu.vntu.containers.ContainerParsedPartiesService;
import ua.edu.vntu.descriptions.Party;
import ua.edu.vntu.descriptions.Tags;
import ua.edu.vntu.handlers.MenuActionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 25.05.13
 * Time: 21:40
 */

public class SelectParty extends JDialog {

    private MenuActionHandler handler;

    private List<Party> parties;

    public SelectParty(MenuActionHandler handler, List<Party> parties) {
        this.parties = parties;
        setTitle("Оберіть партію");
        setModal(true);

        this.handler = handler;
        initComponents();
    }

    private void initComponents() {

        comboBox = new JComboBox();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel6 = new JLabel();
        event = new JLabel();
        location = new JLabel();
        date = new JLabel();
        white = new JLabel();
        black = new JLabel();
        result = new JLabel();
        JLabel jLabel13 = new JLabel();
        JButton ok = new JButton();
        JButton cancel = new JButton();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        fillComboBox();

        jLabel1.setText("Подія:");

        jLabel2.setText("Місце:");

        jLabel3.setText("Дата:");

        jLabel4.setText("Білі:");

        jLabel5.setText("Чорні:");
        jLabel5.setToolTipText("");

        jLabel6.setText("Результат:");

        onChangeSelect();

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel13.setText("Оберіть партію з файлу");

        ok.setText("Вибрати");

        cancel.setText("Відмінити");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel1)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel2)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(jLabel5))))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(location, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(date, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(white, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(black, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(result, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(event)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(93, 93, 93)
                                                .addComponent(jLabel13, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 46, Short.MAX_VALUE)
                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(ok)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancel)
                                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(event))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(location))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(date))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(white))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(black))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(result))
                                .addGap(18, 18, 18)
                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(ok)
                                        .addComponent(cancel))
                                .addGap(24, 24, 24))
        );

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onChangeSelect();
            }
        });

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ok");
                int selectedIndex = comboBox.getSelectedIndex();
                handler.start(selectedIndex);
                dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
    }

    private void fillComboBox() {
        String[] array = new String[parties.size()];

        for (int i = 0; i < parties.size(); i++) {
            array[i] = parties.get(i).getTags().get(Tags.EVENT);
        }

        comboBox.setModel(new DefaultComboBoxModel(array));
    }

    private void onChangeSelect() {

        Party party = parties.get(comboBox.getSelectedIndex());
        Map<String, String> tags = party.getTags();
        event.setText(tags.get(Tags.EVENT));
        location.setText(tags.get(Tags.SITE));
        white.setText(tags.get(Tags.WHITE));
        black.setText(tags.get(Tags.BLACK));
        result.setText(tags.get(Tags.RESULT));
        date.setText(tags.get(Tags.DATE));

    }

    private JComboBox comboBox;
    private JLabel white;
    private JLabel black;
    private JLabel result;
    private JLabel event;
    private JLabel location;
    private JLabel date;
}