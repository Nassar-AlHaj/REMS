package com.rems.realestatemanagement.Controller;
import com.rems.realestatemanagement.models.Role;
import com.rems.realestatemanagement.models.User;
import com.rems.realestatemanagement.models.services.RoleDAOImp;
import com.rems.realestatemanagement.models.services.UsersDAOImp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;


public class addagentController {

    @FXML
    private TextField agentname;
    @FXML
    private TextField agentemail;
    @FXML
    private TextField agentpass;
    @FXML
    private Button backbutton;

    @FXML
    private Button submitagent;

    @FXML
    private Label addlabel;

    private UsersDAOImp UsersDAO;
    private RoleDAOImp RoleDAO;

    public addagentController() {
        UsersDAO = new UsersDAOImp();
        RoleDAO = new RoleDAOImp();
    }


    @FXML
    public void handleAddAgentAction() {




        String name = agentname.getText().trim();
        String email = agentemail.getText().trim();
        String password = agentpass.getText().trim();


        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            addlabel.setText("Please fill in all fields.");
            addlabel.setStyle("-fx-text-fill: red;");
            return;
        }



        if (!isValidEmail(email)) {
            addlabel.setText("Please enter a valid email address.");
            addlabel.setStyle("-fx-text-fill: red;");
            return;
        }


        if (!isValidPassword(password)) {
            addlabel.setText("Password must be strong enough.");
            addlabel.setStyle("-fx-text-fill: red;");
            return;
        }


        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        Role agentRole = RoleDAO.findByName("Agent");
        if (agentRole == null) {
            agentRole = new Role();
            agentRole.setName("Agent");
            RoleDAO.save(agentRole);
        }

        User newUser = new User();
        newUser.setUsername(name);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        newUser.setRole(agentRole);

        try {
            UsersDAO.insert(newUser);
            addlabel.setText("Agent added successfully!");
            addlabel.setStyle("-fx-text-fill: green;");
            clearFields();
        } catch (Exception e) {
            addlabel.setText("Failed to add agent: " + e.getMessage());
            addlabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void clearFields() {
        agentname.clear();
        agentemail.clear();
        agentpass.clear();
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        return password != null && password.matches(passwordRegex);
    }


//    @FXML
//    public void handleGoBack(ActionEvent actionEvent) {
//        try {
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/rems/realestatemanagement/resetpass.fxml")));
//            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



}
