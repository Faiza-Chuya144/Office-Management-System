package bd.edu.seu.office_management.Model;

public class BudgetCalculation {

    private String item;
    private double cost;
    private double totalIncome;
    private double totalExpense;
    private double finalBalance;

    // Constructor
    public BudgetCalculation(String item, double cost, double totalIncome, double totalExpense, double finalBalance) {
        this.item = item;
        this.cost = cost;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.finalBalance = finalBalance;
    }

    // Constructor for saving new expense
    public BudgetCalculation(String item, double cost) {
        this(item, cost, 0, 0, 0); // Defaults for totals
    }

    // Getters and setters
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(double finalBalance) {
        this.finalBalance = finalBalance;
    }

    // ToString method for debugging and logging purposes
    @Override
    public String toString() {
        return "BudgetCalculation{" + "item='" + item + '\'' + ", cost=" + cost + ", totalIncome=" + totalIncome + ", totalExpense=" + totalExpense + ", finalBalance=" + finalBalance + '}';
    }
}
