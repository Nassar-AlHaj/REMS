package com.rems.realestatemanagement.Controller.client;

import com.rems.realestatemanagement.models.Client;
import com.rems.realestatemanagement.models.Client.ClientCategory;
import com.rems.realestatemanagement.models.services.ClientDOAimp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML private TextField idField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;
    @FXML private ComboBox<ClientCategory> categorizationComboBox;

    @FXML private TableView<Client> clientsTable;
    @FXML private TableColumn<Client, Integer> idColumn;
    @FXML private TableColumn<Client, String> firstNameColumn;
    @FXML private TableColumn<Client, String> lastNameColumn;
    @FXML private TableColumn<Client, String> phoneColumn;
    @FXML private TableColumn<Client, String> emailColumn;
    @FXML private TableColumn<Client, String> addressColumn;
    @FXML private TableColumn<Client, ClientCategory> categorizationColumn;

    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button removeButton;

    private ClientDOAimp clientDAO;
    private ObservableList<Client> clientsList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientDAO = new ClientDOAimp();
        clientsList = FXCollections.observableArrayList();

        initializeColumns();
        initializeCategorizationComboBox();
        refreshTable();
        setupTableSelection();
        setupButtonHandlers();
    }

    private void initializeColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        categorizationColumn.setCellValueFactory(new PropertyValueFactory<>("categorization"));
    }

    private void initializeCategorizationComboBox() {
        categorizationComboBox.setItems(FXCollections.observableArrayList(ClientCategory.values()));
        categorizationComboBox.getSelectionModel().selectFirst();
    }

    private void setupTableSelection() {
        clientsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
    }

    private void setupButtonHandlers() {
        addButton.setOnAction(e -> handleAdd());
        editButton.setOnAction(e -> handleEdit());
        removeButton.setOnAction(e -> handleRemove());
    }

    private void handleAdd() {
        if (validateInput()) {
            Client client = new Client(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    phoneField.getText(),
                    emailField.getText(),
                    addressField.getText(),
                    categorizationComboBox.getValue()
            );

            clientDAO.save(client);
            clearFields();
            refreshTable();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Client added successfully!");
        }
    }

    private void handleEdit() {
        Client selectedClient = clientsTable.getSelectionModel().getSelectedItem();
        if (selectedClient == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a client to edit.");
            return;
        }

        if (validateInput()) {
            selectedClient.setFname(firstNameField.getText());
            selectedClient.setLname(lastNameField.getText());
            selectedClient.setPhone(phoneField.getText());
            selectedClient.setEmail(emailField.getText());
            selectedClient.setAddress(addressField.getText());
            selectedClient.setCategorization(categorizationComboBox.getValue());

            clientDAO.update(selectedClient);
            clearFields();
            refreshTable();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Client updated successfully!");
        }
    }

    private void handleRemove() {
        Client selectedClient = clientsTable.getSelectionModel().getSelectedItem();
        if (selectedClient == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a client to remove.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Client");
        alert.setContentText("Are you sure you want to delete this client?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            clientDAO.delete(selectedClient);
            clearFields();
            refreshTable();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Client removed successfully!");
        }
    }

    private void refreshTable() {
        clientsList.clear();
        clientsList.addAll(clientDAO.findAll());
        clientsTable.setItems(clientsList);
    }

    private void populateFields(Client client) {
        idField.setText(String.valueOf(client.getId()));
        firstNameField.setText(client.getFname());
        lastNameField.setText(client.getLname());
        phoneField.setText(client.getPhone());
        emailField.setText(client.getEmail());
        addressField.setText(client.getAddress());
        categorizationComboBox.setValue(client.getCategorization());
    }

    private void clearFields() {
        idField.clear();
        firstNameField.clear();
        lastNameField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
        categorizationComboBox.getSelectionModel().selectFirst();
    }

    private boolean validateInput() {
        StringBuilder errors = new StringBuilder();

        if (firstNameField.getText().trim().isEmpty()) {
            errors.append("First name is required.\n");
        }
        if (lastNameField.getText().trim().isEmpty()) {
            errors.append("Last name is required.\n");
        }
        if (phoneField.getText().trim().isEmpty()) {
            errors.append("Phone number is required.\n");
        }
        if (emailField.getText().trim().isEmpty()) {
            errors.append("Email is required.\n");
        }
        if (addressField.getText().trim().isEmpty()) {
            errors.append("Address is required.\n");
        }
        if (categorizationComboBox.getValue() == null) {
            errors.append("Categorization is required.\n");
        }

        if (errors.length() > 0) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", errors.toString());
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
