package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import javafx.fxml.FXML;

public class PaymentEmpController {
    @FXML
    public void exit() {
        HelloApplication.changeScene("employee_dash");
    }
}
