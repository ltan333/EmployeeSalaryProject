module com.anlee.employeemanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.anlee.employeemanagement to javafx.fxml;
    exports com.anlee.employeemanagement;
}
