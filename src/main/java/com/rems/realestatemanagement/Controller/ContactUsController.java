package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.models.ContactUs;
import com.rems.realestatemanagement.models.services.ContactUsDAOImp;
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
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField descriptions;

    @FXML
    private ComboBox<String> country;

    @FXML
    private Label firstNameError;

    @FXML
    private Label emailError;

    @FXML
    private Label countryError;

    @FXML
    private Label descriptionError;

    private ContactUsDAOImp contactUsDOA;

    @FXML
    public void initialize() {
        contactUsDOA = new ContactUsDAOImp();

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
        descriptionError.setText("");

        boolean hasError = false;

        // Validate first name
        if (firstName.getText().trim().isEmpty()) {
            firstNameError.setText("First Name is required.");
            hasError = true;
        } else if (!firstName.getText().matches("^[a-zA-Z\\s]+$")) {
            firstNameError.setText("First Name must contain only letters and spaces.");
            hasError = true;
        }

        // Validate email
        if (email.getText().trim().isEmpty()) {
            emailError.setText("Email is required.");
            hasError = true;
        } else if (!email.getText().matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            emailError.setText("Invalid email format.");
            hasError = true;
        }

        // Validate country
        if (country.getValue() == null || country.getValue().equals("Select...")) {
            countryError.setText("Country is required.");
            hasError = true;
        }

        // Validate description
        if (descriptions.getText().trim().isEmpty()) {
            descriptionError.setText("Description is required.");
            hasError = true;
        }

        if (!hasError) {
            saveContact();
            showThankYouPopup();
            clearFields();
        }
    }

    private void saveContact() {
        ContactUs contact = new ContactUs();
        contact.setFirstName(firstName.getText());
        contact.setLastName(lastName.getText());
        contact.setEmail(email.getText());
        contact.setCountry(country.getValue());
        contact.setDescription(descriptions.getText());

        int result = contactUsDOA.insert(contact);
        if (result > 0) {
            System.out.println("Contact saved successfully with ID: " + result);
        } else {
            System.out.println("Failed to save contact.");
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

    private void clearFields() {
        firstName.clear();
        lastName.clear();
        email.clear();
        descriptions.clear();
        country.setValue("Select...");
    }
}