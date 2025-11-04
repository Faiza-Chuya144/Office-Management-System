package bd.edu.seu.office_management.Service;

import bd.edu.seu.office_management.Model.AddEmployee;
import bd.edu.seu.office_management.Singleton.Singleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    public boolean save(AddEmployee addEmployee) {
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO addEmployee VALUE('" + addEmployee.getId() + "','" + addEmployee.getName() + "','" + addEmployee.getBirth() + "','" + addEmployee.getPhone() + "','" + addEmployee.getAddress() + "','"+ addEmployee.getGender() +"');";
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(AddEmployee addEmployee){
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE addEmployee SET name = '"+ addEmployee.getName() +"', birth = '"+ addEmployee.getBirth() +"',phone = '"+ addEmployee.getPhone() +"', address = '"+ addEmployee.getAddress() +" ' , gender = '"+ addEmployee.getGender() +"' WHERE id = '"+ addEmployee.getId() +"'";
            statement.execute(query);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(String id) {
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM addEmployee WHERE id = '" + id + "'"; // Enclose id in single quotes
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<AddEmployee> getEmployeeList() {
        List<AddEmployee> employeeList = new ArrayList<>();
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM addEmployee";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String birth = resultSet.getString("birth");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String gender = resultSet.getString("gender");

                AddEmployee employee = new AddEmployee(id, name, birth, phone, address,gender);
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
