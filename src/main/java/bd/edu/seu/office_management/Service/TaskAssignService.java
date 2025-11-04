package bd.edu.seu.office_management.Service;
import bd.edu.seu.office_management.Model.TaskAssign;
import bd.edu.seu.office_management.Singleton.Singleton;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class TaskAssignService {


    public boolean save(TaskAssign taskAssign) {
        try {
            Connection connection = Singleton.getConnection();
            String query = "INSERT INTO taskassign (employeeName, taskName, assignDate, dueDate, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, taskAssign.getEmployeeName());
            preparedStatement.setString(2, taskAssign.getTaskName());
            preparedStatement.setDate(3, Date.valueOf(taskAssign.getAssignDate()));
            preparedStatement.setDate(4, Date.valueOf(taskAssign.getDueDate()));
            preparedStatement.setString(5, taskAssign.getStatus());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(TaskAssign taskAssign) {
        try {
            Connection connection = Singleton.getConnection();
            String query = "UPDATE taskassign SET assignDate = ?, dueDate = ?, status = ? WHERE employeeName = ? AND taskName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(taskAssign.getAssignDate()));
            preparedStatement.setDate(2, Date.valueOf(taskAssign.getDueDate()));
            preparedStatement.setString(3, taskAssign.getStatus());
            preparedStatement.setString(4, taskAssign.getEmployeeName());
            preparedStatement.setString(5, taskAssign.getTaskName());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean delete(String employeeName, String taskName) {
        try {
            Connection connection = Singleton.getConnection();
            String query = "DELETE FROM taskassign WHERE employeeName = ? AND taskName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeName);
            preparedStatement.setString(2, taskName);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<TaskAssign> getTaskAssignList() {
        List<TaskAssign> taskAssignList = new ArrayList<>();
        try {
            Connection connection = Singleton.getConnection();
            String query = "SELECT * FROM taskassign";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String employeeName = resultSet.getString("employeeName");
                String taskName = resultSet.getString("taskName");
                LocalDate assignDate = resultSet.getDate("assignDate").toLocalDate();
                LocalDate dueDate = resultSet.getDate("dueDate").toLocalDate();
                String status = resultSet.getString("status");

                TaskAssign taskAssign = new TaskAssign(employeeName, taskName, assignDate, dueDate, status);
                taskAssignList.add(taskAssign);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskAssignList;
    }


    public List<String> getEmployeeNames() {
        List<String> employeeNames = new ArrayList<>();
        try {
            Connection connection = Singleton.getConnection();
            String query = "SELECT name FROM addEmployee";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                employeeNames.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeNames;
    }

    /**
     * Retrieves the list of tasks from the task database.
     */
    public List<String> getTaskNames() {
        List<String> taskNames = new ArrayList<>();
        try {
            Connection connection = Singleton.getConnection();
            String query = "SELECT taskName FROM task";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                taskNames.add(resultSet.getString("taskName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskNames;
    }
}
