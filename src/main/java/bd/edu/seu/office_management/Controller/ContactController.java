package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ContactController {

    @FXML
    void exit(ActionEvent event) {
        HelloApplication.changeScene("employee_dash");
    }

}
