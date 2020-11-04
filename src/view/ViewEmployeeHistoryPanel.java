package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ViewEmployeeHistoryPanel extends JPanel {
    private JTable table;
    private JPanel actionPanel;

    private JLabel tableName;

    public JButton addEmployeeHistoryButton;
    public JButton deleteEmployeeHistoryButton;
    public JButton mainMenuButton;
    public JButton editButton;

    private static final int numRows = 10;

    private Vector<Vector<String>> employees;


    public ViewEmployeeHistoryPanel(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        DefaultTableModel tableModel = new DefaultTableModel(colName(), numRows);
        table = new JTable(tableModel);
        //table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        tableName = new JLabel("История сотрудников");
        tableName.setFont(new Font("", Font.BOLD, 20));

        createActionLabel();

        add(tableName);
        add(scrollPane);
        add(Box.createVerticalGlue());
        add(actionPanel);
        add(Box.createVerticalGlue());
        add(Box.createVerticalGlue());

    }

    private Vector<String> colName(){
        Vector<String> colName = new Vector<String>();
        colName.add("ID");
        colName.add("Подразделение");
        colName.add("Должность");
        colName.add("Разряд");
        colName.add("Дата начало работы");
        colName.add("Дата окончания работы");
        return colName;
    }

    public int getEmployeeId(){
        Object id;

        if(table.getSelectedRow() == -1)
            return -1;
        else
        {
            id = table.getValueAt(table.getSelectedRow(), 0);

            if((String) id == "" || table.getSelectedRow() == -1)
                return -1;
            else
                return Integer.parseInt((String) id);
        }
    }

    public void updateTable(Vector<Vector<String>> employees){

        this.employees = employees;
        int size = employees.size();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < employees.get(i).size(); j++) {
                table.setValueAt(employees.get(i).get(j), i, j);
            }
        }

        if(size < numRows)
            for (int i = size; i < numRows; i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    table.setValueAt("", i, j);
                }
            }

    }

    private void createActionLabel(){
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));

        mainMenuButton = new JButton("Главное меню");
        addEmployeeHistoryButton = new JButton("Добавить историю");
        deleteEmployeeHistoryButton = new JButton("Удалить историю");
        editButton = new JButton("Редактировать");

        actionPanel.add(mainMenuButton);
        actionPanel.add(Box.createHorizontalGlue());
        actionPanel.add(addEmployeeHistoryButton);
        actionPanel.add(editButton);
        actionPanel.add(deleteEmployeeHistoryButton);
    }

    public List<String> getEmployeeHistory() {
        List<String> employee = new ArrayList<>();
        if(table.getSelectedRow() != -1 && table.getValueAt(table.getSelectedRow(), 0) != null) {
            for (int i = 0; i < table.getColumnCount(); i++) {
                employee.add((String) table.getValueAt(table.getSelectedRow(), i));
            }
        }
        return employee;
    }
}
