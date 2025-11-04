package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import bd.edu.seu.office_management.Model.AddEmployee;
import bd.edu.seu.office_management.Service.EmployeeService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    @FXML
    public TextField idField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField birthField;
    @FXML
    public TextField phoneField;
    @FXML
    public TextField addressField;
    @FXML
    public TextField searchInput;
    @FXML
    public ToggleGroup genderToggleGroup;
    @FXML
    private TableView<AddEmployee> employeeTable;
    @FXML
    private TableColumn<AddEmployee, String> idCol;
    @FXML
    private TableColumn<AddEmployee,String> nameCol;
    @FXML
    private TableColumn<AddEmployee, String> birthCol;
    @FXML
    private TableColumn<AddEmployee, String> phoneCol;
    @FXML
    private TableColumn<AddEmployee, String> addressCol;
    @FXML
    private TableColumn<AddEmployee, String> genderCol;
    @FXML
    public AddEmployee selectedEmployee;

    private ObservableList<AddEmployee> employeeObservableList;
    @FXML
    public void exit(){

        HelloApplication.changeScene("admin_dashboard");
    }

    @FXML
    public void save() {

        String id = idField.getText();
        String name = nameField.getText();
        String birth = birthField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        RadioButton genderRadio = (RadioButton) genderToggleGroup.getSelectedToggle();
        String gender = genderRadio.getText();


        EmployeeService employeeService = new EmployeeService();
        AddEmployee employee = new AddEmployee(id, name,birth, phone, address,gender);

        boolean isSaved = employeeService.save(employee);
        if (isSaved) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Employee saved successfully.");
            alert.showAndWait();

            employeeObservableList.add(employee);
            clearFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to save the employee.");
            alert.showAndWait();
        }
    }

    @FXML
    public void update() {
        if (selectedEmployee != null) {
            selectedEmployee.setId(idField.getText());
            selectedEmployee.setName(nameField.getText());
            selectedEmployee.setBirth(birthField.getText());
            selectedEmployee.setPhone(phoneField.getText());
            selectedEmployee.setAddress(addressField.getText());

            RadioButton genderRadio = (RadioButton) genderToggleGroup.getSelectedToggle();
            String gender = genderRadio.getText();
            selectedEmployee.setGender(gender);


            EmployeeService employeeService = new EmployeeService();
            boolean isUpdated = employeeService.update(selectedEmployee);
            if (isUpdated) {
                employeeTable.refresh(); // Refresh the TableView
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Employee updated successfully.");
                alert.showAndWait();

                clearFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to update the Employee.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void delete() {
        if (selectedEmployee != null) {
            EmployeeService employeeService = new EmployeeService();
            boolean isDeleted = employeeService.delete(selectedEmployee.getId());
            if (isDeleted) {
                employeeObservableList.remove(selectedEmployee);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Employee deleted successfully.");
                alert.showAndWait();

                clearFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to delete the Employee.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void clear() {
        clearFields();
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        birthField.setText("");
        phoneField.setText("");
        addressField.setText("");
        if (genderToggleGroup.getSelectedToggle() != null) {
            genderToggleGroup.getSelectedToggle().setSelected(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getId()));
        nameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        birthCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getBirth()));
        phoneCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPhone()));
        addressCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAddress()));
        genderCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGender()));

        employeeObservableList = FXCollections.observableArrayList();
        EmployeeService employeeService = new EmployeeService();
        employeeObservableList.addAll(employeeService.getEmployeeList());
        employeeTable.setItems(employeeObservableList);


        employeeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedEmployee = newValue;
            displaySelectedEmployee();
        });

        searchInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    search();
                }
            }
        });
    }

    public void search() {
        String searchText = searchInput.getText().toLowerCase();
        ObservableList<AddEmployee> searchResult = FXCollections.observableArrayList();
        for (AddEmployee employee : employeeObservableList) {
            if (employee.getName().toLowerCase().contains(searchText) || employee.getId().toLowerCase().contains(searchText)) {
                searchResult.add(employee);
            }
        }
        employeeTable.setItems(searchResult);
    }

    private void displaySelectedEmployee() {
        if (selectedEmployee != null) {
            idField.setText(selectedEmployee.getId());
            nameField.setText(selectedEmployee.getName());
            birthField.setText(selectedEmployee.getBirth());
            phoneField.setText(selectedEmployee.getPhone());
            addressField.setText(selectedEmployee.getAddress());

            // Set gender toggle group based on selected patient's gender
            for (Toggle toggle : genderToggleGroup.getToggles()) {
                RadioButton radioButton = (RadioButton) toggle;
                if (radioButton.getText().equals(selectedEmployee.getGender())) {
                    radioButton.setSelected(true);
                    break;
                }
            }
        }
    }
}
