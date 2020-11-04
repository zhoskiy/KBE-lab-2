package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DataInputFrame extends JFrame {
    private JPanel labelPanel;
    private JPanel fieldPanel;

    private JTextField post;
    private JTextField age;

    public JButton addButton;

    public DataInputFrame(){
        super("Поиск сотрудников моложе указанного возраста");
        setSize(480, 100);

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


        post = new JTextField();
        post.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        age = new JTextField();
        age.setMaximumSize(new Dimension(WIDTH, HEIGHT));


        fieldPanel.add(post);
        fieldPanel.add(age);
    }

    private void createLabelPanel() {
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));

        JLabel post = new JLabel("Должность");
        JLabel age = new JLabel("Возраст");



        labelPanel.add(post);
        labelPanel.add(Box.createHorizontalGlue());
        labelPanel.add(age);
        labelPanel.add(Box.createHorizontalGlue());
    }


    public List<String> getData()  {
        List<String> employee = new ArrayList<>();

        if(post.getText().equals("") || age.getText().equals("") )
        {
            JOptionPane.showMessageDialog(this, "Не все данные введены");
            return employee;
        }


        String postText = post.getText();
        String ageText = age.getText();


        employee.add(postText);
        employee.add(ageText);

        clearFields();
        setVisible(false);
        return employee;
    }

    private void clearFields(){
        post.setText("");
        age.setText("");

    }
}
