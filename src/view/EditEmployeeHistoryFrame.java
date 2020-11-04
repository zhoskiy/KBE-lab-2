package view;

import control.MyConnection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditEmployeeHistoryFrame extends JFrame {
    private MyConnection myConnection;

    private JPanel labelPanel;
    private JPanel fieldPanel;

    private JTextField idField;
    private JTextField departmentField;
    private JTextField postField;
    private JTextField rank;
    private JTextField startDateField;
    private JTextField endDateField;

    private List<String> tableData;

    public JButton addButton;

    public EditEmployeeHistoryFrame(MyConnection connection){

        super("Редактировать информацию сотрудника");
        setSize(1280, 160);

        this.myConnection = connection;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        addButton = new JButton("OK");

        createLabelPanel();
        createFieldPanel();

        panel.add(Box.createVerticalGlue());
        panel.add(labelPanel);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalGlue());
        panel.add(addButton);

        add(panel);
    }

    private void createFieldPanel() {
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));

        final int WIDTH = 300;
        final int HEIGHT = 20;

        idField = new JTextField();
        idField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        departmentField = new JTextField();
        departmentField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        postField = new JTextField();
        postField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        rank = new JTextField();
        rank.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        startDateField = new JTextField();
        startDateField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        endDateField = new JTextField();
        endDateField.setMaximumSize(new Dimension(WIDTH, HEIGHT));


        fieldPanel.add(departmentField);
        fieldPanel.add(postField);
        fieldPanel.add(rank);
        fieldPanel.add(startDateField);
        fieldPanel.add(endDateField);
    }

    private void createLabelPanel() {
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));

        JLabel department = new JLabel("Подразделение");
        JLabel post = new JLabel("Должность");
        JLabel rank = new JLabel("Разряд");
        JLabel startDate = new JLabel("Дата начало работы");
        JLabel endDate = new JLabel("Дата окончания работы");


        labelPanel.add(department);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(post);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(rank);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(startDate);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(endDate);
        labelPanel.add(Box.createHorizontalGlue());
    }


    public void updateFields (List<String> tableData)  {
        if(tableData.isEmpty())
            return;

        this.tableData = tableData;

        clearFields();

        departmentField.setText(tableData.get(1));
        postField.setText(tableData.get(2));
        rank.setText(tableData.get(3));
        startDateField.setText(tableData.get(4));
        endDateField.setText(tableData.get(5));

    }

    public List<String> getNewInfoEmployeeHistory(){
        List<String> employee = new ArrayList<>();

        if(departmentField.getText().equals("") || postField.getText().equals("") ||
                rank.getText().equals("") || startDateField.getText().equals("") ||
                endDateField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Не все данные введены");
            return employee;
        }


        String fullName = departmentField.getText();
        String gender = postField.getText();
        String familyStatus = rank.getText();
        String post = startDateField.getText();
        String birthDayText = endDateField.getText();

        employee.add(tableData.get(0));
        employee.add(fullName);
        employee.add(gender);
        employee.add(familyStatus);
        employee.add(post);
        employee.add(birthDayText);


        return employee;
    }

    private void clearFields(){
        departmentField.setText("");
        postField.setText("");
        rank.setText("");
        startDateField.setText("");
        endDateField.setText("");
    }
}
