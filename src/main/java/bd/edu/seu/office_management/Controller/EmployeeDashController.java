package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import bd.edu.seu.office_management.Model.Employee;
import bd.edu.seu.office_management.Service.EmployeeSqlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class EmployeeDashController implements Initializable {

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private Label resultLabel;

    @FXML
    private PieChart attendanceChart;

    @FXML
    private Label nameLabel;

    @FXML
    void submit(ActionEvent event) {
        String employeeName = nameField.getText().trim();
        String employeeID = idField.getText().trim();

        // Validate input fields
        if (employeeName.isEmpty() || employeeID.isEmpty()) {
            resultLabel.setText("Please enter both your name and ID!");
            return;
        }

        // Check attendance
        LocalTime currentTime = LocalTime.now();
        LocalTime cutOffTime = LocalTime.of(9, 0);

        String attendanceStatus = currentTime.isBefore(cutOffTime) ? "Present" : "Absent";
        resultLabel.setText(attendanceStatus);

        // Create Employee object
        Employee employee = new Employee(employeeName, employeeID, attendanceStatus);
        HelloApplication.attendance = employee;

        // Save attendance via EmployeeSqlService
        EmployeeSqlService employeeSqlService = new EmployeeSqlService();
        employeeSqlService.attendance(employee);

        System.out.println("Attendance: " + attendanceStatus);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Example data for attendance chart
        int presentDays = 10;
        int absentDays = 2;

        ObservableList<PieChart.Data> attendanceData = FXCollections.observableArrayList(
                new PieChart.Data("Present", presentDays),
                new PieChart.Data("Absent", absentDays)
        );

        attendanceChart.setData(attendanceData);
        attendanceChart.setTitle("Attendance Progress");

        // Set logged user name
        if (HelloApplication.loggedUser != null) {
            nameLabel.setText(HelloApplication.loggedUser.getName());
        } else {
            nameLabel.setText("Welcome!");
        }
    }

    @FXML
    void contact(MouseEvent event) {
        HelloApplication.changeScene("contact");
    }

    @FXML
    void payment(MouseEvent event) {
        HelloApplication.changeScene("payment_emp");
    }

    @FXML
    void progress(MouseEvent event) {
        HelloApplication.changeScene("progress_employee");
    }

    @FXML
    void task_assign(MouseEvent event) {
        HelloApplication.changeScene("task_assign");
    }

    @FXML
    void dashboard(ActionEvent event) {
        HelloApplication.changeScene("login");
    }
}
