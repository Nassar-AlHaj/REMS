package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button Transactions;

    @FXML
    private Button Contacts;

    @FXML
    private Button Admin_Console;

    @FXML
    private Pane view;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public void CreateOffer() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/CreateOfferModal.fxml"));
            Node newPage = loader.load();

            view.getChildren().clear();
            view.getChildren().add(newPage);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the new page.");
        }
    }

    public void onMouse_Entered_CreateOffer(){
        CreateOffer.setOnMouseEntered(mouseEvent ->
                CreateOffer.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));

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
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/proparty-card.fxml"));
            Node newPage = loader.load();

            view.getChildren().clear();
            view.getChildren().add(newPage);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the new page.");
        }
    }

    public void onMouse_Entered_Property(){

        Property.setOnMouseEntered(mouseEvent ->
                Property.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));

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
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/OffersView.fxml"));
            Node newPage = loader.load();

            view.getChildren().clear();
            view.getChildren().add(newPage);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the new page.");
        }
    }

    public void onMouse_Entered_Offer(){
        Offer.setOnMouseEntered(mouseEvent ->
                Offer.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));

        Offer.setOnMouseExited(mouseEvent -> {
            Offer.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_Clicked_Offer() throws IOException {

        Offer.setOnMouseEntered(mouseEvent ->
                Offer.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));
        Offer();
    }

//CreateOffer
    public void Add_Property() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/property.fxml"));
            Node newPage = loader.load();

            view.getChildren().clear();
            view.getChildren().add(newPage);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the new page.");
        }
    }

    public void onMouse_EnteredAdd_Property(){
        Add_Property.setOnMouseEntered(mouseEvent ->
                Add_Property.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));

        Add_Property.setOnMouseExited(mouseEvent -> {
            Add_Property.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_ClickedAdd_Property() throws IOException {

        Add_Property.setOnMouseEntered(mouseEvent ->
                Add_Property.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));
        Add_Property();
    }


    public void Clients() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/newpage.fxml"));
            Node newPage = loader.load();

            view.getChildren().clear();
            view.getChildren().add(newPage);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the new page.");
        }
    }

    public void onMouse_Entered_Clients(){
        Clients.setOnMouseEntered(mouseEvent ->
                Clients.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));

        Clients.setOnMouseExited(mouseEvent -> {
            Clients.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }
    public void onMouse_Clicked_Clients() throws IOException {
        Clients.setOnMouseEntered(mouseEvent ->
                Clients.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
        ));
        Clients();
    }

    public void Transactions() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/newpage.fxml"));
            Node newPage = loader.load();

            view.getChildren().clear();
            view.getChildren().add(newPage);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the new page.");
        }
    }

    public void onMouse_Entered_Transactions(){
        Transactions.setOnMouseEntered(mouseEvent ->
                Transactions.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));

        Transactions.setOnMouseExited(mouseEvent -> {
            Transactions.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }
    public void onMouse_Clicked_Transactions() throws IOException {
        Transactions.setOnMouseClicked(mouseEvent -> {
            Transactions.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white");
        });

        Transactions();
    }

    public void Contacts() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/contactUs.fxml"));
            Node newPage = loader.load();

            view.getChildren().clear();
            view.getChildren().add(newPage);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the new page.");
        }
    }
    public void onMouse_Entered_Contacts(){
        Contacts.setOnMouseEntered(mouseEvent ->
                Contacts.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));

        Contacts.setOnMouseExited(mouseEvent -> {
            Contacts.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }
    public void onMouse_Clicked_Contacts() throws IOException {
        Contacts.setOnMouseEntered(mouseEvent ->
                Contacts.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));
        Contacts();
    }

    public void Admin_Console() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("newpage.fxml"));
            Node newPage = loader.load();

            view.getChildren().clear();
            view.getChildren().add(newPage);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the new page.");
        }
    }
    public void onMouse_Entered_Admin_Console(){
        Admin_Console.setOnMouseEntered(mouseEvent ->
                Admin_Console.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));

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