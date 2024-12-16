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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/Fxml/CreateOfferModal.fxml"));
            Stage modal = new Stage();
            modal.setScene(new Scene(loader.load()));
            modal.initModality(Modality.APPLICATION_MODAL);
            modal.setTitle("Create New Offer");
            modal.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
