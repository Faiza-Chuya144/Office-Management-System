package bd.edu.seu.office_management.Service;


import bd.edu.seu.office_management.Model.Task;
import bd.edu.seu.office_management.Singleton.Singleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    public boolean save(Task task) {
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO task VALUE('" + task.getTaskNo() + "','" + task.getTaskName() + "','" + task.getTaskCost() + "','" + task.getTaskFrom() + "','" + task.getDueDate() + "');";
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Task task){
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE task SET taskName = '"+ task.getTaskName() +"', taskCost = '"+ task.getTaskCost() +"',taskFrom = '"+ task.getTaskFrom() +"', dueDate = '"+ task.getDueDate() +" ' WHERE taskNo = '"+ task.getTaskNo() +"'";
            statement.execute(query);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(String taskNo) {
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            // Enclose the taskNo in single quotes
            String query = "DELETE FROM task WHERE taskNo = '" + taskNo + "'";
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Task> getTaskList() {
        List<Task> taskList = new ArrayList<>();
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM task";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String taskNo = resultSet.getString("taskNo");
                String taskName = resultSet.getString("taskName");
                String taskCost = resultSet.getString("taskCost");
                String taskFrom = resultSet.getString("taskFrom");
                String dueDate = resultSet.getString("dueDate");

                Task task = new Task(taskNo, taskName, taskCost, taskFrom, dueDate);
                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }
}
