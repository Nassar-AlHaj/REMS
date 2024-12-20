package com.rems.realestatemanagement.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

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
    private CheckBox showpass;


    @FXML
    public void handleLoginAction(ActionEvent actionEvent) {

        String useremail = email.getText();
        String password = pass.getText();
        System.out.println("Username: " + useremail);
        System.out.println("Password: " + password);

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("com/rems/realestatemanagement/resetpassword.fxml")));
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void initialize() {
        shpass.textProperty().bindBidirectional(pass.textProperty());

        showpass.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                shpass.setVisible(true);
                pass.setVisible(false);
            } else {
                shpass.setVisible(false);
                pass.setVisible(true);
            }
        });
    }


    public void handleGoToDashboard(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/rems/realestatemanagement/DashboardDesignSearchAndFiltering.fxml")));

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void handleGoToResetAction(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/rems/realestatemanagement/resetpass.fxml")));

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
