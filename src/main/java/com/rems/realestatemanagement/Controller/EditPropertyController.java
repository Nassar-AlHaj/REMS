package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.models.Property;
import com.rems.realestatemanagement.models.services.PropertyDAOImp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class EditPropertyController {

    @FXML
    private ComboBox<String> propertyTypeComboBox;

    @FXML
    private TextField propertyNameField;

    @FXML
    private TextArea propertyDescriptionField;

    @FXML
    private TextField propertyLocationField;

    @FXML
    private TextField propertyPriceField;

    @FXML
    private TextField propertyRoomsField;

    @FXML
    private Button updateImageButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView propertyImageView;

    private String imagePath;
    private PropertyDAOImp propertyDAO;

    @FXML
    public void initialize() {
        propertyDAO = new PropertyDAOImp();

        propertyTypeComboBox.getItems().addAll(
                "Residential",
                "Commercial",
                "Industrial",
                "Agricultural",
                "Land"
        );

        updateImageButton.setOnAction(event -> handleUpdateImage());
        saveButton.setOnAction(event -> handleSave());
        cancelButton.setOnAction(event -> handleCancel());
    }

    private void handleUpdateImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select New Property Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) updateImageButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            imagePath = selectedFile.toURI().toString();
            Image image = new Image(imagePath);
            propertyImageView.setImage(image);
        }
    }

    private void handleSave() {
        try {
            String propertyType = propertyTypeComboBox.getValue();
            String propertyName = propertyNameField.getText();
            String propertyDescription = propertyDescriptionField.getText();
            String propertyLocation = propertyLocationField.getText();
            double propertyPrice = Double.parseDouble(propertyPriceField.getText());
            int propertyRooms = Integer.parseInt(propertyRoomsField.getText());

            Property property = new Property();
            property.setPropertyType(propertyType);
            property.setPropertyName(propertyName);
            property.setDescription(propertyDescription);
            property.setLocation(propertyLocation);
            property.setPrice(propertyPrice);
            property.setNumberOfRooms(propertyRooms);
            property.setImageProperty(imagePath);

            int propertyId = propertyDAO.insert(property);
            if (propertyId > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Property details saved successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save property details.");
            }

            clearFields();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter valid details. " + e.getMessage());
        }
    }

    private void handleCancel() {
        clearFields();
        System.out.println("Editing cancelled.");
    }

    private void clearFields() {
        propertyTypeComboBox.setValue(null);
        propertyNameField.clear();
        propertyDescriptionField.clear();
        propertyLocationField.clear();
        propertyPriceField.clear();
        propertyRoomsField.clear();
        propertyImageView.setImage(null);
        imagePath = null;
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}