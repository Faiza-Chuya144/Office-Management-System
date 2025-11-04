package bd.edu.seu.office_management;

import bd.edu.seu.office_management.Model.Employee;
import bd.edu.seu.office_management.Model.EmployeeProgress;
import bd.edu.seu.office_management.Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;
    public static User loggedUser;
    public static EmployeeProgress progress;
    public static Employee attendance;



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Image icon = new Image(HelloApplication.class.getResourceAsStream("/image/Office Logo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Welcome To Office Management System");
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }

    public static void main(String[] args) {
        launch();
    }
    public static void  changeScene(String fileName){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName+".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
