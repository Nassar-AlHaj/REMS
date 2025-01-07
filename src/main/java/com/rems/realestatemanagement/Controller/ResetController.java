package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.models.User;
import com.rems.realestatemanagement.models.services.UsersDAOImp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResetController {

    @FXML
    private TextField emailverefication;
    @FXML
    private TextField code;
    @FXML
    private Button emailbutton;
    @FXML
    private Button ecode;
    @FXML
    private Label codelabel;

    private UsersDAOImp UsersDAO;

    private String verificationCode;

    public ResetController() {
        UsersDAO = new UsersDAOImp();
    }

    public void initialize() {

        code.setVisible(false);
        ecode.setVisible(false);
        codelabel.setVisible(false);
    }

    @FXML
    void handleSendEmail(javafx.event.ActionEvent event) {
        String recipientEmail = emailverefication.getText().trim();

        if (recipientEmail.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter an email address.").show();
            return;
        }

        User user = UsersDAO.getUserByEmail(recipientEmail);

        if (user == null) {
            new Alert(Alert.AlertType.ERROR, "Email address not found in our records.").show();
            return;
        }

        try {
            sendEmail(recipientEmail);
            new Alert(Alert.AlertType.INFORMATION, "Verification email sent successfully.").show();

            code.setVisible(true);
            ecode.setVisible(true);
            codelabel.setVisible(true);
            emailbutton.setVisible(false);


        } catch (Exception e) {
            Logger.getLogger(ResetController.class.getName()).log(Level.SEVERE, null, e);
            new Alert(Alert.AlertType.ERROR, "Failed to send verification email. Please try again.").show();
        }
    }

    private void sendEmail(String recipientEmail) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        String myEmail = "nadarinnoo@gmail.com";
        String password = "occh xyad wlrq bfcx";

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }
        });

        Message message = prepareMessage(session, myEmail, recipientEmail);
        if (message != null) {
            Transport.send(message);
        }
    }

    private Message prepareMessage(Session session, String myEmail, String recipientEmail) {
        try {
            verificationCode = generateVerificationCode();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your Verification Code");
            message.setText("Your verification code is: " + verificationCode);

            return message;
        } catch (Exception e) {
            Logger.getLogger(ResetController.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }


    public void handleGoNewPass(javafx.event.ActionEvent event) {
        String enteredCode = code.getText().trim();

        if (enteredCode.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter the verification code.").show();
            return;
        }

        if (enteredCode.equals(verificationCode)) {
            try {

                String verifiedEmail = emailverefication.getText().trim();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/newpassform.fxml"));
                Parent root = loader.load();
                NewpassController controller = loader.getController();
                controller.setVerifiedEmail(verifiedEmail);

                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while transitioning to the next scene.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid verification code. Please try again.").show();
        }
    }


}