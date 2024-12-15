package com.rems.realestatemanagement.Controller;
import com.rems.realestatemanagement.models.Agent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ActionCellFactory implements Callback<TableColumn<Agent, Void>, TableCell<Agent, Void>>{
    @Override
    public TableCell<Agent, Void> call(TableColumn<Agent, Void> param) {
        return new TableCell<Agent, Void>() {
            private final Button editButton = new Button("✏️");
            private final Button deleteButton = new Button("🗑️");
            private final HBox pane = new HBox(5, editButton, deleteButton);

            {
                editButton.setOnAction(event -> {
                    Agent agent = getTableView().getItems().get(getIndex());
                    System.out.println("Edit clicked for: " + agent.getAgentName());
                    // Code to open Edit form can go here
                });

                deleteButton.setOnAction(event -> {
                    Agent agent = getTableView().getItems().get(getIndex());
                    System.out.println("Delete clicked for: " + agent.getAgentName());
                    getTableView().getItems().remove(agent); // Remove the agent
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        };
    }
}
