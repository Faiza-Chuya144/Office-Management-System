package bd.edu.seu.office_management.Service;
import bd.edu.seu.office_management.Model.Employee;
import bd.edu.seu.office_management.Singleton.Singleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSqlService {


    public boolean attendance(Employee employee){

        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO employee_attendance VALUE('"+ employee.getName() +"','"+ employee.getId() +"','"+ employee.getAttendance() +"');";
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed");
        }
        return false;
    }



    public List<Employee> getAttendanceDetails() {
        List<Employee> tableBookingList = new ArrayList<>();

        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM employee_attendance;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String ID = resultSet.getString("ID");
                String attendance = resultSet.getString("Attendance");


                Employee employee = new Employee(name,ID,attendance);
                tableBookingList.add(employee);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tableBookingList;


    }


}
