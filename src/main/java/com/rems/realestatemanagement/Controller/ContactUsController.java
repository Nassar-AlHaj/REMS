package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactUsController {

    @FXML
    private TextField firstName;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<String> country;

    @FXML
    private Label firstNameError;

    @FXML
    private Label emailError;

    @FXML
    private Label countryError;

    @FXML
    public void initialize() {
        country.getItems().addAll(
                "Palestine",
                "Canada",
                "United States",
                "Australia",
                "India",
                "Germany",
                "France",
                "China",
                "Japan",
                "Brazil"
        );

        country.setPromptText("Select...");
    }

    @FXML
    private void handleContactUs() {
        firstNameError.setText("");
        emailError.setText("");
        countryError.setText("");

        boolean hasError = false;

        if (firstName.getText().trim().isEmpty()) {
            firstNameError.setText("First Name is required.");
            hasError = true;
        }

        if (email.getText().trim().isEmpty()) {
            emailError.setText("Email is required.");
            hasError = true;
        }

        if (country.getValue() == null || country.getValue().equals("Select...")) {
            countryError.setText("Country is required.");
            hasError = true;
        }

        if (!hasError) {
            showThankYouPopup();
        }
    }

    private void showThankYouPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/Fxml/popup.fxml"));
            AnchorPane popupRoot = loader.load();

            Stage popupStage = new Stage();
            popupStage.setTitle("Thank You");
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(popupRoot));
            popupStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
