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
    requires java.naming;
    requires java.sql;
    requires mysql.connector.java;
    requires com.google.protobuf;
    requires java.mail;
    requires jbcrypt;
    opens com.rems.realestatemanagement.models;
    exports com.rems.realestatemanagement;
    opens com.rems.realestatemanagement.Controller.auth to javafx.fxml;
    opens com.rems.realestatemanagement.Controller.agent to javafx.fxml;
    opens com.rems.realestatemanagement.Controller.property to javafx.fxml;
    opens com.rems.realestatemanagement.Controller.offer to javafx.fxml;
    opens com.rems.realestatemanagement.Controller.client to javafx.fxml;
    opens com.rems.realestatemanagement.Controller.common to javafx.fxml;
    opens com.rems.realestatemanagement.Controller.dashboard to javafx.fxml;
}