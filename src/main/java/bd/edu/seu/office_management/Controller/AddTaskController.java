package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import bd.edu.seu.office_management.Model.Task;
import bd.edu.seu.office_management.Service.TaskService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {

    @FXML
    public TextField taskNoField;
    @FXML
    public TextField taskNameField;
    @FXML
    public TextField taskCostField;
    @FXML
    public TextField taskFromField;
    @FXML
    public TextField dueDateField;

    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> taskNoCol;
    @FXML
    private TableColumn<Task, String> taskNameCol;
    @FXML
    private TableColumn<Task, String> taskCostCol;
    @FXML
    private TableColumn<Task, String> taskFromCol;
    @FXML
    private TableColumn<Task, String> dueDateCol;
    @FXML
    public Task selectedTask;

    private ObservableList<Task> taskObservableList;

    @FXML
    public void exit() {
        HelloApplication.changeScene("admin_dashboard");
    }

    @FXML
    public void save() {
        String taskNo = taskNoField.getText();
        String taskName = taskNameField.getText();
        String taskCost = taskCostField.getText();
        String taskFrom = taskFromField.getText();
        String dueDate = dueDateField.getText();

        TaskService taskService = new TaskService();
        Task task = new Task(taskNo, taskName, taskCost, taskFrom, dueDate);

        boolean isSaved = taskService.save(task);
        if (isSaved) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Task saved successfully.");
            alert.showAndWait();

            taskObservableList.add(task);
            clearFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to save the task.");
            alert.showAndWait();
        }
    }

    @FXML
    public void update() {
        if (selectedTask != null) {
            selectedTask.setTaskNo(taskNoField.getText());
            selectedTask.setTaskName(taskNameField.getText());
            selectedTask.setTaskCost(taskCostField.getText());
            selectedTask.setTaskFrom(taskFromField.getText());
            selectedTask.setDueDate(dueDateField.getText());

            TaskService taskService = new TaskService();
            boolean isUpdated = taskService.update(selectedTask);
            if (isUpdated) {
                taskTable.refresh();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Task updated successfully.");
                alert.showAndWait();

                clearFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to update the task.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void delete() {
        if (selectedTask != null) {
            TaskService taskService = new TaskService();
            boolean isDeleted = taskService.delete(selectedTask.getTaskNo());
            if (isDeleted) {
                taskObservableList.remove(selectedTask);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Task deleted successfully.");
                alert.showAndWait();

                clearFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to delete the task.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void clear() {
        clearFields();
    }

    private void clearFields() {
        taskNoField.setText("");
        taskNameField.setText("");
        taskCostField.setText("");
        taskFromField.setText("");
        dueDateField.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskNoCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTaskNo()));
        taskNameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTaskName()));
        taskCostCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTaskCost()));
        taskFromCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTaskFrom()));
        dueDateCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDueDate()));

        taskObservableList = FXCollections.observableArrayList();
        TaskService taskService = new TaskService();
        taskObservableList.addAll(taskService.getTaskList());
        taskTable.setItems(taskObservableList);

        taskTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedTask = newValue;
            displaySelectedTask();
        });

    }


    private void displaySelectedTask() {
        if (selectedTask != null) {
            taskNoField.setText(selectedTask.getTaskNo());
            taskNameField.setText(selectedTask.getTaskName());
            taskCostField.setText(selectedTask.getTaskCost());
            taskFromField.setText(selectedTask.getTaskFrom());
            dueDateField.setText(selectedTask.getDueDate());
        }
    }
}
