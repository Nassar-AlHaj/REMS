package com.rems.realestatemanagement.Controller.agent;

import com.rems.realestatemanagement.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class EditAgentDialogController {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
    );
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
    );

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label passwordMatchLabel;

    private User user;
    private Dialog<ButtonType> dialog;

    @FXML
    public void initialize() {
        setupValidationListeners();
        initializeLabels();
    }

    private void setupValidationListeners() {
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> validatePasswords());
        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) -> validatePasswords());
        emailField.textProperty().addListener((observable, oldValue, newValue) -> validateEmail());
    }

    private void initializeLabels() {
        passwordMatchLabel.setText("");
    }

    public void setUser(User user) {
        this.user = user;
        nameField.setText(user.getUsername());
        emailField.setText(user.getEmail());
    }

    private void validatePasswords() {
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (password.isEmpty() && confirmPassword.isEmpty()) {
            clearPasswordValidation();
            return;
        }

        if (!password.equals(confirmPassword)) {
            updatePasswordValidation("Passwords do not match", Color.RED, true);
            return;
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            updatePasswordValidation(
                    "Password must contain at least 8 characters, including uppercase, lowercase, number, and special character",
                    Color.RED,
                    true
            );
            return;
        }

        updatePasswordValidation("Password requirements met", Color.GREEN, false);
    }

    private void clearPasswordValidation() {
        passwordMatchLabel.setText("");
        disableOkButton(false);
    }

    private void updatePasswordValidation(String message, Color color, boolean disableButton) {
        passwordMatchLabel.setText(message);
        passwordMatchLabel.setTextFill(color);
        disableOkButton(disableButton);
    }

    private void validateEmail() {
        String email = emailField.getText();
        boolean isValid = EMAIL_PATTERN.matcher(email).matches();
        emailField.setStyle(isValid ? "" : "-fx-border-color: red;");
        disableOkButton(!isValid);
    }

    private void disableOkButton(boolean disable) {
        if (dialog != null) {
            Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
            if (okButton != null) {
                okButton.setDisable(disable);
            }
        }
    }

    public void setDialog(Dialog<ButtonType> dialog) {
        this.dialog = dialog;
    }

    public String getName() {
        return nameField.getText().trim();
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getPassword() {
        String password = passwordField.getText();

        if (password.isEmpty()) {
            return null;
        }

        boolean isPasswordValid = password.equals(confirmPasswordField.getText()) &&
                PASSWORD_PATTERN.matcher(password).matches();

        return isPasswordValid ? hashPassword(password) : null;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    @FXML
    void handleOK() {
        if (!isFormValid()) {
            dialog.close();
        }
    }

    private boolean isFormValid() {
        if (!EMAIL_PATTERN.matcher(emailField.getText()).matches()) {
            return false;
        }

        String password = passwordField.getText();
        if (!password.isEmpty()) {
            return password.equals(confirmPasswordField.getText()) &&
                    PASSWORD_PATTERN.matcher(password).matches();
        }

        return true;
    }
}