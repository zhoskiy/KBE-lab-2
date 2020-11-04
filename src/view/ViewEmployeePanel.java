package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ViewEmployeePanel extends JPanel {
    private JTable table;
    private JPanel actionPanel;

    private JLabel tableName;

    private JButton addEmployeeButton;
    private JButton deleteEmployeeButton;
    private JButton mainMenuButton;
    public JButton editButton;
    public JButton lookYoungEmpButton;
    public JButton lookRetireButton;

    private static final int numRows = 10;

    private Vector<Vector<String>> employees;


    public ViewEmployeePanel(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        DefaultTableModel tableModel = new DefaultTableModel(colName(), numRows);
        table = new JTable(tableModel);
        //table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        tableName = new JLabel("Информация о сотрудниках");
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
        colName.add("ФИО");
        colName.add("Пол");
        colName.add("Семейный статус");
        colName.add("Должность");
        colName.add("Дата рождения");
        return colName;
    }

    public int getEmployeeId(){
        Object id;
        if(table.getSelectedRow() == -1)
            return -1;
        else
        {
            id = table.getValueAt(table.getSelectedRow(), 0);

            if(((String) id).equals("") || table.getSelectedRow() == -1)
                return -1;
            else
                return Integer.parseInt((String) id);
        }
    }

    public List<String> getEmployee(){
        List<String> employee = new ArrayList<>();

        for (int i = 0; i < table.getColumnCount(); i++) {
            employee.add((String) table.getValueAt(table.getSelectedRow(), i));
        }

        return employee;
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
        addEmployeeButton = new JButton("Добавить работника");
        deleteEmployeeButton = new JButton("Удалить работника");
        editButton = new JButton("Редактировать");
        lookYoungEmpButton = new JButton("Поиск сотрудников");
        lookRetireButton = new JButton("Пенсионеры");

        actionPanel.add(mainMenuButton);
        actionPanel.add(Box.createHorizontalGlue());
        actionPanel.add(lookYoungEmpButton);
        actionPanel.add(lookRetireButton);
        actionPanel.add(Box.createHorizontalGlue());
        actionPanel.add(addEmployeeButton);
        actionPanel.add(editButton);
        actionPanel.add(deleteEmployeeButton);
    }

    public JButton getMainMenuButton(){
        return mainMenuButton;
    }

    public JButton getAddEmployeeButton() {
        return addEmployeeButton;
    }

    public JButton getDeleteEmployeeButton(){
        return deleteEmployeeButton;
    }
}
