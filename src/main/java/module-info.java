module bd.edu.seu.office_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens bd.edu.seu.office_management to javafx.fxml;
    exports bd.edu.seu.office_management;
    exports bd.edu.seu.office_management.Model;
    opens bd.edu.seu.office_management.Model to javafx.fxml;
    exports bd.edu.seu.office_management.Controller;
    opens bd.edu.seu.office_management.Controller to javafx.fxml;
    exports bd.edu.seu.office_management.Service;
    opens bd.edu.seu.office_management.Service to javafx.fxml;
    exports bd.edu.seu.office_management.Singleton;
    opens bd.edu.seu.office_management.Singleton to javafx.fxml;
}