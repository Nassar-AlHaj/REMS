package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.models.User;
import com.rems.realestatemanagement.models.services.UsersDOAImp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public class newpassController {

    @FXML
    private PasswordField newpass;
    @FXML
    private PasswordField newpass2;
    @FXML
    private Button submitbutton;
    @FXML
    private Label errorlabel;
    @FXML
    private String verifiedEmail;

    private UsersDOAImp UsersDOA;

    public newpassController() {
        UsersDOA = new UsersDOAImp();
    }

    public void setVerifiedEmail(String email) {
        this.verifiedEmail = email;
    }

    @FXML
    private void handleNewPassAction(javafx.event.ActionEvent actionEvent) {
        String newPassword = newpass.getText().trim();
        String confirmPassword = newpass2.getText().trim();

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            errorlabel.setText("Please enter both password fields.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            errorlabel.setText("Passwords do not match.");
            return;
        }

        if (!isValidPassword(newPassword)) {
            errorlabel.setText("Password must be strong");
            return;
        }
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        User user = UsersDOA.getUserByEmail(verifiedEmail);
        if (user != null) {
            user.setPassword(hashedPassword);
            UsersDOA.update(user);
            errorlabel.setText("Password updated successfully.");

            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/rems/realestatemanagement/login.fxml")));
                Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            errorlabel.setText("User not found.");
        }

    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        return password.matches(passwordRegex);
    }

}