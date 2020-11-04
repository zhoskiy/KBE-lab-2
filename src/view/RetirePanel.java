package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RetirePanel extends JPanel {
    private JTable table;
    private JPanel namePanel;
    private JPanel actionPanel;

    private JLabel tableName;


    public JButton backButton;

    private static final int numRows = 10;

    private Vector<Vector<String>> retires;


    public RetirePanel(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        DefaultTableModel tableModel = new DefaultTableModel(colName(), numRows);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        tableName = new JLabel("Пенсионеры");
        tableName.setFont(new Font("", Font.BOLD, 20));

        createActionLabel();
        createNamePanel();

        add(namePanel);
        add(scrollPane);
        add(Box.createVerticalGlue());
        add(actionPanel);
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());
    }

    private Vector<String> colName(){
        Vector<String> colName = new Vector<String>();
        colName.add("ФИО");
        colName.add("Дата рождения");
        return colName;
    }

    public void updateTable(Vector<Vector<String>> retires){

        this.retires = retires;
        int size = retires.size();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < retires.get(i).size(); j++) {
                table.setValueAt(retires.get(i).get(j), i, j);
            }
        }

        if(size < numRows)
            for (int i = size; i < numRows; i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    table.setValueAt("", i, j);
                }
            }

    }

    private void createNamePanel() {

        namePanel = new JPanel();

        tableName = new JLabel();
        tableName.setFont(new Font("Пенсионеры", Font.BOLD, 20));

        namePanel.add(Box.createHorizontalGlue());
        namePanel.add(tableName);
        namePanel.add(Box.createHorizontalGlue());
    }

    private void createActionLabel(){
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));

        backButton = new JButton("Назад");

        actionPanel.add(Box.createHorizontalGlue());
        actionPanel.add(backButton);
        actionPanel.add(Box.createHorizontalGlue());

    }
}
