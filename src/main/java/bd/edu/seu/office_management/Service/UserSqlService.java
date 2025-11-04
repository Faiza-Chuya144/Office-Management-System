package bd.edu.seu.office_management.Service;

import bd.edu.seu.office_management.Model.User;
import bd.edu.seu.office_management.Singleton.Singleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserSqlService {
    public boolean register(User user){

        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO user VALUE('"+ user.getName() +"','"+ user.getPassword() +"','"+ user.getType() +"');";
            statement.execute(query);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed To save file");
        }
        return false;
    }




    public User logIn(String name, String password, String type){
        try{
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user WHERE name = '"+ name +"' AND password = '" + password + "'AND type = '"+type+"';";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String userName = resultSet.getString("name");
                String userPassword = resultSet.getString("password");
                String usertype = resultSet.getString("type");
                return new User(userName,userPassword,usertype);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Failed To save File");
        }
        return null;
    }
}