module com.rems.realestatemanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires mysql.connector.java;
    requires java.sql;
    requires java.naming;

    opens com.rems.realestatemanagement to org.hibernate.orm.core;
    opens com.rems.realestatemanagement.Controller to javafx.fxml;
    opens com.rems.realestatemanagement.models.services to org.hibernate.orm.core;
    exports com.rems.realestatemanagement;
    exports com.rems.realestatemanagement.Controller;
    exports com.rems.realestatemanagement.models.services to org.hibernate.orm.core;
}

