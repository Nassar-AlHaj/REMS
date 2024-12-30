package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import com.rems.realestatemanagement.models.Property;
import com.rems.realestatemanagement.models.services.PropertyDAOImp;
import java.io.IOException;
import java.util.List;


public class PropartyCardController {

    @FXML
    private VBox propertyCard;

    @FXML
    private FlowPane flowPane;

    private PropertyDAOImp propertyDAOImp = new PropertyDAOImp();

    @FXML
    private void initialize() {
        propertyCard.setOnMouseClicked(event -> openPropertyDetails());
        propertyCard.setOnMouseEntered(event -> propertyCard.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 0);"));
        propertyCard.setOnMouseExited(event -> propertyCard.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 0);"));

        loadProperties();
    }

    private void openPropertyDetails() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/proparty-card-details.fxml"));
            Parent propertyDetails = loader.load();
            Stage stage = (Stage) propertyCard.getScene().getWindow();
            Scene scene = new Scene(propertyDetails);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadProperties() {
        try {
            List<Property> properties = propertyDAOImp.getAllProperties();
            displayProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayProperties(List<Property> properties) {
        if (flowPane != null) {
            flowPane.getChildren().clear();
            for (Property property : properties) {
                VBox propertyCard = createPropertyCard(property);
                // Add the same hover and click effects to dynamically created cards
                propertyCard.setOnMouseEntered(event -> propertyCard.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 0);"));
                propertyCard.setOnMouseExited(event -> propertyCard.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 0);"));
                flowPane.getChildren().add(propertyCard);
            }
        }
    }

    private VBox createPropertyCard(Property property) {
        VBox card = new VBox();
        card.setPrefHeight(257.0);
        card.setPrefWidth(246.0);

        ImageView propertyImage = new ImageView();
        if (property.getImageProperty() != null && !property.getImageProperty().isEmpty()) {
            propertyImage.setImage(new Image(property.getImageProperty()));
        } else {
            propertyImage.setImage(new Image(getClass().getResource("/com/rems/realestatemanagement/img/propartycard2.png").toString()));
        }
        propertyImage.setFitHeight(120.0);
        propertyImage.setFitWidth(228.0);

        Label nameLabel = new Label(property.getPropertyName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        HBox priceBox = new HBox();
        ImageView dollarIcon = new ImageView(new Image(getClass().getResource("/com/rems/realestatemanagement/img/dollar.png").toString()));
        dollarIcon.setFitHeight(12.0);
        dollarIcon.setFitWidth(12.0);
        Label priceLabel = new Label(String.format("%.2f", property.getPrice()));
        priceLabel.setTextFill(javafx.scene.paint.Color.web("#fcb262"));
        priceBox.getChildren().addAll(dollarIcon, priceLabel);
        priceBox.setSpacing(5);

        HBox locationBox = new HBox();
        ImageView locationIcon = new ImageView(new Image(getClass().getResource("/com/rems/realestatemanagement/img/marker.png").toString()));
        locationIcon.setFitHeight(12.0);
        locationIcon.setFitWidth(12.0);
        Label locationLabel = new Label(property.getLocation());
        locationLabel.setTextFill(javafx.scene.paint.Color.web("#817b7b"));
        locationBox.getChildren().addAll(locationIcon, locationLabel);
        locationBox.setSpacing(5);

        HBox actionBox = new HBox();
        ImageView frameIcon = new ImageView(new Image(getClass().getResource("/com/rems/realestatemanagement/img/Frame.png").toString()));
        frameIcon.setFitHeight(20.0);
        frameIcon.setFitWidth(20.0);
        ImageView removeIcon = new ImageView(new Image(getClass().getResource("/com/rems/realestatemanagement/img/Remove.png").toString()));
        removeIcon.setFitHeight(20.0);
        removeIcon.setFitWidth(20.0);
        actionBox.getChildren().addAll(frameIcon, removeIcon);
        actionBox.setSpacing(10);
        actionBox.setPadding(new Insets(0, 10, 5, 0)); // Adjust padding as needed
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        card.getChildren().addAll(propertyImage, nameLabel, priceBox, locationBox, actionBox);
        card.setSpacing(10);
        card.setPadding(new Insets(10));

        return card;
    }

}