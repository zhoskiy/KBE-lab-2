package view;

import control.MyConnection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddEmployeeHistoryFrame extends JFrame{
    private MyConnection myConnection;

    private JPanel labelPanel;
    private JPanel fieldPanel;

    private JTextField idField;
    private JTextField departmentField;
    private JTextField postField;
    private JTextField rankField;
    private JTextField startDateField;
    private JTextField endDateField;

    public JButton addButton;

    public AddEmployeeHistoryFrame(MyConnection connection){

        super("Добавить историю сотрудника");
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
        rankField = new JTextField();
        rankField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        startDateField = new JTextField();
        startDateField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        endDateField = new JTextField();
        endDateField.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        fieldPanel.add(idField);
        fieldPanel.add(departmentField);
        fieldPanel.add(postField);
        fieldPanel.add(rankField);
        fieldPanel.add(startDateField);
        fieldPanel.add(endDateField);
    }

    private void createLabelPanel() {
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));

        JLabel id = new JLabel("Id");
        JLabel department = new JLabel("Подразделение");
        JLabel post = new JLabel("Должность");
        JLabel rank = new JLabel("Разряд");
        JLabel startDate = new JLabel("Начало работы");
        JLabel endDate = new JLabel("Конец работы");

        labelPanel.add(id);
        labelPanel.add(Box.createHorizontalGlue());
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


    public java.util.List<String> getNewDataEmployeeHistory()  {
        List<String> employee = new ArrayList<>();

        if(departmentField.getText().equals("") || postField.getText().equals("") ||
                rankField.getText().equals("") || startDateField.getText().equals("") ||
                endDateField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Не все данные введены");
            return employee;
        }

        String id = idField.getText();
        String departmentFieldText = departmentField.getText();
        String postFieldText = postField.getText();
        String rankFieldText = rankField.getText();
        String startDateFieldText = startDateField.getText();
        String endDateFieldText = endDateField.getText();

        employee.add(id);
        employee.add(departmentFieldText);
        employee.add(postFieldText);
        employee.add(rankFieldText);
        employee.add(startDateFieldText);
        employee.add(endDateFieldText);

        clearFields();

        return employee;
    }

    private void clearFields(){
        idField.setText("");
        departmentField.setText("");
        postField.setText("");
        rankField.setText("");
        startDateField.setText("");
        endDateField.setText("");
    }
}
