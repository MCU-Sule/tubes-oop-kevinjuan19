module com.teststackpane {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;



    opens Controller to javafx.fxml;
    exports Controller;
    exports Model;
    opens Model to javafx.fxml;


}