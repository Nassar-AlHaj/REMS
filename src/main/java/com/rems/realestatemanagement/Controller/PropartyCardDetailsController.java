package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PropartyCardDetailsController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
