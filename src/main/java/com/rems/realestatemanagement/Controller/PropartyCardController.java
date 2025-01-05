package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.models.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.rems.realestatemanagement.models.services.PropertyDAOImp;

import java.io.IOException;
import java.util.List;

public class PropartyCardController {

    @FXML private TextField Enter_Keyword;
    @FXML private MenuButton Location;
    @FXML private MenuButton Type;
    @FXML private MenuButton Filter;
    @FXML private FlowPane flowPane;

    private final PropertyDAOImp propertyDAOImp = new PropertyDAOImp();

    @FXML
    private void initialize() {
        loadProperties();
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
        priceBox.getChildren().addAll(dollarIcon, priceLabel);
        priceBox.setSpacing(5);

        HBox locationBox = new HBox();
        ImageView locationIcon = new ImageView(new Image(getClass().getResource("/com/rems/realestatemanagement/img/marker.png").toString()));
        locationIcon.setFitHeight(12.0);
        locationIcon.setFitWidth(12.0);
        Label locationLabel = new Label(property.getLocation());
        locationBox.getChildren().addAll(locationIcon, locationLabel);
        locationBox.setSpacing(5);

        HBox actionBox = new HBox();
        ImageView editIcon = new ImageView(new Image(getClass().getResource("/com/rems/realestatemanagement/img/Frame.png").toString()));
        editIcon.setFitHeight(20.0);
        editIcon.setFitWidth(20.0);
        editIcon.setOnMouseClicked(event -> handleEditClick(property));

        ImageView deleteIcon = new ImageView(new Image(getClass().getResource("/com/rems/realestatemanagement/img/Remove.png").toString()));
        deleteIcon.setFitHeight(20.0);
        deleteIcon.setFitWidth(20.0);
        deleteIcon.setOnMouseClicked(event -> handleDeleteClick(property, card));

        actionBox.getChildren().addAll(editIcon, deleteIcon);
        actionBox.setSpacing(10);
        actionBox.setPadding(new Insets(0, 10, 5, 0));
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        card.getChildren().addAll(propertyImage, nameLabel, priceBox, locationBox, actionBox);
        card.setSpacing(10);
        card.setPadding(new Insets(10));

        return card;
    }

    private void handleEditClick(Property property) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/editProperty.fxml"));
            Parent editPropertyRoot = loader.load();

            EditPropertyController controller = loader.getController();
            controller.setProperty(property);
            controller.setOnSaveListener(updatedProperty -> refreshPropertyCard(updatedProperty));

            Stage stage = new Stage();
            stage.setTitle("Edit Property");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(editPropertyRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshPropertyCard(Property updatedProperty) {
        for (int i = 0; i < flowPane.getChildren().size(); i++) {
            VBox card = (VBox) flowPane.getChildren().get(i);
            Label nameLabel = (Label) card.getChildren().get(1);
            if (nameLabel.getText().equals(updatedProperty.getPropertyName())) {
                VBox updatedCard = createPropertyCard(updatedProperty);
                flowPane.getChildren().set(i, updatedCard);
                break;
            }
        }
    }

    private void handleDeleteClick(Property property, VBox card) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Deletion");
        confirmationAlert.setHeaderText("Are you sure you want to delete this property?");
        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    propertyDAOImp.delete(property);
                    flowPane.getChildren().remove(card);

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setContentText("Property deleted successfully.");
                    successAlert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setContentText("Failed to delete the property.");
                    errorAlert.showAndWait();
                }
            }
        });
    }
}