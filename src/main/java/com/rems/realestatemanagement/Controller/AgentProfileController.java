package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.Controller.AgentModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class AgentProfileController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TableView<AgentTableItem> agentTable;
    @FXML
    private TableColumn<AgentTableItem, Integer> idColumn;
    @FXML
    private TableColumn<AgentTableItem, String> nameColumn;
    @FXML
    private TableColumn<AgentTableItem, String> emailColumn;
    @FXML
    private TableColumn<AgentTableItem, String> passwordColumn;
    @FXML
    private TableColumn<AgentTableItem, Button> actionColumn;

    private final AgentModel agentModel = new AgentModel();
    private final ObservableList<AgentTableItem> agentTableItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordHashProperty());
        actionColumn.setCellValueFactory(cellData -> {
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(event -> deleteAgent(cellData.getValue().getAgent()));
            return new SimpleObjectProperty<>(deleteButton);
        });

        agentTable.setItems(agentTableItems);
        updateAgentTable();
    }

    @FXML
    public void addAgent() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();


        String hashedPassword = hashPassword(password);

        AgentModel.Agent newAgent = new AgentModel.Agent(1, name, email, hashedPassword);
        agentModel.addAgent(newAgent);
        updateAgentTable();
        clearFields();
    }

    private void updateAgentTable() {
        agentTableItems.clear();
        for (AgentModel.Agent agent : agentModel.getAgents()) {
            agentTableItems.add(new AgentTableItem(agent));
        }
    }

    private void deleteAgent(AgentModel.Agent agent) {
        agentModel.removeAgent(agent);
        updateAgentTable();
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        passwordField.clear();
    }


    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hashedText = new StringBuilder(number.toString(16));


            while (hashedText.length() < 64) {
                hashedText.insert(0, "0");
            }
            return hashedText.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private static class AgentTableItem {
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty email;
        private final SimpleStringProperty passwordHash;
        private final AgentModel.Agent agent;

        public AgentTableItem(AgentModel.Agent agent) {
            this.id = new SimpleIntegerProperty(agent.getId());
            this.name = new SimpleStringProperty(agent.getAgentName());
            this.email = new SimpleStringProperty(agent.getEmail());
            this.passwordHash = new SimpleStringProperty(agent.getPasswordHash());
            this.agent = agent;
        }

        public int getId() {
            return id.get();
        }

        public SimpleIntegerProperty idProperty() {
            return id;
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public String getEmail() {
            return email.get();
        }

        public SimpleStringProperty emailProperty() {
            return email;
        }

        public String getPasswordHash() {
            return passwordHash.get();
        }

        public SimpleStringProperty passwordHashProperty() {
            return passwordHash;
        }

        public AgentModel.Agent getAgent() {
            return agent;
        }
    }
}