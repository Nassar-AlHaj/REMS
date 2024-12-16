package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PropertyController {

    @FXML
    private ComboBox<String> propertyType;

    @FXML
    private Button chooseFileButton;

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

    }
}