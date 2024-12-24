package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class PropartyCardController {

    @FXML
    private VBox propertyCard1;

    @FXML
    private void initialize() {
        propertyCard1.setOnMouseClicked(event -> openPropertyDetails());
        propertyCard1.setOnMouseEntered(event -> propertyCard1.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 0);"));
        propertyCard1.setOnMouseExited(event -> propertyCard1.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 0);"));
    }

    private void openPropertyDetails() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/proparty-card-details.fxml"));
            Parent propertyDetails = loader.load();
            Stage stage = (Stage) propertyCard1.getScene().getWindow();
            Scene scene = new Scene(propertyDetails);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}