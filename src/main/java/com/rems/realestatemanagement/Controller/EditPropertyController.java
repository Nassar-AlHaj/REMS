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
    private Property currentProperty;
    private PropertyDAOImp propertyDAO;

    public interface OnSaveListener {
        void onSave(Property updatedProperty);
    }

    private OnSaveListener saveListener;

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

    public void setProperty(Property property) {
        this.currentProperty = property;

        propertyTypeComboBox.setValue(property.getPropertyType());
        propertyNameField.setText(property.getPropertyName());
        propertyDescriptionField.setText(property.getDescription());
        propertyLocationField.setText(property.getLocation());
        propertyPriceField.setText(String.valueOf(property.getPrice()));
        propertyRoomsField.setText(String.valueOf(property.getNumberOfRooms()));

        if (property.getImageProperty() != null && !property.getImageProperty().isEmpty()) {
            propertyImageView.setImage(new Image(property.getImageProperty()));
            imagePath = property.getImageProperty(); // تخزين المسار الحالي للصورة
        }
    }

    public void setOnSaveListener(OnSaveListener listener) {
        this.saveListener = listener;
    }

    private void handleUpdateImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            imagePath = file.toURI().toString();
            propertyImageView.setImage(new Image(imagePath));
        }
    }

    private void handleSave() {
        try {
            if (currentProperty == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "No property data to update.");
                return;
            }

            currentProperty.setPropertyType(propertyTypeComboBox.getValue());
            currentProperty.setPropertyName(propertyNameField.getText());
            currentProperty.setDescription(propertyDescriptionField.getText());
            currentProperty.setLocation(propertyLocationField.getText());
            currentProperty.setPrice(Double.parseDouble(propertyPriceField.getText()));
            currentProperty.setNumberOfRooms(Integer.parseInt(propertyRoomsField.getText()));

            if (imagePath != null && !imagePath.isEmpty()) {
                currentProperty.setImageProperty(imagePath);
            }

            boolean isUpdated = propertyDAO.update(currentProperty);
            if (isUpdated) {
                if (saveListener != null) {
                    saveListener.onSave(currentProperty);
                }
                showAlert(Alert.AlertType.INFORMATION, "Success", "Property details updated successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update property details.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter valid details. " + e.getMessage());
        }
    }

    private void handleCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
