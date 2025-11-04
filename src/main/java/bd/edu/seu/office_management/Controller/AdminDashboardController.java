/*package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {
    @FXML
    public Label nameField;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameField.setText(HelloApplication.loggedUser.getName());
    }

    @FXML
    public void logout(){
        HelloApplication.changeScene("login");
    }

    @FXML
    public void add_employee(){
        HelloApplication.changeScene("add_employee");
    }
    @FXML
    public void task_details(){
        HelloApplication.changeScene("add_task");
    }
    @FXML
    public void task_assign(){
        HelloApplication.changeScene("task_assign");
    }
    @FXML
    public void budget_calculation(){
        HelloApplication.changeScene("budget_calculation");
    }
    @FXML
    public void employee_attendance(){
        HelloApplication.changeScene("attendance_passing");
    }
}*/
package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {
    @FXML
    public Label nameField;

    @FXML
    private BarChart<String, Number> profitChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    // Fixed profit data for the last 12 months
    private final String[] months = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    private final int[] profits = {
            5000, 5200, 5500, 5000, 6500, 7000,
            7500, 8000, 6500, 7000, 8500, 10000
    };

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the username in the label
        nameField.setText(HelloApplication.loggedUser.getName());

        // Populate the BarChart with profit data for the last 12 months
        XYChart.Series<String, Number> profitSeries = new XYChart.Series<>();
        profitSeries.setName("Company Profit (Last 12 Months)");

        // Adding data to the series
        for (int i = 0; i < months.length; i++) {
            profitSeries.getData().add(new XYChart.Data<>(months[i], profits[i]));
        }

        // Add the series to the chart
        profitChart.getData().add(profitSeries);
    }

    @FXML
    public void logout() {
        HelloApplication.changeScene("login");
    }

    @FXML
    public void add_employee() {
        HelloApplication.changeScene("add_employee");
    }

    @FXML
    public void task_details() {
        HelloApplication.changeScene("add_task");
    }

    @FXML
    public void task_assign() {
        HelloApplication.changeScene("task_assign");
    }

    @FXML
    public void budget_calculation() {
        HelloApplication.changeScene("budget_calculation");
    }

    @FXML
    public void employee_attendance() {
        HelloApplication.changeScene("attendance_passing");
    }
}

