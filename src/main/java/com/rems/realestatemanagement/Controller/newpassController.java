package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class newpassController {
    @FXML
    private PasswordField newpass;
    @FXML
    private PasswordField newpass2;
    @FXML
    private TextField shnewpass;
    @FXML
    private TextField shnewpass2;
    @FXML
    private CheckBox showcheck;
    @FXML
    private Button submitbutton;


//    @FXML
//    private void handleLoginAction() {
//
//        String newpassword = newpass.getText();
//        String newpassword2 = newpass2.getText();
//
//
//        System.out.println("newpassword: " + newpassword);
//        System.out.println("newpassword2: " + newpassword2);
//
//    }



    @FXML
    public void initialize() {
        shnewpass.textProperty().bindBidirectional(newpass.textProperty());
        shnewpass2.textProperty().bindBidirectional(newpass2.textProperty());

        showcheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                shnewpass.setVisible(true);
                newpass.setVisible(false);
                shnewpass2.setVisible(true);
                newpass2.setVisible(false);
            } else {
                shnewpass.setVisible(false);
                newpass.setVisible(true);
                shnewpass2.setVisible(false);
                newpass2.setVisible(true);
            }
        });
    }


//    @FXML
//    private void handleGoToReset(javafx.event.ActionEvent event) {
//        try {
//
//            Parent root = FXMLLoader.load(getClass().getResource("resetpassword.fxml"));
//
//            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//
//
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}




