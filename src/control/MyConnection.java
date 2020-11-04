package control;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class MyConnection {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/factory";
    static final String USER = "postgres";
    static final String PASS = "12345678";


    private Connection connection;

    public MyConnection(){

        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");

        connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now ");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    public Vector<Vector<String>> getAllEmployee(){

        Vector<Vector<String>> employees = new Vector<Vector<String>>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM employees");
            while (rs.next()){

                Vector<String> employee = new Vector<String>();

                employee.add(rs.getString(1));
                employee.add(rs.getString(2));
                employee.add(rs.getString(3));
                employee.add(rs.getString(4));
                employee.add(rs.getString(5));
                employee.add(rs.getString(6));

                employees.add(employee);
            }
            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return employees;
    }

    public Vector<Vector<String>> getYoungEmployees(List<String> data) {
        if(data.isEmpty())
            return  getAllEmployee();

        Vector<Vector<String>> employees = new Vector<Vector<String>>();

        System.out.println(data);

        int age = Integer.parseInt(data.get(1));
        String post = "'"+data.get(0)+"'";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from employees " +
                    "where DATE_PART('year', current_date) - DATE_PART('year', birth_date)  < " + age +
                    " and post = "+ post);
            while (rs.next()){

                Vector<String> employee = new Vector<String>();

                employee.add(rs.getString(1));
                employee.add(rs.getString(2));
                employee.add(rs.getString(3));
                employee.add(rs.getString(4));
                employee.add(rs.getString(5));
                employee.add(rs.getString(6));

                employees.add(employee);
            }
            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return employees;
    }

    public Vector<Vector<String>> getDepartmentSchedule(int departmentID){


        Vector<Vector<String>> schedules = new Vector<Vector<String>>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT dp.post, dp.post_count, sc.shift_start, sc.shift_end " +
                    "FROM schedule sc " +
                    "JOIN department_post dp ON dp.department = sc.department_id " +
                    "WHERE department_id = " + departmentID);
            while (rs.next()){

                Vector<String> schedule = new Vector<String>();

                schedule.add(rs.getString(1));
                schedule.add(rs.getString(2));
                schedule.add(rs.getString(3));
                schedule.add(rs.getString(4));
               // schedule.add(rs.getString(5));


                schedules.add(schedule);
            }
            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return schedules;
    }

    public void editEmployeeHistory(List<String> employee) throws SQLException, ParseException {

        if(employee.isEmpty())
            return;

        int id = Integer.parseInt(employee.get(0));
        System.out.println(id);
        String sql = "UPDATE employees_post SET department = ?, post = ?, rank = ?, startDate = ?, endDate = ?" +
                " WHERE id = " + id;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(employee.get(4));
        java.sql.Date sqlDate1 = convert(startDate);
        Date endDate = format.parse(employee.get(5));
        java.sql.Date sqlDate2 = convert(endDate);

        preparedStatement.setString(1, employee.get(1));
        preparedStatement.setString(2, employee.get(2));
        preparedStatement.setString(3, employee.get(3));
        preparedStatement.setDate(4, sqlDate1);
        preparedStatement.setDate(5, sqlDate2);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void editEmployee(List<String> employee) throws SQLException, ParseException {

        if(employee.isEmpty())
            return;

        int id = Integer.parseInt(employee.get(0));
        System.out.println(id);
        String sql = "UPDATE employees SET full_name = ?, gender = ?, family_status = ?, post = ?, birth_date = ? " +
                "WHERE id = " + id;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay = format.parse(employee.get(5));
        java.sql.Date sqlDate = convert(birthDay);

        preparedStatement.setString(1, employee.get(1));
        preparedStatement.setString(2, employee.get(2));
        preparedStatement.setString(3, employee.get(3));
        preparedStatement.setString(4, employee.get(4));
        preparedStatement.setDate(5, sqlDate);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void AddEmployee(List<String> employee) throws SQLException, ParseException {

        if(employee.isEmpty())
            return;

        String sql = "INSERT INTO employees (full_name, gender, family_status, post, birth_date) Values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay = format.parse(employee.get(4));
        java.sql.Date sqlDate = convert(birthDay);

        preparedStatement.setString(1, employee.get(0));
        preparedStatement.setString(2, employee.get(1));
        preparedStatement.setString(3, employee.get(2));
        preparedStatement.setString(4, employee.get(3));
        preparedStatement.setDate(5, sqlDate);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteEmployee(int id) throws SQLException {
        if(id == -1)
            return;

        String sql = "DELETE FROM employees WHERE id =" + id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteEmployeeHistory(int id) throws SQLException {
        if(id == -1)
            return;

        String sql = "DELETE FROM employees_post WHERE id =" + id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private static java.sql.Date convert(Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    public void AddEmployeeHistory(List<String> newDataEmployeeHistory) throws SQLException, ParseException {

        if(newDataEmployeeHistory.isEmpty())
            return;

        String sql = "INSERT INTO employees_post (id, department, post, rank, startDate, endDate) " +
                "Values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        int id = Integer.parseInt((String) newDataEmployeeHistory.get(0));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(newDataEmployeeHistory.get(4));
        java.sql.Date sqlDate1 = convert(startDate);
        Date endDate = format.parse(newDataEmployeeHistory.get(5));
        java.sql.Date sqlDate2 = convert(endDate);

        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, newDataEmployeeHistory.get(1));
        preparedStatement.setString(3, newDataEmployeeHistory.get(2));
        preparedStatement.setString(4, newDataEmployeeHistory.get(3));
        preparedStatement.setDate(5, sqlDate1);
        preparedStatement.setDate(6, sqlDate2);

        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    public Vector<Vector<String>> getAllEmployeeHistory() {

        Vector<Vector<String>> employees = new Vector<Vector<String>>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM employees_post");
            while (rs.next()){

                Vector<String> employee = new Vector<String>();

                employee.add(rs.getString(1));
                employee.add(rs.getString(2));
                employee.add(rs.getString(3));
                employee.add(rs.getString(4));
                employee.add(rs.getString(5));
                employee.add(rs.getString(6));

                employees.add(employee);
            }
            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return employees;
    }

    public Vector<Vector<String>> getRetires() {

        String gender = "'ж'";
        Vector<Vector<String>> retires = new Vector<Vector<String>>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT full_name, birth_date  FROM employees " +
                    "where DATE_PART('year', current_date) - DATE_PART('year', birth_date)  >=  55 "+
                    " and gender = " + gender);
            while (rs.next()){

                Vector<String> retire = new Vector<String>();

                retire.add(rs.getString(1));
                retire.add(rs.getString(2));

                retires.add(retire);
            }
            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return retires;
    }
}
