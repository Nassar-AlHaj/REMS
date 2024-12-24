package com.rems.realestatemanagement.Controller;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class PropartyCardDetailsController {
    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        backButton.setOnAction(event -> goBack());
        backButton.setOnMouseEntered(event -> backButton.setOpacity(1.0));
        backButton.setOnMouseExited(event -> backButton.setOpacity(0.6));
    }

    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/DashboardDesignSearchAndFiltering.fxml"));
            Parent propertyList = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(propertyList));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}