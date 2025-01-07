package com.rems.realestatemanagement.Controller.offer;

import com.rems.realestatemanagement.models.services.OffersDAOimp;
import com.rems.realestatemanagement.models.interfaces.OffersDAO;
import com.rems.realestatemanagement.models.Offers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateOfferController {

    @FXML
    private TextField clientNameField, propertyId, amountField;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private Button cancelButton, createButton;

    private OffersDAO offersDAO;

    @FXML
    public void initialize() {
        System.out.println("Initializing CreateOfferController...");
        typeComboBox.getItems().addAll("Rent", "Purchase");
        typeComboBox.setValue("Purchase");

        offersDAO = new OffersDAOimp();

        cancelButton.setOnAction(event -> closeModal());
        createButton.setOnAction(event -> CreateOffer());
    }

    private void closeModal() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    private void CreateOffer() {
        // Create a new Offers object using data from the UI
        String clientNameText = clientNameField.getText();
        String propertyIdText = propertyId.getText();
        System.out.println("Property ID: " + propertyIdText);
        double amount = Double.parseDouble(amountField.getText());
        String type = typeComboBox.getValue();

        // Create an offer object
        Offers newOffer = new Offers(clientNameText, propertyIdText, type, amount);

        // Use the DAO to save the offer
        offersDAO.CreateOffer(newOffer);

        System.out.println("Offer Created!");

        // Close the modal after saving
        closeModal();
    }



}
