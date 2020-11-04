package view;

import javax.swing.*;
import java.awt.*;

public class InputDepartmentFrame extends JFrame {
    private JPanel labelPanel;
    private JPanel fieldPanel;

    private JTextField departmentField;

    public JButton addButton;

    public InputDepartmentFrame(){
        super("Выбор подразделения");
        setSize(400, 100);

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

        departmentField = new JTextField();
        departmentField.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        fieldPanel.add(departmentField);
    }

    private void createLabelPanel() {
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));

        JLabel post = new JLabel("Введите номер подразделения");

        labelPanel.add(post);
       // labelPanel.add(Box.createHorizontalGlue());
    }

    public int getDepartmentID()  {
        int departmentId = -1;
        if(departmentField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Не все данные введены");
            return departmentId;
        }

        departmentId = Integer.parseInt(departmentField.getText());
        setVisible(false);
        clearFields();
        return departmentId;
    }

    private void clearFields(){
        departmentField.setText("");
    }
}
