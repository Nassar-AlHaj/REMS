package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.models.User;
import com.rems.realestatemanagement.models.services.UsersDAOImp;
import com.rems.realestatemanagement.session.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public class loginController {

    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private Button register;
    @FXML
    private Hyperlink forgotpass;
    @FXML
    private Label errorLabel;
    private UsersDAOImp UsersDAO;

    public loginController() {
        UsersDAO = new UsersDAOImp();
    }

    @FXML
    public void handleLoginAction(ActionEvent actionEvent) {
        String useremail = email.getText().trim();
        String password = pass.getText().trim();

        if (!isValidEmail(useremail)) {
            errorLabel.setText("Please enter a valid email address.");
            errorLabel.setTextFill(Color.RED);
            return;
        }

        if (!isValidPassword(password)) {
            errorLabel.setText("Password must be strong.");
            errorLabel.setTextFill(Color.RED);
            return;
        }

        if (password.isEmpty()) {
            errorLabel.setText("Password cannot be empty.");
            errorLabel.setTextFill(Color.RED);
            return;
        }

        errorLabel.setText("");

        User user = UsersDAO.getUserByEmailAndPassword(useremail, password);

        if (user != null) {

            if (BCrypt.checkpw(password, user.getPassword())) {
                UserSession session = UserSession.getInstance();
                session.setUsername(user.getUsername());
                session.setEmail(user.getEmail());
                session.setRole(user.getRole().getName());
                try {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/rems/realestatemanagement/DashboardDesignSearchAndFiltering.fxml")));  // Adjust the path to your next FXML page
                    Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                errorLabel.setText("Invalid password.");
                errorLabel.setTextFill(Color.RED);
            }



        } else {
            errorLabel.setText("User not found.");
            errorLabel.setTextFill(Color.RED);
        }
    }

    @FXML
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

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        return password != null && password.matches(passwordRegex);
    }
}