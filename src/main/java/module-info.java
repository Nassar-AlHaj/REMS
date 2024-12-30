
module com.rems.realestatemanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires mysql.connector.java;

    opens com.rems.realestatemanagement.Controller to javafx.fxml;
    exports com.rems.realestatemanagement;
    exports com.rems.realestatemanagement.Controller;

    opens com.rems.realestatemanagement.models to org.hibernate.orm.core;

}
