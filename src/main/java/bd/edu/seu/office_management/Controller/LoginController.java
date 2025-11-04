package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import bd.edu.seu.office_management.Model.User;
import bd.edu.seu.office_management.Service.UserSqlService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passField;
    @FXML
    private ChoiceBox<String> typeField;


    @FXML
    void logIn(ActionEvent event) {
        String name =   nameField.getText();
        String password =   passField.getText();
        String type = typeField.getValue();


        UserSqlService userSqlService = new UserSqlService();
        User user = userSqlService.logIn(name,password,type);
        if(user != null){
            HelloApplication.loggedUser = user;
            switch (user.getType()) {
                case "Admin":
                    HelloApplication.changeScene("admin_dashboard");
                    break;
                case "Employee":
                    HelloApplication.changeScene("employee_dash");
                    break;
            }


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("User name or password Incorrect");
            alert.showAndWait();
        }



    }

    @FXML
    void register(MouseEvent event) {
        HelloApplication.changeScene("register");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeField.setItems(FXCollections.observableArrayList("Admin","Employee"));
    }
}