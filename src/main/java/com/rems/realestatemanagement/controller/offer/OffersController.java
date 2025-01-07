package com.rems.realestatemanagement.controller.offer;

import com.rems.realestatemanagement.models.Offers;
import com.rems.realestatemanagement.models.interfaces.OffersDAO;
import com.rems.realestatemanagement.models.services.OffersDAOimp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OffersController {

    @FXML
    private TableView<Offers> offersTable;

    @FXML
    private TableColumn<Offers, String> clientNameColumn;

    @FXML
    private TableColumn<Offers, String> propertyIdColumn;

    @FXML
    private TableColumn<Offers, String> typeColumn;

    @FXML
    private TableColumn<Offers, Double> amountColumn;

    @FXML
    private TableColumn<Offers, LocalDate> dateColumn;

    @FXML
    private Button addOfferButton;

    private final OffersDAO offersDAO;

    public OffersController() {
        this.offersDAO = new OffersDAOimp();
    }

    @FXML
    public void initialize() {
        setupTableColumns();
        loadOffers();

        addOfferButton.setOnAction(event -> openCreateOfferModal());
    }

    private void setupTableColumns() {
        // Set up cell value factories
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        propertyIdColumn.setCellValueFactory(new PropertyValueFactory<>("propertyId"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Format amount as currency
        amountColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double amount, boolean empty) {
                super.updateItem(amount, empty);
                if (empty || amount == null) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", amount));
                }
            }
        });

        // Format date
        dateColumn.setCellFactory(column -> new TableCell<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(formatter.format(date));
                }
            }
        });
    }

    public void loadOffers() {
        try {
            var offers = offersDAO.getAllOffers();
            offersTable.setItems(FXCollections.observableArrayList(offers));
        } catch (Exception e) {
            showError("Error loading offers", e.getMessage());
        }
    }

    private void openCreateOfferModal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/createOfferModal.fxml"));
            Scene modalScene = new Scene(loader.load());

            CreateOfferController controller = loader.getController();

            Stage modal = new Stage();
            modal.setScene(modalScene);
            modal.initModality(Modality.APPLICATION_MODAL);
            modal.setTitle("Create New Offer");

            // Refresh table after modal is closed
            modal.setOnHidden(event -> loadOffers());

            modal.showAndWait();

        } catch (Exception e) {
            showError("Error Opening Modal", "Failed to open Create Offer Modal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}