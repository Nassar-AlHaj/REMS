package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateOfferController {

    @FXML
    private TextField clientName, propertyId, amountField;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private Button cancelButton, createButton;

    @FXML
    public void initialize() {
        typeComboBox.getItems().addAll("Rent", "Purchase");

        cancelButton.setOnAction(event -> closeModal());
        createButton.setOnAction(event -> saveOffer());
    }

    private void closeModal() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    private void saveOffer() {
        System.out.println("Offer Created!");
        closeModal();
    }
}
