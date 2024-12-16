package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class resetController {
    private Button resetButton;
    private TextField email;

    @FXML
    public void handleGoToReset(javafx.event.ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("resetpassword.fxml"));

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
