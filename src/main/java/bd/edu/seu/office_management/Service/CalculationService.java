package bd.edu.seu.office_management.Service;

import bd.edu.seu.office_management.Model.BudgetCalculation;
import bd.edu.seu.office_management.Singleton.Singleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalculationService {
    public boolean save(BudgetCalculation budgetCalculation, double totalIncome, double totalExpense, double finalBalance) {
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();

            String query = "INSERT INTO budgetCalculation (item, cost, total_income, total_expense, final_balance) " +
                    "VALUES ('" + budgetCalculation.getItem() + "', " + budgetCalculation.getCost() + ", " +
                    totalIncome + ", " + totalExpense + ", " + finalBalance + ");";

            statement.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to fetch all budget calculations from the database
    public List<BudgetCalculation> getAllBudgetCalculations() {
        List<BudgetCalculation> budgetCalculations = new ArrayList<>();
        try {
            Connection connection = Singleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM budgetCalculation";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String item = resultSet.getString("item");
                double cost = resultSet.getDouble("cost");
                double totalIncome = resultSet.getDouble("total_income");
                double totalExpense = resultSet.getDouble("total_expense");
                double finalBalance = resultSet.getDouble("final_balance");

                BudgetCalculation budgetCalculation = new BudgetCalculation(item, cost, totalIncome, totalExpense, finalBalance);
                budgetCalculations.add(budgetCalculation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return budgetCalculations;
    }
}
