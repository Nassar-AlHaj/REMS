package com.rems.realestatemanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.IOException;

public class DashboardDesignSearchAndFilteringController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button Dashboard;

    @FXML
    private Button Property;

    @FXML
    private Button Tenants;

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
    private TextField Enter_Keyword;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void Dashboard() {

    }

    public void onMouse_Entered_Dashboard(){
        Dashboard.setOnMouseEntered(mouseEvent ->
                Dashboard.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));

        Dashboard.setOnMouseExited(mouseEvent -> {
            Dashboard.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_Clicked_Dashboard() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
        Dashboard.setOnMouseClicked(mouseEvent -> {
            Dashboard.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white");
        });
    }

    public void Property() {

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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Property");
        stage.setScene(scene);
        stage.show();

        Property.setOnMouseClicked(mouseEvent -> {
            Property.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white");
        });
    }



    public void Tenants() {

    }

    public void onMouse_Entered_Tenants(){
        Tenants.setOnMouseEntered(mouseEvent ->
                Tenants.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));

        Tenants.setOnMouseExited(mouseEvent -> {
            Tenants.setStyle("-fx-background-color: '';-fx-text-fill: black");
        });
    }

    public void onMouse_Clicked_Tenants() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Tenants");
        stage.setScene(scene);
        stage.show();
        Tenants.setOnMouseEntered(mouseEvent ->
                Tenants.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));
    }


    public void Add_Property() {

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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Add_Property");
        stage.setScene(scene);
        stage.show();

        Add_Property.setOnMouseEntered(mouseEvent ->
                Add_Property.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));
    }


    public void Clients() {

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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Clients");
        stage.setScene(scene);
        stage.show();

        Clients.setOnMouseEntered(mouseEvent ->
                Clients.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
        ));

    }

    public void Transactions() {

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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Transactions");
        stage.setScene(scene);
        stage.show();
        Transactions.setOnMouseClicked(mouseEvent -> {
            Transactions.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white");
        });
    }

    public void Contacts() {

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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Contacts");
        stage.setScene(scene);
        stage.show();

        Contacts.setOnMouseEntered(mouseEvent ->
                Contacts.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white"
                ));
    }

    public void Admin_Console() {

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

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Admin Console");
        stage.setScene(scene);
        stage.show();

        Admin_Console.setOnMouseClicked(mouseEvent -> {
            Admin_Console.setStyle("-fx-background-color: #1D4634;-fx-text-fill: white");
        });
    }
}