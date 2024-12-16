package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateOfferController {

    @FXML
    private TextField clientName, propertyAddress, amountField;

    @FXML
    private ComboBox<String> typeComboBox, statusComboBox;

    @FXML
    private Button cancelButton, createButton;

    @FXML
    public void initialize() {
        typeComboBox.getItems().addAll("Rent", "Purchase");
        statusComboBox.getItems().addAll("Pending", "In Negotiation", "Approved");

        cancelButton.setOnAction(event -> closeModal());
        createButton.setOnAction(event -> saveOffer());
    }

    private void closeModal() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    private void saveOffer() {
        // Here you can add logic to save data to your database
        System.out.println("Offer Created!");
        closeModal();
    }
}
