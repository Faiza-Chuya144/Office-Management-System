package bd.edu.seu.office_management.Model;

import java.time.LocalDate;

public class TaskAssign {
    private String employeeName;
    private String taskName;
    private LocalDate assignDate;
    private LocalDate dueDate;
    private String status;

    public TaskAssign(String employeeName, String taskName, LocalDate assignDate, LocalDate dueDate, String status) {
        this.employeeName = employeeName;
        this.taskName = taskName;
        this.assignDate = assignDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(LocalDate assignDate) {
        this.assignDate = assignDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
