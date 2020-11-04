import control.MyConnection;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;

public class Main extends JFrame
{

    private final MyConnection myConnection;

    private final AddEmployeeFrame addEmployeeFrame;
    private final AddEmployeeHistoryFrame addEmployeeHistoryFrame;
    private final MainPanel mainPanel;
    private final ViewEmployeePanel viewEmployeePanel;
    private final ViewEmployeeHistoryPanel viewEmployeeHistoryPanel;
    private final EditEmployeeFrame editEmployeeFrame;
    private final EditEmployeeHistoryFrame editEmployeeHistoryFrame;
    private final DataInputFrame dataInputFrame;
    private final InputDepartmentFrame inputDepartmentFrame;
    private final SchedulePanel schedulePanel;
    private final RetirePanel retirePanel;

    public Main(MyConnection myConnection){

        super("ООО Где мой сон?");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setSize(1280, 360);

        this.myConnection = myConnection;

        ViewEmployeeListener viewEmployeeListener = new ViewEmployeeListener();
        ViewEmployeeHistoryListener viewEmployeeHistoryListener = new ViewEmployeeHistoryListener();
        ViewMainMenuListener viewMainMenuListener = new ViewMainMenuListener();
        ViewScheduleListener viewScheduleListener = new ViewScheduleListener();


        ViewAddEmployeeFrame viewAddEmployeeFrame = new ViewAddEmployeeFrame();
        ViewEditEmployeeFrame viewEditEmployeeFrame = new ViewEditEmployeeFrame();
        ViewAddEmployeeHistoryFrame viewAddEmployeeHistoryFrame = new ViewAddEmployeeHistoryFrame();
        ViewEditEmployeeHistoryFrame viewEditEmployeeHistoryFrame = new ViewEditEmployeeHistoryFrame();
        ViewDataInputEmpFrame viewDataEmpFrame = new ViewDataInputEmpFrame();
        ViewInputDepartmentFrame viewInputDepartmentFrame = new ViewInputDepartmentFrame();
        ViewRetireListener viewRetireListener = new ViewRetireListener();


        AddEmployeeListener addEmployeeListener = new AddEmployeeListener();
        AddEmployeeHistoryListener addEmployeeHistoryListener = new AddEmployeeHistoryListener();
        DeleteEmployeeListener deleteEmployeeListener = new DeleteEmployeeListener();
        DeleteEmployeeHistoryListener deleteEmployeeHistoryListener = new DeleteEmployeeHistoryListener();
        EditEmployeeListener editEmployeeListener = new EditEmployeeListener();
        EditEmployeeHistoryListener editEmployeeHistoryListener= new EditEmployeeHistoryListener();
        SearchYoungEmpListener searchYoungEmpListener = new SearchYoungEmpListener();


        mainPanel = new MainPanel();
        viewEmployeePanel = new ViewEmployeePanel();
        viewEmployeePanel.setVisible(false);
        addEmployeeFrame = new AddEmployeeFrame(myConnection);
        addEmployeeHistoryFrame = new AddEmployeeHistoryFrame(myConnection);
        viewEmployeeHistoryPanel = new ViewEmployeeHistoryPanel();
        viewEmployeeHistoryPanel.setVisible(false);
        editEmployeeFrame = new EditEmployeeFrame(myConnection);
        editEmployeeHistoryFrame = new EditEmployeeHistoryFrame(myConnection);
        dataInputFrame = new DataInputFrame();
        inputDepartmentFrame = new InputDepartmentFrame();
        schedulePanel = new SchedulePanel();
        schedulePanel.setVisible(false);
        retirePanel = new RetirePanel();
        retirePanel.setVisible(false);


        mainPanel.getViewEmployeeButton().addActionListener(viewEmployeeListener);
        mainPanel.getViewEmployeeHistoryButton().addActionListener(viewEmployeeHistoryListener);
        mainPanel.getViewScheduleButton().addActionListener(viewInputDepartmentFrame);
        viewEmployeePanel.getMainMenuButton().addActionListener(viewMainMenuListener);
        viewEmployeePanel.getAddEmployeeButton().addActionListener(viewAddEmployeeFrame);
        viewEmployeePanel.getDeleteEmployeeButton().addActionListener(deleteEmployeeListener);
        viewEmployeePanel.editButton.addActionListener(viewEditEmployeeFrame);
        viewEmployeePanel.lookYoungEmpButton.addActionListener(viewDataEmpFrame);
        viewEmployeePanel.lookRetireButton.addActionListener(viewRetireListener);
        addEmployeeFrame.addButton.addActionListener(addEmployeeListener);
        editEmployeeFrame.addButton.addActionListener(editEmployeeListener);
        editEmployeeHistoryFrame.addButton.addActionListener(editEmployeeHistoryListener);
        mainPanel.getViewEmployeeHistoryButton().addActionListener(viewEmployeeHistoryListener);
        viewEmployeeHistoryPanel.mainMenuButton.addActionListener(viewMainMenuListener);
        viewEmployeeHistoryPanel.addEmployeeHistoryButton.addActionListener(viewAddEmployeeHistoryFrame);
        viewEmployeeHistoryPanel.deleteEmployeeHistoryButton.addActionListener(deleteEmployeeHistoryListener);
        viewEmployeeHistoryPanel.editButton.addActionListener(viewEditEmployeeHistoryFrame);
        addEmployeeHistoryFrame.addButton.addActionListener(addEmployeeHistoryListener);
        dataInputFrame.addButton.addActionListener(searchYoungEmpListener);
        inputDepartmentFrame.addButton.addActionListener(viewScheduleListener);
        schedulePanel.mainMenuButton.addActionListener(viewMainMenuListener);
        retirePanel.backButton.addActionListener(viewEmployeeListener);

        panel.add(viewEmployeeHistoryPanel);
        panel.add(mainPanel);
        panel.add(viewEmployeePanel);
        panel.add(schedulePanel);
        panel.add(retirePanel);
        add(panel);

        setVisible(true);
    }

    private class SearchYoungEmpListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent){
            viewEmployeePanel.updateTable(myConnection.getYoungEmployees(dataInputFrame.getData()));
        }
    }

    private class AddEmployeeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent){
            try {
                myConnection.AddEmployee(addEmployeeFrame.getNewDataEmployee());
                viewEmployeePanel.updateTable(myConnection.getAllEmployee());
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private class AddEmployeeHistoryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent){
            try {
                myConnection.AddEmployeeHistory(addEmployeeHistoryFrame.getNewDataEmployeeHistory());
               viewEmployeeHistoryPanel.updateTable(myConnection.getAllEmployeeHistory());
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private class DeleteEmployeeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent){
            try {
                myConnection.deleteEmployee(viewEmployeePanel.getEmployeeId());
                viewEmployeePanel.updateTable(myConnection.getAllEmployee());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    private class DeleteEmployeeHistoryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent){
            try {
                myConnection.deleteEmployeeHistory(viewEmployeeHistoryPanel.getEmployeeId());
                viewEmployeeHistoryPanel.updateTable(myConnection.getAllEmployeeHistory());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    private class EditEmployeeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent){
            try {
                myConnection.editEmployee(editEmployeeFrame.getNewInfoEmployee());
                viewEmployeePanel.updateTable(myConnection.getAllEmployee());
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    private class EditEmployeeHistoryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent){
            try {
                myConnection.editEmployeeHistory(editEmployeeHistoryFrame.getNewInfoEmployeeHistory());
                viewEmployeeHistoryPanel.updateTable(myConnection.getAllEmployeeHistory());
            } catch (SQLException | ParseException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    private  class ViewAddEmployeeFrame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event){
            addEmployeeFrame.setVisible(true);
        }
    }

    private  class ViewDataInputEmpFrame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event){
            dataInputFrame.setVisible(true);
        }
    }

    private  class ViewInputDepartmentFrame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event){
            inputDepartmentFrame.setVisible(true);
        }
    }



    private  class ViewEditEmployeeFrame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event){

            editEmployeeFrame.setVisible(true);
            editEmployeeFrame.updateFields(viewEmployeePanel.getEmployee());
        }
    }

    private  class ViewEditEmployeeHistoryFrame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event){

            editEmployeeHistoryFrame.setVisible(true);
            editEmployeeHistoryFrame.updateFields(viewEmployeeHistoryPanel.getEmployeeHistory());
        }
    }

    private  class ViewAddEmployeeHistoryFrame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event){
            addEmployeeHistoryFrame.setVisible(true);
        }
    }

    private  class ViewMainMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event){
            mainPanel.setVisible(true);
            viewEmployeePanel.setVisible(false);
            viewEmployeeHistoryPanel.setVisible(false);
            schedulePanel.setVisible(false);

        }
    }

    private  class ViewScheduleListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event){
            mainPanel.setVisible(false);
            viewEmployeePanel.setVisible(false);
            viewEmployeeHistoryPanel.setVisible(false);
            schedulePanel.setVisible(true);
            int departmentID = inputDepartmentFrame.getDepartmentID();
            schedulePanel.updateTable(myConnection.getDepartmentSchedule(departmentID), departmentID);
        }
    }

    private class ViewEmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            viewEmployeePanel.setVisible(true);
            viewEmployeePanel.updateTable(myConnection.getAllEmployee());
            viewEmployeeHistoryPanel.setVisible(false);
            mainPanel.setVisible(false);
            schedulePanel.setVisible(false);
            retirePanel.setVisible(false);
        }
    }

    private class ViewRetireListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            viewEmployeePanel.setVisible(false);
            retirePanel.setVisible(true);
            retirePanel.updateTable(myConnection.getRetires());
        }
    }

    private class ViewEmployeeHistoryListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            viewEmployeeHistoryPanel.setVisible(true);
            viewEmployeePanel.setVisible(false);
            mainPanel.setVisible(false);
            viewEmployeeHistoryPanel.updateTable(myConnection.getAllEmployeeHistory());

        }
    }

    public static void main(String[] args)  {


        MyConnection myConnection = new MyConnection();
        new Main(myConnection);
    }
}
