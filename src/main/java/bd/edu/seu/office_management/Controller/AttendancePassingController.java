package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import bd.edu.seu.office_management.Model.Employee;
import bd.edu.seu.office_management.Model.EmployeeProgress;
import bd.edu.seu.office_management.Service.EmpProgressService;
import bd.edu.seu.office_management.Service.EmployeeSqlService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AttendancePassingController implements Initializable {
    @FXML
    private TableColumn<Employee, String> attendanceColumn;

    @FXML
    private TableView<Employee> attendanceTable;

    @FXML
    private TableColumn<Employee, String> idColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn;





    ObservableList<Employee> employeeAttendanceObservableList;  //bahire declare
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getName()));
        idColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getId()));
        attendanceColumn.setCellValueFactory(cell->new SimpleStringProperty(cell.getValue().getAttendance()));



        //ObservableList<EmployeeProgress> /////coz bahire already declared
        employeeAttendanceObservableList = FXCollections.observableArrayList();

        //from database
        EmployeeSqlService employeeSqlService=new EmployeeSqlService();
        List<Employee> detailList=employeeSqlService.getAttendanceDetails();
        employeeAttendanceObservableList.addAll(detailList);//////bahire declare
        //

        attendanceTable.setItems(employeeAttendanceObservableList);
    }

    @FXML
    void exit(ActionEvent event) {
        HelloApplication.changeScene("admin_dashboard");
    }

}
