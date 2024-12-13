package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class InteractionController {

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    @FXML
    private Label responseLabel;

    public InteractionController() {
    }

    @FXML
    private void initialize() {
        System.out.println("InteractionController is being initialized");
    }

    @FXML
    private void handleSendButtonClick() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            responseLabel.setText("Message sent: " + message);
            System.out.println("Message sent: " + message);
        } else {
            responseLabel.setText("Please enter a message.");
            System.out.println("No message entered.");
        }
    }
}
