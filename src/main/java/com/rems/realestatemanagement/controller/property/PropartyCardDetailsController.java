package com.rems.realestatemanagement.controller.property;

import com.rems.realestatemanagement.models.Property;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
    private Label propertyNameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label stateLabel;

    @FXML
    private ImageView propertyImageView;

    @FXML
    private Text InformationText;

    private Property property;

    @FXML
    private void initialize() {

    }

    public void setPropertyData(Property property) {
        if (property == null) {
            System.out.println("Warning: Null property passed to details controller");
            return;
        }
        this.property = property;
        if (propertyNameLabel == null || priceLabel == null ||
                locationLabel == null || stateLabel == null ||
                propertyImageView == null || InformationText == null ) {
            System.out.println("Warning: One or more FXML elements not properly injected");
            return;
        }
        displayPropertyDetails();
    }

    private void displayPropertyDetails() {
        if (property != null) {
            propertyNameLabel.setText(property.getPropertyName());
            priceLabel.setText(String.format("$%.2f", property.getPrice()));
            locationLabel.setText(property.getLocation());
            stateLabel.setText(property.getState());
            InformationText.setText(property.getDescription());

            if (property.getImageProperty() != null && !property.getImageProperty().isEmpty()) {
                propertyImageView.setImage(new Image(property.getImageProperty()));
            } else {
                propertyImageView.setImage(new Image(getClass().getResource(
                        "/com/rems/realestatemanagement/img/propartycard2.png").toString()));
            }
        }
    }

    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/proparty-card.fxml"));
            Parent propertyList = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(propertyList));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}