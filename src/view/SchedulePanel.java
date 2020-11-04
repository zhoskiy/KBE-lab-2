package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class SchedulePanel extends JPanel {
    private JTable table;
    private JPanel namePanel;
    private JPanel actionPanel;
    private JLabel tableName;
    public JButton mainMenuButton;

    private static final int numRows = 10;
    private Vector<Vector<String>> schedule;


    public SchedulePanel(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        DefaultTableModel tableModel = new DefaultTableModel(colName(), numRows);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);


        createActionPanel();
        createNamePanel();

        add(namePanel);
        add(scrollPane);
        add(Box.createVerticalGlue());
        add(actionPanel);
        add(Box.createVerticalGlue());
    }

    private void createNamePanel() {

        namePanel = new JPanel();

        tableName = new JLabel();
        tableName.setFont(new Font("", Font.BOLD, 20));

        namePanel.add(Box.createHorizontalGlue());
        namePanel.add(tableName);
        namePanel.add(Box.createHorizontalGlue());
    }

    private Vector<String> colName(){
        Vector<String> colName = new Vector<String>();
        colName.add("Должность");
        colName.add("Кол-во ставок");
        colName.add("Время начало работы");
        colName.add("Время окончания работы");
        return colName;
    }


    public void updateTable(Vector<Vector<String>> schedule, int departmentNumber){

        this.schedule = schedule;
        tableName.setText("Расписание " + departmentNumber +" подразделения");

        int size = schedule.size();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < schedule.get(i).size(); j++) {
                table.setValueAt(schedule.get(i).get(j), i, j);
            }
        }

        if(size < numRows)
            for (int i = size; i < numRows; i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    table.setValueAt("", i, j);
                }
            }

    }

    private void createActionPanel(){
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));

        mainMenuButton = new JButton("Главное меню");

        actionPanel.add(Box.createHorizontalGlue());
        actionPanel.add(mainMenuButton);
        actionPanel.add(Box.createHorizontalGlue());
    }
}
