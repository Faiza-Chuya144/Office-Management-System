package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import bd.edu.seu.office_management.Model.TaskAssign;
import bd.edu.seu.office_management.Service.TaskAssignService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TaskAssignController implements Initializable {

    @FXML
    private TableView<TaskAssign> taskAssignTable;

    @FXML
    private TableColumn<TaskAssign, String> employeeNameCol;

    @FXML
    private TableColumn<TaskAssign, String> taskNameCol;

    @FXML
    private TableColumn<TaskAssign, LocalDate> assignDateCol;

    @FXML
    private TableColumn<TaskAssign, LocalDate> dueDateCol;

    @FXML
    private TableColumn<TaskAssign, String> statusCol;

    @FXML
    private ComboBox<String> employeeNameField;

    @FXML
    private ComboBox<String> taskNameField;

    @FXML
    private DatePicker assignDateField;

    @FXML
    private DatePicker dueDateField;

    @FXML
    private ToggleGroup statusToggle;

    private final TaskAssignService taskAssignService = new TaskAssignService();
    private ObservableList<TaskAssign> taskAssignList;
    private TaskAssign selectedTaskAssign;
    @FXML
    private Button save;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button clear;
    @FXML
    private Button exit;
    @FXML
    private Button exit1;
    @FXML
    private ToolBar toolbar;

   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployeeName()));
        taskNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTaskName()));
        assignDateCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getAssignDate()));
        dueDateCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDueDate()));

        statusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));

        loadTableData();
        loadComboBoxData();
       adjustButtonVisibility();
       adjustToolbarVisibility();

        taskAssignTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedTaskAssign = newValue;
            displaySelectedTaskAssign();
        });

    }

    private void adjustButtonVisibility() {
        if (HelloApplication.loggedUser != null) {
            String userRole = HelloApplication.loggedUser.getType();
            if (userRole.equals("Admin")) {
                save.setVisible(true);
                update.setVisible(true);
                delete.setVisible(true);
                clear.setVisible(true);
                exit.setVisible(true);
                exit1.setVisible(false);

            } else if (userRole.equals("Employee")) {
                save.setVisible(false);
                update.setVisible(false);
                delete.setVisible(false);
                clear.setVisible(false);
                exit.setVisible(false);
                exit1.setVisible(true);
            }
        }
    }
    private void adjustToolbarVisibility() {
        if (HelloApplication.loggedUser != null) {
            String userRole = HelloApplication.loggedUser.getType(); // Get logged-in user's role

            if ("Admin".equals(userRole)) {
                toolbar.setVisible(true);
            } else if ("Employee".equals(userRole)){
                toolbar.setVisible(false);
            }
        }
    }

    private void loadTableData() {
        taskAssignList = FXCollections.observableArrayList(taskAssignService.getTaskAssignList());
        taskAssignTable.setItems(taskAssignList);
    }

    private void loadComboBoxData() {
        employeeNameField.setItems(FXCollections.observableArrayList(taskAssignService.getEmployeeNames()));
        taskNameField.setItems(FXCollections.observableArrayList(taskAssignService.getTaskNames()));
    }

    private void displaySelectedTaskAssign() {
        if (selectedTaskAssign != null) {
            employeeNameField.setValue(selectedTaskAssign.getEmployeeName());
            taskNameField.setValue(selectedTaskAssign.getTaskName());
            assignDateField.setValue(selectedTaskAssign.getAssignDate());
            dueDateField.setValue(selectedTaskAssign.getDueDate());

            String status = selectedTaskAssign.getStatus();
            for (Toggle toggle : statusToggle.getToggles()) {
                RadioButton radioButton = (RadioButton) toggle;
                if (radioButton.getText().equals(status)) {
                    statusToggle.selectToggle(toggle);
                    break;
                }
            }
        }
    }

    private void clearFields() {
        selectedTaskAssign = null;
        employeeNameField.setValue(null);
        taskNameField.setValue(null);
        assignDateField.setValue(null);
        dueDateField.setValue(null);
        statusToggle.selectToggle(null);
        taskAssignTable.getSelectionModel().clearSelection();
    }

    @FXML
    void save(ActionEvent event) {
        String employeeName = employeeNameField.getValue();
        String taskName = taskNameField.getValue();
        LocalDate assignDate = assignDateField.getValue();
        LocalDate dueDate = dueDateField.getValue();
        RadioButton selectedStatus = (RadioButton) statusToggle.getSelectedToggle();

        if (employeeName != null && taskName != null && assignDate != null && dueDate != null && selectedStatus != null) {
            TaskAssign taskAssign = new TaskAssign(employeeName, taskName, assignDate, dueDate, selectedStatus.getText());

            if (taskAssignService.save(taskAssign)) {
                taskAssignList.add(taskAssign);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Task assignment saved successfully!");
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save task assignment.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please fill in all fields.");
        }
    }

    @FXML
    void update(ActionEvent event) {
        if (selectedTaskAssign != null) {
            String employeeName = employeeNameField.getValue();
            String taskName = taskNameField.getValue();
            LocalDate assignDate = assignDateField.getValue();
            LocalDate dueDate = dueDateField.getValue();
            RadioButton selectedStatus = (RadioButton) statusToggle.getSelectedToggle();

            if (employeeName != null && taskName != null && assignDate != null && dueDate != null && selectedStatus != null) {
                selectedTaskAssign.setEmployeeName(employeeName);
                selectedTaskAssign.setTaskName(taskName);
                selectedTaskAssign.setAssignDate(assignDate);
                selectedTaskAssign.setDueDate(dueDate);
                selectedTaskAssign.setStatus(selectedStatus.getText());

                if (taskAssignService.update(selectedTaskAssign)) {
                    taskAssignTable.refresh();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Task assignment updated successfully!");
                    clearFields();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update task assignment.");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please fill in all fields.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a task assignment to update.");
        }
    }

    @FXML
    void delete(ActionEvent event) {
        if (selectedTaskAssign != null) {
            if (taskAssignService.delete(selectedTaskAssign.getEmployeeName(), selectedTaskAssign.getTaskName())) {
                taskAssignList.remove(selectedTaskAssign);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Task assignment deleted successfully!");
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete task assignment.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a task assignment to delete.");
        }
    }

    @FXML
    void clear(ActionEvent event) {
        clearFields();
    }

    @FXML
    void exit(){
        HelloApplication.changeScene("admin_dashboard");
    }
    @FXML
    void exit1(){
        HelloApplication.changeScene("employee_dash");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
