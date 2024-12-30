package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import com.rems.realestatemanagement.models.Property;
import com.rems.realestatemanagement.models.services.PropertyDAOImp;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class PropartyCardController {
    @FXML private TextField Enter_Keyword;
    @FXML private MenuButton Location;
    @FXML private MenuButton Type;
    @FXML private MenuButton Filter;
    @FXML private CheckMenuItem Price_above;
    @FXML private CheckMenuItem Price_below;
    @FXML private CheckMenuItem Latest;
    @FXML private CheckMenuItem Oldest;
    @FXML private CheckMenuItem All;
    @FXML private CheckMenuItem Residential;
    @FXML private CheckMenuItem Commercial;
    @FXML private CheckMenuItem Industrial;
    @FXML private CheckMenuItem Agricultural;
    @FXML private CheckMenuItem Land;
    @FXML private CheckMenuItem AllLocation;
    @FXML private CheckMenuItem New_York;
    @FXML private CheckMenuItem Los_Angeles;
    @FXML private CheckMenuItem Chicago;
    @FXML private ChoiceBox Availability;
    @FXML private Button Search;

    @FXML
    private VBox propertyCard;

    @FXML
    private FlowPane flowPane;

    private PropertyDAOImp propertyDAOImp = new PropertyDAOImp();

    @FXML
    private void initialize() {

        Availability.setValue("Availability");
        Availability.getItems().addAll("Available", "Not Available");



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


    public void Filtering() {
        try {
            List<Property> properties = propertyDAOImp.getAllProperties();

            if (Price_above.isSelected() && !Price_below.isSelected()) {
                properties = properties.stream()
                        .filter(p -> p.getPrice() > 0)
                        .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                        .collect(Collectors.toList());
            } else if (!Price_above.isSelected() && Price_below.isSelected()) {
                properties = properties.stream()
                        .filter(p -> p.getPrice() > 0)
                        .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                        .collect(Collectors.toList());
            }

            if (Latest.isSelected()) {
                properties = properties.stream()
                        .sorted((p1, p2) -> p2.getlistingDate().compareTo(p1.getlistingDate()))
                        .collect(Collectors.toList());
            } else if (Oldest.isSelected()) {
                properties = properties.stream()
                        .sorted((p1, p2) -> p1.getlistingDate().compareTo(p2.getlistingDate()))
                        .collect(Collectors.toList());
            }

            if (All.isSelected()) {
                displayProperties(properties);
            }else {
                if (Residential.isSelected()) {
                    properties = properties.stream()
                            .filter(p -> p.getPropertyType().equals("Residential"))
                            .collect(Collectors.toList());
                }

                if (Commercial.isSelected()) {
                    properties = properties.stream()
                            .filter(p -> p.getPropertyType().equals("Commercial"))
                            .collect(Collectors.toList());
                }

                if (Industrial.isSelected()) {
                    properties = properties.stream()
                            .filter(p -> p.getPropertyType().equals("Industrial"))
                            .collect(Collectors.toList());
                }

                if (Agricultural.isSelected()) {
                    properties = properties.stream()
                            .filter(p -> p.getPropertyType().equals("Agricultural"))
                            .collect(Collectors.toList());
                }

                if (Land.isSelected()) {
                    properties = properties.stream()
                            .filter(p -> p.getPropertyType().equals("Land"))
                            .collect(Collectors.toList());
                }
            }

            if (AllLocation.isSelected()) {
                displayProperties(properties);
            }else {
                if (New_York.isSelected()) {
                    properties = properties.stream()
                            .filter(p-> p.getLocation().equals("New York"))
                            .collect(Collectors.toList());
                }
                if (Los_Angeles.isSelected()) {
                    properties = properties.stream()
                            .filter(p-> p.getLocation().equals("Los Angeles"))
                            .collect(Collectors.toList());
                }
                if (Chicago.isSelected()) {
                    properties = properties.stream()
                            .filter(p-> p.getLocation().equals("Chicago"))
                            .collect(Collectors.toList());
                }
            }

            if (Availability.getValue().equals("Available")) {
                properties = properties.stream()
                        .filter(p -> p.getState().equals("Available"))
                        .collect(Collectors.toList());
            } else if (Availability.getValue().equals("Not Available")) {
                properties = properties.stream()
                        .filter(p -> p.getState().equals("Rented"))
                        .filter(p -> p.getState().equals("sold"))
                        .collect(Collectors.toList());
            }

            displayProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}