module com.rems.realestatemanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.desktop;

    opens com.rems.realestatemanagement to javafx.fxml;
    opens com.rems.realestatemanagement.Controller to javafx.fxml;
    opens com.rems.realestatemanagement.models to org.hibernate.orm.core;

    exports com.rems.realestatemanagement;
    exports com.rems.realestatemanagement.Controller;
    exports com.rems.realestatemanagement.models;
}