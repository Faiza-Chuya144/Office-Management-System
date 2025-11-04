package bd.edu.seu.office_management.Model;

public class EmployeeProgress
{
    private String taskCompleted;
    private String challenges;
    private String skillUtilized;
    private String nextSteps;
    private String rate;
    private String date;

    public EmployeeProgress(String taskCompleted, String challenges, String skillUtilized, String nextSteps, String rate, String date) {
        this.taskCompleted = taskCompleted;
        this.challenges = challenges;
        this.skillUtilized = skillUtilized;
        this.nextSteps = nextSteps;
        this.rate = rate;
        this.date = date;
    }

    public String getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(String taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    public String getChallenges() {
        return challenges;
    }

    public void setChallenges(String challenges) {
        this.challenges = challenges;
    }

    public String getSkillUtilized() {
        return skillUtilized;
    }

    public void setSkillUtilized(String skillUtilized) {
        this.skillUtilized = skillUtilized;
    }

    public String getNextSteps() {
        return nextSteps;
    }

    public void setNextSteps(String nextSteps) {
        this.nextSteps = nextSteps;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
