package bd.edu.seu.office_management.Model;

public class Employee {
    private String name;
    private String id;

    private String attendance;


    public Employee(String name, String id, String attendance) {
        this.name = name;
        this.id = id;
        this.attendance = attendance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}