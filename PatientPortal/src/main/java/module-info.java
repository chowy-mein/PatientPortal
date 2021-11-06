module com.example.patientportal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires java.sql;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires java.sql.rowset;

    opens com.example.patientportal to javafx.fxml;
    exports com.example.patientportal;

}