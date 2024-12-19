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
    private TextField shownewpass;
    @FXML
    private TextField shownewpass2;
    @FXML
    private CheckBox showresetpass;
    @FXML
    private Button submitbutton;


    @FXML
    private void handleNewPassAction() {

        String newpassword = newpass.getText();
        String newpassword2 = newpass2.getText();


        System.out.println("newpassword: " + newpassword);
        System.out.println("newpassword2: " + newpassword2);

    }



    @FXML
    public void initialize() {
        shownewpass.textProperty().bindBidirectional(newpass.textProperty());
        shownewpass2.textProperty().bindBidirectional(newpass2.textProperty());

        showresetpass.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                shownewpass.setVisible(true);
                newpass.setVisible(false);
                shownewpass2.setVisible(true);
                newpass2.setVisible(false);
            } else {
                shownewpass.setVisible(false);
                newpass.setVisible(true);
                shownewpass2.setVisible(false);
                newpass2.setVisible(true);
            }
        });
    }




}
