package bd.edu.seu.office_management.Controller;

import bd.edu.seu.office_management.HelloApplication;
import bd.edu.seu.office_management.Model.BudgetCalculation;
import bd.edu.seu.office_management.Model.Task;
import bd.edu.seu.office_management.Service.CalculationService;
import bd.edu.seu.office_management.Service.TaskService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class BudgetCalculationController {

    @FXML
    private TextField itemField;

    @FXML
    private TextField costField;

    @FXML
    private TableView<Task> incomeTable;

    @FXML
    private TableColumn<Task, String> taskNameCol;

    @FXML
    private TableColumn<Task, Double> taskCostCol;

    @FXML
    private TableView<BudgetCalculation> expenseTable;

    @FXML
    private TableColumn<BudgetCalculation, String> itemCol;

    @FXML
    private TableColumn<BudgetCalculation, Double> costCol;

    @FXML
    private TextField incomeField;

    @FXML
    private TextField expenseField;

    @FXML
    private TextField balanceField;

    private final ObservableList<Task> taskData = FXCollections.observableArrayList();
    private final ObservableList<BudgetCalculation> expenseData = FXCollections.observableArrayList();

    private final CalculationService calculationService = new CalculationService();

    @FXML
    public void initialize() {
        // Initialize TaskService to fetch tasks
        TaskService taskService = new TaskService();
        taskData.addAll(taskService.getTaskList());

        // Configure columns for the income table
        taskNameCol.setCellValueFactory(data -> Bindings.createStringBinding(data.getValue()::getTaskName));
        taskCostCol.setCellValueFactory(data -> Bindings.createDoubleBinding(
                () -> Double.parseDouble(data.getValue().getTaskCost())).asObject());

        // Bind income table to task data
        incomeTable.setItems(taskData);

        // Configure columns for the expense table
        itemCol.setCellValueFactory(data -> Bindings.createStringBinding(data.getValue()::getItem));
        costCol.setCellValueFactory(data -> Bindings.createDoubleBinding(data.getValue()::getCost).asObject());

        // Fetch and load saved budget calculations
        loadExpenseData();

        // Bind expense table to expense data
        expenseTable.setItems(expenseData);

        // Bind total income to text field
        incomeField.textProperty().bind(Bindings.format("%.2f", Bindings.createDoubleBinding(
                () -> taskData.stream().mapToDouble(task -> Double.parseDouble(task.getTaskCost())).sum(), taskData)));

        // Bind total expense to text field
        expenseField.textProperty().bind(Bindings.format("%.2f", Bindings.createDoubleBinding(
                () -> expenseData.stream().mapToDouble(BudgetCalculation::getCost).sum(), expenseData)));

        // Bind total balance (income - expense)
        balanceField.textProperty().bind(Bindings.format("%.2f", Bindings.createDoubleBinding(() ->
                taskData.stream().mapToDouble(task -> Double.parseDouble(task.getTaskCost())).sum()
                        - expenseData.stream().mapToDouble(BudgetCalculation::getCost).sum(), taskData, expenseData)));
    }

    // Method to load saved expense data
    private void loadExpenseData() {
        List<BudgetCalculation> savedData = calculationService.getAllBudgetCalculations();
        expenseData.addAll(savedData);
    }

    @FXML
    void save(ActionEvent event) {
        String item = itemField.getText();
        double cost;

        try {
            cost = Double.parseDouble(costField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid cost!");
            alert.show();
            return;
        }

        // Add expense to the table
        BudgetCalculation newExpense = new BudgetCalculation(item, cost, 0, 0, 0); // Temporary values for totals
        expenseData.add(newExpense);

        // Save the new expense to the database
        double totalIncome = taskData.stream().mapToDouble(task -> Double.parseDouble(task.getTaskCost())).sum();
        double totalExpense = expenseData.stream().mapToDouble(BudgetCalculation::getCost).sum();
        double finalBalance = totalIncome - totalExpense;
        boolean success = calculationService.save(newExpense, totalIncome, totalExpense, finalBalance);

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data saved successfully!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to save data!");
            alert.show();
        }

        // Refresh the table
        expenseTable.refresh();

        // Clear input fields
        itemField.clear();
        costField.clear();
    }

    @FXML
    void exit(ActionEvent event) {
        HelloApplication.changeScene("admin_dashboard");
    }
}
