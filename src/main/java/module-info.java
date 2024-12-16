module com.rems.realestatemanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.rems.realestatemanagement to javafx.fxml;
    opens com.rems.realestatemanagement.Controller to javafx.fxml;
    exports com.rems.realestatemanagement;
}