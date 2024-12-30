package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.rems.realestatemanagement.models.services.PropertyDAOImp;
import com.rems.realestatemanagement.models.Property;

import java.io.File;
import java.time.LocalDate;

public class PropertyController {

    private PropertyDAOImp propertyDAOImp = new PropertyDAOImp();





    @FXML
    private ComboBox<String> propertyType;

    @FXML
    private TextField propertyName;

    @FXML
    private TextArea description;

    @FXML
    private TextField price;

    @FXML
    private ComboBox<String> state;

    @FXML
    private TextField numberOfRooms;

    @FXML
    private TextField locations;

    @FXML
    private Button chooseFileButton;

    @FXML
    private VBox uploadedImagesContainer;

    @FXML
    public void initialize() {
        propertyType.getItems().addAll(
                "Select Property Type",
                "Residential",
                "Commercial",
                "Industrial",
                "Agricultural",
                "Land"
        );
        propertyType.setValue("Select Property Type");

        chooseFileButton.setOnAction(event -> handleChooseFile());
    }

    private void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Property Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) chooseFileButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            String imageUrl = selectedFile.toURI().toString();
            propertyDAOImp .updateImage(1, imageUrl);
            addUploadedImage(imageUrl);
        }
    }


    private void addUploadedImage(String imageUrl) {
        HBox hbox = new HBox(10);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1; -fx-border-radius: 5; -fx-padding: 5;");

        ImageView imageView = new ImageView(new Image(imageUrl));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);

        TextField textField = new TextField(imageUrl);
        textField.setPrefHeight(35);
        textField.setPrefWidth(250);
        textField.setStyle("-fx-background-color: #f1f1f1; -fx-border-radius: 5; -fx-border-color: #cccccc;");

        ImageView deleteIcon = new ImageView(new Image(getClass().getResource("/img/DeleteIcon.png").toString()));
        deleteIcon.setFitHeight(20);
        deleteIcon.setFitWidth(20);
        deleteIcon.setPreserveRatio(true);

        deleteIcon.setOnMouseClicked(event -> {
            uploadedImagesContainer.getChildren().remove(hbox);
            propertyDAOImp.deleteImage(1);
        });

        borderPane.setLeft(imageView);
        borderPane.setCenter(textField);
        borderPane.setRight(deleteIcon);

        hbox.getChildren().add(borderPane);
        uploadedImagesContainer.getChildren().add(hbox);
    }


    @FXML
    public void onApplyLeasingClicked() {

        if (propertyName.getText().isEmpty() || description.getText().isEmpty() || price.getText().isEmpty() ||
                state.getValue()== null || numberOfRooms.getText().isEmpty() || locations.getText().isEmpty() ||
                propertyType.getValue().equals("Select Property Type")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please fill in all fields.");
            return;
        }

        try {
            Property property = new Property();
            property.setPropertyName(propertyName.getText());
            property.setPropertyType(propertyType.getValue());
            property.setDescription(description.getText());
            property.setPrice(Double.parseDouble(price.getText()));
            property.setState(state.getValue());
            property.setNumberOfRooms(Integer.parseInt(numberOfRooms.getText()));
            property.setLocation(locations.getText());

            if (!uploadedImagesContainer.getChildren().isEmpty()) {
                HBox firstImageBox = (HBox) uploadedImagesContainer.getChildren().get(0);
                BorderPane borderPane = (BorderPane) firstImageBox.getChildren().get(0);
                TextField imageUrlField = (TextField) borderPane.getCenter();
                property.setImageProperty(imageUrlField.getText());
            }

            property.setlistingDate(LocalDate.now());


            propertyDAOImp.insert(property);

            showAlert(Alert.AlertType.INFORMATION, "Success", "The property has been successfully added for leasing.");

            resetFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numerical values for price and number of rooms.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void resetFields() {
        propertyName.clear();
        description.clear();
        price.clear();
        state.setValue("Available");
        numberOfRooms.clear();
        locations.clear();
        propertyType.setValue("Select Property Type");
        uploadedImagesContainer.getChildren().clear();
    }
}