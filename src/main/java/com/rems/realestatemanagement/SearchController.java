package com.rems.realestatemanagement;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SearchController {
    @FXML
    private TextField keywordField;
    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField filterField;

    @FXML
    private Button searchButton;

    @FXML
    private void initialize() {
        // Set default values
        statusComboBox.setValue("All Status");
        typeComboBox.setValue("All Type");
    }

    @FXML
    private void handleSearchAction() {
        // Fetch input data
        String keyword = keywordField.getText();
        String status = statusComboBox.getValue();
        String type = typeComboBox.getValue();
        String filter = filterField.getText();

        // Simulate processing search input
        System.out.println("Search Parameters:");
        System.out.println("Keyword: " + keyword);
        System.out.println("Status: " + status);
        System.out.println("Type: " + type);
        System.out.println("Filter: " + filter);

        // Here you could integrate logic to perform actions (e.g., search in database)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Search Executed");
        alert.setHeaderText("Search Input Received");
        alert.setContentText("Results processed with your input.");
        alert.showAndWait();
    }
}