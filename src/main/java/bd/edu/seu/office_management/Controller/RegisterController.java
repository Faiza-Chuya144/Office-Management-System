package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import bd.edu.seu.office_management.Model.User;
import bd.edu.seu.office_management.Service.UserSqlService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private TextField userNameField;
    @FXML
    private TextField userPassField;

    @FXML
    private ChoiceBox<String> userTypeBox;



    @FXML
    void register(ActionEvent event) {
        String name =   userNameField.getText();
        String password =   userPassField.getText();
        String type = userTypeBox.getValue();

        User user = new User(name , password , type);
        UserSqlService userSqlService = new UserSqlService();
        boolean isRegistered = userSqlService.register(user);
        if(isRegistered){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Done");
            alert.setContentText("Successfully Registered");
            alert.showAndWait();
            System.out.println("Registration done");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to register");
            alert.showAndWait();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTypeBox.setItems(FXCollections.observableArrayList("Admin","Employee"));
    }

    @FXML
    public void changeToLogin() {
        HelloApplication.changeScene("login");
    }
}
