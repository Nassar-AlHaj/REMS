package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.models.Agent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class AgentController {

    @FXML
    private TableView<Agent> agentTableView;

    @FXML
    private Button addAgentButton;

    private ObservableList<Agent> agentData;

    public void initialize() {
        // Initialize TableView with sample data
        agentData = FXCollections.observableArrayList(
                new Agent(1, "Lindsey Stroud", "lindsey.stroud@gmail.com", "5f4dcc3b5aa765d61d8327deb882cf99"),
                new Agent(2, "Sarah Brown", "sarah.brown@gmail.com", "5f4dcc3b5aa765d61d8327deb882cf99"),
                new Agent(4, "Micheal Owen", "micheal.owen@gmail.com", "5f4dcc3b5aa765d61d8327deb882cf99"),
                new Agent(5, "Mary Jane", "mary.jane@gmail.com", "5f4dcc3b5aa765d61d8327deb882cf99"),
                new Agent(6, "Peter Dodle", "peter.dodle@gmail.com", "5f4dcc3b5aa765d61d8327deb882cf99")
        );

        agentTableView.setItems(agentData);
    }

    @FXML
    private void handleAddAgent() {
        System.out.println("Add Agent button clicked.");
        // Code to display Add Agent form can be added here
    }



}