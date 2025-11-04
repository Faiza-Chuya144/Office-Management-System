package bd.edu.seu.office_management.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {
    private static final String DB_HOST = "localhost";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "(Enter your password)";
    private static final String DB_NAME = "oms";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME ;
    private static Connection connection ;

    private static Singleton singleton = new Singleton();

    private Singleton(){
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection(){
        return connection;
    }
}

