package bd.edu.seu.office_management.Model;

public class Task {
    private String taskNo;
    private String taskName;
    private String taskCost;
    private String taskFrom;
    private String dueDate;

    public Task(String taskNo, String taskName, String taskCost, String taskFrom, String dueDate) {
        this.taskNo = taskNo;
        this.taskName = taskName;
        this.taskCost = taskCost;
        this.taskFrom = taskFrom;
        this.dueDate = dueDate;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskCost() {
        return taskCost;
    }

    public void setTaskCost(String taskCost) {
        this.taskCost = taskCost;
    }

    public String getTaskFrom() {
        return taskFrom;
    }

    public void setTaskFrom(String taskFrom) {
        this.taskFrom = taskFrom;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
