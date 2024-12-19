module com.rems.realestatemanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.rems.realestatemanagement.Controller to javafx.fxml;
    exports com.rems.realestatemanagement;
    exports com.rems.realestatemanagement.Controller;
}

