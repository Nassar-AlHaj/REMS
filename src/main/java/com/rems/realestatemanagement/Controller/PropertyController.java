package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.models.Property;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyController {
    @FXML private TextField searchField;
    @FXML private ComboBox<String> locationComboBox;
    @FXML private TextField minPriceField;
    @FXML private TextField maxPriceField;
    @FXML private TextField sizeField;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private ComboBox<String> availabilityComboBox;
    @FXML private TableView<Property> propertyTable;
    @FXML private TableColumn<Property, String> titleColumn;
    @FXML private TableColumn<Property, String> locationColumn;
    @FXML private TableColumn<Property, Double> priceColumn;
    @FXML private TableColumn<Property, Double> sizeColumn;
    @FXML private TableColumn<Property, String> typeColumn;
    @FXML private TableColumn<Property, String> availabilityColumn;

    private ObservableList<Property> allProperties = FXCollections.observableArrayList();

    public void initialize() {
        // Sample Data
        allProperties.addAll(
                new Property("House A", "New York", 300000, 120, "Residential", true),
                new Property("Office X", "Los Angeles", 500000, 300, "Commercial", false),
                new Property("Apartment B", "Chicago", 200000, 80, "Residential", true)
        );

        // Load data into table
        propertyTable.setItems(allProperties);

        // Initialize ComboBoxes
        locationComboBox.setItems(FXCollections.observableArrayList("All", "New York", "Los Angeles", "Chicago"));
        typeComboBox.setItems(FXCollections.observableArrayList("All", "Residential", "Commercial"));

        // TableColumn Value Factory
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        locationColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLocation()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()).asObject());
        sizeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getSize()).asObject());
        typeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getType()));
        availabilityColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().isAvailable() ? "Available" : "Not Available"));
    }

    @FXML
    private void handleSearch() {
        // Get filter criteria
        String keyword = searchField.getText().toLowerCase();
        String location = locationComboBox.getValue();
        String type = typeComboBox.getValue();
        boolean isAvailable = "Available".equals(availabilityComboBox.getValue());
        String minPrice = minPriceField.getText();
        String maxPrice = maxPriceField.getText();

        // Filter Logic
        List<Property> filtered = allProperties.stream()
                .filter(p -> (keyword.isEmpty() || p.getTitle().toLowerCase().contains(keyword)))
                .filter(p -> (location == null || location.equals("All") || p.getLocation().equals(location)))
                .filter(p -> (type == null || type.equals("All") || p.getType().equals(type)))
                .filter(p -> (availabilityComboBox.getValue() == null || availabilityComboBox.getValue().equals("All") || p.isAvailable() == isAvailable))
                .filter(p -> minPrice.isEmpty() || p.getPrice() >= Double.parseDouble(minPrice))
                .filter(p -> maxPrice.isEmpty() || p.getPrice() <= Double.parseDouble(maxPrice))
                .collect(Collectors.toList());

        // Update table
        propertyTable.setItems(FXCollections.observableArrayList(filtered));
    }

}