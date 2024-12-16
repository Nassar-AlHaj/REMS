package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class loginController {

@FXML
private TextField email;
@FXML
private PasswordField pass;
@FXML
private TextField shpass;
@FXML
private Button loginbutton;
@FXML
private Hyperlink fpass;
@FXML
private CheckBox show;


//    @FXML
//    public void handleLoginAction() {
//
//        String useremail = email.getText();
//        String password = pass.getText();
//
//
//        System.out.println("Username: " + useremail);
//        System.out.println("Password: " + password);
//
//    }
    @FXML
    public void initialize() {
        shpass.textProperty().bindBidirectional(pass.textProperty());

        show.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                shpass.setVisible(true);
                pass.setVisible(false);
            } else {
                shpass.setVisible(false);
                pass.setVisible(true);
            }
        });
    }

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
