package view;

import control.MyConnection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditEmployeeFrame extends JFrame {

    private MyConnection myConnection;

    private JPanel labelPanel;
    private JPanel fieldPanel;

    private JTextField idField;
    private JTextField fullNameField;
    private JTextField genderField;
    private JTextField familyStatusField;
    private JTextField postField;
    private JTextField birthDayField;
    private List<String> tableData;

    public JButton addButton;

    public EditEmployeeFrame(MyConnection connection){

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
        fullNameField = new JTextField();
        fullNameField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        genderField = new JTextField();
        genderField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        familyStatusField = new JTextField();
        familyStatusField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        postField = new JTextField();
        postField.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        birthDayField = new JTextField();
        birthDayField.setMaximumSize(new Dimension(WIDTH, HEIGHT));


        fieldPanel.add(fullNameField);
        fieldPanel.add(genderField);
        fieldPanel.add(familyStatusField);
        fieldPanel.add(postField);
        fieldPanel.add(birthDayField);
    }

    private void createLabelPanel() {
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));

        JLabel fullName = new JLabel("ФИО");
        JLabel gender = new JLabel("Пол");
        JLabel familyStatus = new JLabel("Семейный статус");
        JLabel post = new JLabel("Должность");
        JLabel birthDay = new JLabel("Дата рождения");


        labelPanel.add(fullName);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(gender);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(familyStatus);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(post);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(birthDay);
        labelPanel.add(Box.createHorizontalGlue());
    }


    public void updateFields (List<String> tableData)  {
        this.tableData = tableData;
        clearFields();

        fullNameField.setText(tableData.get(1));
        genderField.setText(tableData.get(2));
        familyStatusField.setText(tableData.get(3));
        postField.setText(tableData.get(4));
        birthDayField.setText(tableData.get(5));

    }

    public List<String> getNewInfoEmployee(){
        List<String> employee = new ArrayList<>();

        if(fullNameField.getText().equals("") || genderField.getText().equals("") ||
                familyStatusField.getText().equals("") || postField.getText().equals("") ||
                birthDayField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Не все данные введены");
            return employee;
        }


        String fullName = fullNameField.getText();
        String gender = genderField.getText();
        String familyStatus = familyStatusField.getText();
        String post = postField.getText();
        String birthDayText = birthDayField.getText();

        employee.add(tableData.get(0));
        employee.add(fullName);
        employee.add(gender);
        employee.add(familyStatus);
        employee.add(post);
        employee.add(birthDayText);


        return employee;
    }

    private void clearFields(){
        fullNameField.setText("");
        genderField.setText("");
        familyStatusField.setText("");
        postField.setText("");
        birthDayField.setText("");
    }
}
