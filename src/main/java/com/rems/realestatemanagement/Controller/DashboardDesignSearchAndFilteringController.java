package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class DashboardDesignSearchAndFilteringController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button CreateOffer;

    @FXML
    private Button Property;

    @FXML
    private Button Offer;

    @FXML
    private Button Add_Property;

    @FXML
    private Button Clients;

    @FXML
    private Button Interactions;

    @FXML
    private Button Contacts;

    @FXML
    private Button Admin_Console;

    @FXML
    private AnchorPane view;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private void loadPage(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node newPage = loader.load();

            AnchorPane.setTopAnchor(newPage, 0.0);
            AnchorPane.setRightAnchor(newPage, 0.0);
            AnchorPane.setBottomAnchor(newPage, 0.0);
            AnchorPane.setLeftAnchor(newPage, 0.0);

            if (!(newPage instanceof AnchorPane)) {
                AnchorPane wrapper = new AnchorPane(newPage);
                AnchorPane.setTopAnchor(newPage, 0.0);
                AnchorPane.setRightAnchor(newPage, 0.0);
                AnchorPane.setBottomAnchor(newPage, 0.0);
                AnchorPane.setLeftAnchor(newPage, 0.0);
                newPage = wrapper;
            }

            view.getChildren().clear();
            view.getChildren().add(newPage);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the page: " + fxmlPath);
        }
    }

    public void CreateOffer() {
        loadPage("/com/rems/realestatemanagement/CreateOfferModal.fxml");
    }

    public void onMouse_Entered_CreateOffer() {
        CreateOffer.setOnMouseEntered(mouseEvent ->
                CreateOffer.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );

        CreateOffer.setOnMouseExited(mouseEvent -> {
            CreateOffer.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_Clicked_CreateOffer() throws IOException {
        CreateOffer.setOnMouseClicked(mouseEvent -> {
            CreateOffer.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white");
        });
        CreateOffer();
    }

    public void Property() {
        loadPage("/com/rems/realestatemanagement/proparty-card.fxml");
    }

    public void onMouse_Entered_Property() {
        Property.setOnMouseEntered(mouseEvent ->
                Property.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );

        Property.setOnMouseExited(mouseEvent -> {
            Property.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_Clicked_Property() throws IOException {
        Property.setOnMouseClicked(mouseEvent -> {
            Property.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white");
        });
        Property();
    }

    public void Offer() {
        loadPage("/com/rems/realestatemanagement/OffersView.fxml");
    }

    public void onMouse_Entered_Offer() {
        Offer.setOnMouseEntered(mouseEvent ->
                Offer.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );

        Offer.setOnMouseExited(mouseEvent -> {
            Offer.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_Clicked_Offer() throws IOException {
        Offer.setOnMouseEntered(mouseEvent ->
                Offer.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );
        Offer();
    }

    public void Add_Property() {
        loadPage("/com/rems/realestatemanagement/property.fxml");
    }

    public void onMouse_EnteredAdd_Property() {
        Add_Property.setOnMouseEntered(mouseEvent ->
                Add_Property.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );

        Add_Property.setOnMouseExited(mouseEvent -> {
            Add_Property.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_ClickedAdd_Property() throws IOException {
        Add_Property.setOnMouseEntered(mouseEvent ->
                Add_Property.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );
        Add_Property();
    }

    public void Clients() {
        loadPage("/com/rems/realestatemanagement/Client.fxml");
    }

    public void onMouse_Entered_Clients() {
        Clients.setOnMouseEntered(mouseEvent ->
                Clients.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );

        Clients.setOnMouseExited(mouseEvent -> {
            Clients.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_Clicked_Clients() throws IOException {
        Clients.setOnMouseEntered(mouseEvent ->
                Clients.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );
        Clients();
    }


    public void Interactions() {
        loadPage("/com/rems/realestatemanagement/Interactions.fxml");
    }

    public void onMouse_Entered_Interactions() {
        Interactions.setOnMouseEntered(mouseEvent ->
                Interactions.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );

        Interactions.setOnMouseExited(mouseEvent -> {
            Interactions.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_Clicked_Interactions() throws IOException {
        Interactions.setOnMouseClicked(mouseEvent -> {
            Interactions.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white");
        });
        Interactions();
    }

    public void Contacts() {
        loadPage("/com/rems/realestatemanagement/contactUs.fxml");
    }

    public void onMouse_Entered_Contacts() {
        Contacts.setOnMouseEntered(mouseEvent ->
                Contacts.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );

        Contacts.setOnMouseExited(mouseEvent -> {
            Contacts.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_Clicked_Contacts() throws IOException {
        Contacts.setOnMouseEntered(mouseEvent ->
                Contacts.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );
        Contacts();
    }

    public void Admin_Console() {
        loadPage("/com/rems/realestatemanagement/AgentProfile-view.fxml");
    }

    public void onMouse_Entered_Admin_Console() {
        Admin_Console.setOnMouseEntered(mouseEvent ->
                Admin_Console.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white")
        );

        Admin_Console.setOnMouseExited(mouseEvent -> {
            Admin_Console.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_Clicked_Admin_Console() throws IOException {
        Admin_Console.setOnMouseClicked(mouseEvent -> {
            Admin_Console.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white");
        });
        Admin_Console();
    }
}