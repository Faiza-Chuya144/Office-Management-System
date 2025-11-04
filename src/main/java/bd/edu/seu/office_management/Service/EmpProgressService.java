package bd.edu.seu.office_management.Service;

import bd.edu.seu.office_management.Model.EmployeeProgress;
import bd.edu.seu.office_management.Singleton.Singleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpProgressService
{
    public boolean progress(EmployeeProgress employeeProgress) {
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();

            String query = "INSERT INTO employee_progress VALUE('" + employeeProgress.getTaskCompleted() + "','" + employeeProgress.getChallenges() + "','" + employeeProgress.getSkillUtilized() + "','" + employeeProgress.getNextSteps() + "','" + employeeProgress.getRate()+ "','" + employeeProgress.getDate() + "')";
            statement.execute(query);
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return false;

    }



    public List<EmployeeProgress> getProgress() {
        List<EmployeeProgress> progressList = new ArrayList<>();

        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM employee_progress;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                String date = resultSet.getString("date");
                String task = resultSet.getString("task_completed");
                String challenges = resultSet.getString("challanges");
                String skill = resultSet.getString("skill_utilized");

                String next = resultSet.getString("next_steps");
                String rate = resultSet.getString("rate");


                EmployeeProgress employeeProgress = new EmployeeProgress(task,challenges,skill,next,rate,date);
                progressList.add(employeeProgress);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return progressList;


    }



    //delete
    public boolean deleteInfo(EmployeeProgress employeeProgress) {
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();

            String query = "Delete From employee_progress Where date=('" + employeeProgress.getDate() + "')";
            statement.execute(query);
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return false;

    }

    //update
    public boolean updateInfo(EmployeeProgress employeeProgress) {
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();

            String query = "UPDATE employee_progress SET task_completed='" + employeeProgress.getTaskCompleted() + "', challanges= '" + employeeProgress.getChallenges() + "', skill_utilized = '" + employeeProgress.getSkillUtilized() + "', next_steps = '" +employeeProgress.getSkillUtilized() + "', rate = '" + employeeProgress.getRate() + "' WHERE date = '" + employeeProgress.getDate() + "'";
            System.out.println(query);
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }





}
