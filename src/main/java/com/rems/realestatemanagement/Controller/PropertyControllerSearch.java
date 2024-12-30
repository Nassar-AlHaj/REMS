package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.Controller.Property;
import com.rems.realestatemanagement.models.interfaces.PropertyDAO;
import com.rems.realestatemanagement.models.services.PropertyDAOImp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyControllerSearch {
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
    private PropertyDAOImp propertyDAO;


    public void initialize() {
        propertyDAO = new PropertyDAOImp();



        locationComboBox.setItems(FXCollections.observableArrayList("All", "New York", "Los Angeles", "Chicago"));
        typeComboBox.setItems(FXCollections.observableArrayList("All", "Residential", "Commercial"));

        List<Property> filter = allProperties.stream()
                .filter(p-> (locationComboBox.getValue() == null || locationComboBox.getValue().equals("All")))
                .filter(p -> (typeComboBox.getValue() == null || typeComboBox.getValue().equals("All")))
                .filter(p -> (availabilityComboBox.getValue() == null || availabilityComboBox.getValue().equals("All")))
                .collect(Collectors.toList());
        propertyTable.setItems(FXCollections.observableArrayList(filter));


    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().toLowerCase();
        String location = locationComboBox.getValue();
        String type = typeComboBox.getValue();
        boolean isAvailable = "Available".equals(availabilityComboBox.getValue());

        List<Property> filtered = allProperties.stream()
                .filter(p -> (keyword.isEmpty() || p.getTitle().toLowerCase().contains(keyword)))
                .filter(p -> (location == null || location.equals("All") || p.getLocation().equals(location)))
                .filter(p -> (type == null || type.equals("All") || p.getType().equals(type)))
                .filter(p -> (availabilityComboBox.getValue() == null || availabilityComboBox.getValue().equals("All") || p.isAvailable() == isAvailable))
                .collect(Collectors.toList());

        propertyTable.setItems(FXCollections.observableArrayList(filtered));
    }

}


