package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.Modality;

public class OffersController {

    @FXML
    private TableView<?> offersTable;

    @FXML
    private Button addOfferButton;

    @FXML
    public void initialize() {
        addOfferButton.setOnAction(event -> openCreateOfferModal());
    }

    private void openCreateOfferModal() {
        try {
            // Load the FXML file for the modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/CreateOfferModal.fxml"));
            Scene modalScene = new Scene(loader.load());

            // Optionally retrieve the controller
            // CreateOfferController controller = loader.getController();
            // Initialize controller data if needed, e.g., controller.setOffersData(offersTable.getItems());

            // Create a new Stage for the modal
            Stage modal = new Stage();
            modal.setScene(modalScene);
            modal.initModality(Modality.APPLICATION_MODAL); // Makes the modal window blocking
            modal.setTitle("Create New Offer");

            // Display the modal
            modal.showAndWait();

        } catch (Exception e) {
            // Handle any exceptions (e.g., FXML file not found or loading issues)
            e.printStackTrace();
            System.err.println("Failed to open Create Offer Modal: " + e.getMessage());
        }
    }
}
