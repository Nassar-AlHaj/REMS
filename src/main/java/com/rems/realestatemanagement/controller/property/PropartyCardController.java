package com.rems.realestatemanagement.controller.property;

import com.rems.realestatemanagement.models.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.rems.realestatemanagement.models.services.PropertyDAOImp;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PropartyCardController {

    @FXML private TextField Enter_Keyword;
    @FXML private MenuButton Location;
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

    @FXML
    private FlowPane flowPaneCard;

    private final PropertyDAOImp propertyDAOImp = new PropertyDAOImp();
    @FXML
    private void initialize() {
        loadProperties();
        Availability.setValue("Availability");
        Availability.getItems().addAll("Available", "Not Available");

        initializeLocations();
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
        if (flowPaneCard != null) {
            flowPaneCard.getChildren().clear();
            for (Property property : properties) {
                VBox propertyCard = createPropertyCard(property);

                propertyCard.setOnMouseClicked(event -> {
                    event.consume();
                    showPropertyDetailsPopup(property);
                });

                propertyCard.setOnMouseEntered(event ->
                        propertyCard.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 0);"));
                propertyCard.setOnMouseExited(event ->
                        propertyCard.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 0);"));

                flowPaneCard.getChildren().add(propertyCard);
            }
        }
    }


    private void showPropertyDetailsPopup(Property property) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/proparty-card-details.fxml"));
            Parent propertyDetails = loader.load();

            PropartyCardDetailsController detailsController = loader.getController();
            detailsController.setPropertyData(property);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Property Details");
            Scene scene = new Scene(propertyDetails);
            popupStage.setWidth(1000);
            popupStage.setHeight(450);
            popupStage.setResizable(false);
            popupStage.setScene(scene);
            popupStage.centerOnScreen();
            popupStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to open property details.");
            alert.showAndWait();
        }
    }




    private void initializeLocations() {
        List<String> locations = propertyDAOImp.getAllLocations();
        Location.getItems().clear();

        CheckMenuItem allLocationsItem = new CheckMenuItem("All");
        allLocationsItem.setSelected(true);
        Location.getItems().add(allLocationsItem);

        if (locations != null) {
            locations.forEach(loc -> {
                CheckMenuItem locationItem = new CheckMenuItem(loc);
                Location.getItems().add(locationItem);
            });
        }
    }

    private VBox createPropertyCard(Property property) {
        VBox card = new VBox();
        card.setPrefHeight(257.0);
        card.setPrefWidth(246.0);
        card.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        FlowPane.setMargin(card, new Insets(15));

        StackPane imageContainer = new StackPane();
        imageContainer.setPrefHeight(150.0);
        imageContainer.setPrefWidth(200.0);
        imageContainer.setStyle("-fx-background-color: white");

        ImageView propertyImage = new ImageView();
        if (property.getImageProperty() != null && !property.getImageProperty().isEmpty()) {
            propertyImage.setImage(new Image(property.getImageProperty()));
        } else {
            propertyImage.setImage(new Image(getClass().getResource("/com/rems/realestatemanagement/img/propartycard2.png").toString()));
        }
        propertyImage.setFitHeight(120.0);
        propertyImage.setFitWidth(228.0);

        propertyImage.setOnMouseClicked(event -> {
            event.consume();
            showPropertyDetailsPopup(property);
        });

        propertyImage.setOnMouseEntered(e -> propertyImage.setCursor(Cursor.HAND));
        propertyImage.setOnMouseExited(e -> propertyImage.setCursor(Cursor.DEFAULT));

        Label stateLabel = new Label(property.getState());
        stateLabel.setAlignment(Pos.CENTER);
        stateLabel.setContentDisplay(ContentDisplay.CENTER);
        stateLabel.setPrefHeight(25.0);
        stateLabel.setPrefWidth(60.0);
        stateLabel.setStyle("-fx-background-color: #e67300;");
        stateLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        stateLabel.setFont(javafx.scene.text.Font.font("System", javafx.scene.text.FontWeight.BOLD, 12.0));
        StackPane.setMargin(stateLabel, new Insets(10, 0, 0, 10));
        StackPane.setAlignment(stateLabel, Pos.TOP_LEFT);

        imageContainer.getChildren().addAll(propertyImage, stateLabel);

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
        ImageView editIcon = new ImageView(new Image(getClass().getResource("/com/rems/realestatemanagement/img/Frame.png").toString()));
        editIcon.setFitHeight(20.0);
        editIcon.setFitWidth(20.0);
        editIcon.setOnMouseClicked(event -> {
            event.consume();
            handleEditClick(property);
        });

        ImageView deleteIcon = new ImageView(new Image(getClass().getResource("/com/rems/realestatemanagement/img/Remove.png").toString()));
        deleteIcon.setFitHeight(20.0);
        deleteIcon.setFitWidth(20.0);
        deleteIcon.setOnMouseClicked(event -> {
            event.consume();
            handleDeleteClick(property, card);
        });

        actionBox.getChildren().addAll(editIcon, deleteIcon);
        actionBox.setSpacing(10);
        actionBox.setPadding(new Insets(0, 10, 5, 0));
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        card.getChildren().addAll(imageContainer, nameLabel, priceBox, locationBox, actionBox);
        card.setSpacing(10);
        card.setPadding(new Insets(10));
        card.setOnMouseClicked(event -> {
            event.consume();
            showPropertyDetailsPopup(property);
        });

        card.setOnMouseEntered(event -> {
            card.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 0);");
            card.setCursor(Cursor.HAND);
        });
        card.setOnMouseExited(event -> {
            card.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 0);");
            card.setCursor(Cursor.DEFAULT);
        });

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
        for (int i = 0; i < flowPaneCard.getChildren().size(); i++) {
            VBox card = (VBox) flowPaneCard.getChildren().get(i);
            Label nameLabel = (Label) card.getChildren().get(1);
            if (nameLabel.getText().equals(updatedProperty.getPropertyName())) {
                VBox updatedCard = createPropertyCard(updatedProperty);
                flowPaneCard.getChildren().set(i, updatedCard);
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
                    flowPaneCard.getChildren().remove(card);

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

    public void Filtering() {
        try {

            List<Property> properties = propertyDAOImp.getAllProperties();
            properties = applyKeywordFilter(properties);
            properties = applyLocationFilter(properties);

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
    private List<Property> applyKeywordFilter(List<Property> properties) {
        String keyword = Enter_Keyword.getText().trim().toLowerCase();
        if (!keyword.isEmpty()) {
            return properties.stream()
                    .filter(p -> p.getPropertyName().toLowerCase().contains(keyword) ||
                            p.getLocation().toLowerCase().contains(keyword) ||
                            p.getDescription().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());
        }
        return properties;
    }
    private List<Property> applyLocationFilter(List<Property> properties) {
        List<String> selectedLocations = Location.getItems().stream()
                .filter(item -> item instanceof CheckMenuItem && ((CheckMenuItem) item).isSelected())
                .map(MenuItem::getText)
                .collect(Collectors.toList());

        if (!selectedLocations.contains("All")) {
            return properties.stream()
                    .filter(p -> selectedLocations.contains(p.getLocation()))
                    .collect(Collectors.toList());
        }
        return properties;
    }

}

