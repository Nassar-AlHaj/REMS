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
import java.util.ArrayList;
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
    @FXML private ChoiceBox<String> Availability;
    @FXML private Button Search;
    @FXML private VBox propertyCard;
    @FXML private FlowPane flowPane;

    private final PropertyDAOImp propertyDAOImp = new PropertyDAOImp();
    private static final String HOVER_EFFECT = "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 0);";
    private static final String NORMAL_EFFECT = "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 0);";

    @FXML
    private void initialize() {
        initializeAvailability();
        initializeLocations();
        setupPropertyCardEffects();
        loadProperties();
    }

    private void initializeAvailability() {
        Availability.setValue("Availability");
        Availability.getItems().addAll("Available", "Not Available");
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

    private void setupPropertyCardEffects() {
        if (propertyCard != null) {
            propertyCard.setOnMouseClicked(event -> openPropertyDetails());
            propertyCard.setOnMouseEntered(event -> propertyCard.setStyle(HOVER_EFFECT));
            propertyCard.setOnMouseExited(event -> propertyCard.setStyle(NORMAL_EFFECT));
        }
    }

    private void openPropertyDetails() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/property-card-details.fxml"));
            Parent propertyDetails = loader.load();
            Stage stage = (Stage) propertyCard.getScene().getWindow();
            stage.setScene(new Scene(propertyDetails));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading property details: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadProperties() {
        try {
            List<Property> properties = propertyDAOImp.getAllProperties();
            displayProperties(properties);
        } catch (Exception e) {
            System.err.println("Error loading properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void displayProperties(List<Property> properties) {
        if (flowPane != null) {
            flowPane.getChildren().clear();
            properties.forEach(property -> {
                VBox propertyCard = createPropertyCard(property);
                propertyCard.setOnMouseClicked(event -> openPropertyDetails());
                propertyCard.setOnMouseEntered(event -> propertyCard.setStyle(HOVER_EFFECT));
                propertyCard.setOnMouseExited(event -> propertyCard.setStyle(NORMAL_EFFECT));
                flowPane.getChildren().add(propertyCard);
            });
        }
    }

    private VBox createPropertyCard(Property property) {
        VBox card = new VBox(10);
        card.setPrefHeight(257.0);
        card.setPrefWidth(246.0);
        card.setPadding(new Insets(10));

        ImageView propertyImage = createPropertyImage(property);
        Label nameLabel = createStyledLabel(property.getPropertyName(), "-fx-font-weight: bold; -fx-font-size: 14;");
        HBox priceBox = createPriceBox(property);
        HBox locationBox = createLocationBox(property);
        HBox actionBox = createActionBox();

        card.getChildren().addAll(propertyImage, nameLabel, priceBox, locationBox, actionBox);
        return card;
    }

    private ImageView createPropertyImage(Property property) {
        ImageView propertyImage = new ImageView();
        String imagePath = property.getImageProperty();
        if (imagePath != null && !imagePath.isEmpty()) {
            propertyImage.setImage(new Image(imagePath));
        } else {
            propertyImage.setImage(new Image(getClass().getResource("/com/rems/realestatemanagement/img/propertycard2.png").toString()));
        }
        propertyImage.setFitHeight(120.0);
        propertyImage.setFitWidth(228.0);
        return propertyImage;
    }

    private Label createStyledLabel(String text, String style) {
        Label label = new Label(text);
        label.setStyle(style);
        return label;
    }

    private HBox createPriceBox(Property property) {
        HBox priceBox = new HBox(5);
        ImageView dollarIcon = createIcon("/com/rems/realestatemanagement/img/dollar.png", 12.0, 12.0);
        Label priceLabel = createStyledLabel(String.format("%.2f", property.getPrice()), "");
        priceLabel.setTextFill(javafx.scene.paint.Color.web("#fcb262"));
        priceBox.getChildren().addAll(dollarIcon, priceLabel);
        return priceBox;
    }

    private HBox createLocationBox(Property property) {
        HBox locationBox = new HBox(5);
        ImageView locationIcon = createIcon("/com/rems/realestatemanagement/img/marker.png", 12.0, 12.0);
        Label locationLabel = createStyledLabel(property.getLocation(), "");
        locationLabel.setTextFill(javafx.scene.paint.Color.web("#817b7b"));
        locationBox.getChildren().addAll(locationIcon, locationLabel);
        return locationBox;
    }

    private ImageView createIcon(String path, double height, double width) {
        ImageView icon = new ImageView(new Image(getClass().getResource(path).toString()));
        icon.setFitHeight(height);
        icon.setFitWidth(width);
        return icon;
    }

    private HBox createActionBox() {
        HBox actionBox = new HBox(10);
        ImageView frameIcon = createIcon("/com/rems/realestatemanagement/img/Frame.png", 20.0, 20.0);
        ImageView removeIcon = createIcon("/com/rems/realestatemanagement/img/Remove.png", 20.0, 20.0);
        actionBox.getChildren().addAll(frameIcon, removeIcon);
        actionBox.setPadding(new Insets(0, 10, 5, 0));
        actionBox.setAlignment(Pos.CENTER_RIGHT);
        return actionBox;
    }

    @FXML
    public void Filtering() {
        try {
            List<Property> properties = propertyDAOImp.getAllProperties();
            properties = applyKeywordFilter(properties);
            properties = applyPriceFilter(properties);
            properties = applyDateFilter(properties);
            properties = applyTypeFilter(properties);
            properties = applyLocationFilter(properties);
            properties = applyAvailabilityFilter(properties);

            displayProperties(properties);
        } catch (Exception e) {
            System.err.println("Error filtering properties: " + e.getMessage());
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

    private List<Property> applyPriceFilter(List<Property> properties) {
        if (Price_above.isSelected() && !Price_below.isSelected()) {
            return properties.stream()
                    .filter(p -> p.getPrice() > 0)
                    .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                    .collect(Collectors.toList());
        } else if (!Price_above.isSelected() && Price_below.isSelected()) {
            return properties.stream()
                    .filter(p -> p.getPrice() > 0)
                    .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                    .collect(Collectors.toList());
        }
        return properties;
    }

    private List<Property> applyDateFilter(List<Property> properties) {
        if (Latest.isSelected()) {
            return properties.stream()
                    .sorted((p1, p2) -> p2.getlistingDate().compareTo(p1.getlistingDate()))
                    .collect(Collectors.toList());
        } else if (Oldest.isSelected()) {
            return properties.stream()
                    .sorted((p1, p2) -> p1.getlistingDate().compareTo(p2.getlistingDate()))
                    .collect(Collectors.toList());
        }
        return properties;
    }

    private List<Property> applyTypeFilter(List<Property> properties) {
        if (!All.isSelected()) {
            List<Property> filteredProperties = new ArrayList<>();
            if (Residential.isSelected()) {
                filteredProperties.addAll(filterByType(properties, "Residential"));
            }
            if (Commercial.isSelected()) {
                filteredProperties.addAll(filterByType(properties, "Commercial"));
            }
            if (Industrial.isSelected()) {
                filteredProperties.addAll(filterByType(properties, "Industrial"));
            }
            if (Agricultural.isSelected()) {
                filteredProperties.addAll(filterByType(properties, "Agricultural"));
            }
            if (Land.isSelected()) {
                filteredProperties.addAll(filterByType(properties, "Land"));
            }
            return !filteredProperties.isEmpty() ? filteredProperties : properties;
        }
        return properties;
    }

    private List<Property> filterByType(List<Property> properties, String type) {
        return properties.stream()
                .filter(p -> p.getPropertyType().equals(type))
                .collect(Collectors.toList());
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

    private List<Property> applyAvailabilityFilter(List<Property> properties) {
        String availabilityValue = Availability.getValue();
        if ("Available".equals(availabilityValue)) {
            return properties.stream()
                    .filter(p -> p.getState().equals("Available"))
                    .collect(Collectors.toList());
        } else if ("Not Available".equals(availabilityValue)) {
            return properties.stream()
                    .filter(p -> p.getState().equals("Rented") || p.getState().equals("Sold"))
                    .collect(Collectors.toList());
        }
        return properties;
    }
}