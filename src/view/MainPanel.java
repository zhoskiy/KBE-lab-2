package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JPanel {
    private JButton viewEmployeeButton;
    private JButton viewEmployeeHistoryButton;
    private JButton viewSchedule;

    public MainPanel(){
    viewEmployeeButton = new JButton("Список работников");
    viewEmployeeHistoryButton = new JButton("История работников");
    viewSchedule = new JButton("Расписание подразделения");

    viewEmployeeButton.setMaximumSize(new Dimension(250, 100));
    viewEmployeeHistoryButton.setMaximumSize(new Dimension(250, 100));
    viewSchedule.setMaximumSize(new Dimension(250, 100));

    setLayout(new BoxLayout(this , BoxLayout.X_AXIS));

    add(viewEmployeeButton);
    add(Box.createHorizontalGlue());
    add(viewEmployeeHistoryButton);
    add(Box.createHorizontalGlue());
    add(viewSchedule);
    }



    public JButton getViewEmployeeButton(){
        return viewEmployeeButton;
    }

    public JButton getViewEmployeeHistoryButton(){
        return viewEmployeeHistoryButton;
    }

    public JButton getViewScheduleButton(){
        return viewSchedule;
    }

}

