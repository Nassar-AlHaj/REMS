package com.rems.realestatemanagement.Controller;
import com.rems.realestatemanagement.models.Client;
import com.rems.realestatemanagement.models.Interaction;
import com.rems.realestatemanagement.models.Interaction.InteractionType;
import com.rems.realestatemanagement.models.interfaces.ClientDOA;
import com.rems.realestatemanagement.models.interfaces.InteractionDAO;
import com.rems.realestatemanagement.models.services.ClientDOAimp;
import com.rems.realestatemanagement.models.services.InteractionDAOImpl;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import java.time.format.DateTimeFormatter;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class InteractionController implements Initializable {

    @FXML
    private ComboBox<InteractionType> interactionTypeComboBox;
    @FXML
    private ComboBox<Client> clientComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea detailsArea;
    @FXML
    private TableView<Interaction> interactionsTable;
    @FXML
    private TableColumn<Interaction, String> clientNameColumn;
    @FXML
    private TableColumn<Interaction, LocalDate> dateColumn;
    @FXML
    private TableColumn<Interaction, String> interactionTypeColumn;
    @FXML
    private TableColumn<Interaction, String> detailsColumn;
    @FXML
    private TextField searchField;
    private FilteredList<Interaction> filteredData;

    private final InteractionDAO interactionDAO = new InteractionDAOImpl();
    private final ClientDOA clientDAO = new ClientDOAimp();
    private final ObservableList<Interaction> interactionsList = FXCollections.observableArrayList();
    private final ObservableList<Client> clientsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupComboBoxes();
        setupTable();
        loadData();
    }

    private void setupComboBoxes() {
        interactionTypeComboBox.setItems(FXCollections.observableArrayList(InteractionType.values()));

        interactionTypeComboBox.setConverter(new StringConverter<InteractionType>() {
            @Override
            public String toString(InteractionType type) {
                if (type == null) return "";
                return type.name().replace('_', ' ');
            }

            @Override
            public InteractionType fromString(String string) {
                return null;
            }
        });

        clientComboBox.setConverter(new StringConverter<Client>() {
            @Override
            public String toString(Client client) {
                return client == null ? "" : client.getFname() + " " + client.getLname();
            }

            @Override
            public Client fromString(String string) {
                return null;
            }
        });

        clientComboBox.setPromptText("Select a client");
        loadClients();
    }

    private void loadClients() {
        clientsList.clear();
        List<Client> clients = clientDAO.findAll();
        clientsList.addAll(clients);
        clientComboBox.setItems(clientsList);
    }



    private void setupTable() {
        // Configure column data
        clientNameColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getClient().getFname() + " " + data.getValue().getClient().getLname()));

        dateColumn.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getInteractionDate()));

        dateColumn.setCellFactory(column -> new TableCell<Interaction, LocalDate>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setText(empty || date == null ? null : date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
            }
        });

        interactionTypeColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getType().name().replace('_', ' ')));

        detailsColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDetails()));

        interactionsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Add action column for "Delete" button
        TableColumn<Interaction, Void> actionColumn = new TableColumn<>("Actions");
        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.getStyleClass().add("delete-button");
                deleteButton.setOnAction(event -> {
                    Interaction interaction = getTableView().getItems().get(getIndex());
                    handleDeleteAction(interaction);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteButton);
            }
        });

        interactionsTable.getColumns().add(actionColumn);

        // Setup search functionality
        filteredData = new FilteredList<>(interactionsList, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(interaction -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                String clientName = interaction.getClient().getFname() + " " + interaction.getClient().getLname();

                return clientName.toLowerCase().contains(lowerCaseFilter)
                        || interaction.getType().name().toLowerCase().contains(lowerCaseFilter)
                        || interaction.getDetails().toLowerCase().contains(lowerCaseFilter)
                        || interaction.getInteractionDate().toString().contains(lowerCaseFilter);
            });
        });

        interactionsTable.setItems(filteredData);
    }





    private void handleDeleteAction(Interaction interaction) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Interaction");
        alert.setHeaderText("Delete Interaction");
        alert.setContentText("Are you sure you want to delete this interaction?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            interactionDAO.deleteInteraction(interaction);
            loadData();
        }
    }



    private void loadData() {
        interactionsList.clear();
        interactionsList.addAll(interactionDAO.getAllInteractions());
        filteredData = new FilteredList<>(interactionsList, p -> true);
        interactionsTable.setItems(filteredData);
    }

    @FXML
    private void handleSave() {
        if (validateInput()) {
            try {
                Client selectedClient = clientComboBox.getValue();
                InteractionType type = interactionTypeComboBox.getValue();
                LocalDate interactionDate = datePicker.getValue();
                String details = detailsArea.getText();

                Interaction interaction = new Interaction(selectedClient, type, details, interactionDate);

                interactionDAO.createInteraction(interaction);
                clearForm();
                loadData();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Interaction saved successfully!");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save interaction: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private boolean validateInput() {
        if (clientComboBox.getValue() == null ||
                interactionTypeComboBox.getValue() == null ||
                datePicker.getValue() == null ||
                detailsArea.getText().isEmpty()) {

            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill in all fields");
            return false;
        }
        return true;
    }

    private void clearForm() {
        clientComboBox.setValue(null);
        interactionTypeComboBox.setValue(null);
        datePicker.setValue(null);
        detailsArea.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void handleDelete() {
        Interaction selectedInteraction = interactionsTable.getSelectionModel().getSelectedItem();
        if (selectedInteraction != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Interaction");
            alert.setHeaderText("Delete Interaction");
            alert.setContentText("Are you sure you want to delete this interaction?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                InteractionDAO interactionDAO = new InteractionDAOImpl();
                interactionDAO.deleteInteraction(selectedInteraction);
                refreshTable();
            }
        }
    }

    private void refreshTable() {
        InteractionDAO interactionDAO = new InteractionDAOImpl();
        interactionsTable.setItems(FXCollections.observableArrayList(interactionDAO.getAllInteractions()));
    }

    @FXML
    private void handleCancel() {
        clearForm();
    }
}