package com.rems.realestatemanagement.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashboardDesignSearchAndFilteringController {

    @FXML
    private Button Admin_Console;

    @FXML
    private Button Clients;

    @FXML
    private Button Offer;

    @FXML
    private Button Interactions;

    @FXML
    private Button Contacts;

    @FXML
    private ChoiceBox<String> Property_management;

    @FXML
    private AnchorPane view;

    private List<Button> buttons;

    private Button activeButton;

    private String activeChoice;

    @FXML
    public void initialize() {
        buttons = new ArrayList<>();
        buttons.add(Admin_Console);
        buttons.add(Clients);
        buttons.add(Offer);
        buttons.add(Interactions);
        buttons.add(Contacts);

        // Property_management.setPromptText("Property Management");
        Property_management.getItems().addAll(
                "Property",
                "Add Property"
        );

        Property_management.setOnMouseEntered(event -> {
            Property_management.show();
        });

        for (Button button : buttons) {
            button.setOnMouseEntered(event -> {
                if (button != activeButton) {
                    button.setStyle("-fx-background-color: #1D4634; -fx-text-fill: white;" +
                            "-fx-font-size: 16px;" +
                            "-fx-min-width: 180;" +
                            "-fx-alignment: CENTER_LEFT;" +
                            "-fx-padding: 8 12;");
                }
            });

            button.setOnMouseExited(event -> {
                if (button != activeButton) {
                    button.setStyle("-fx-background-color: white; -fx-text-fill: #1D4634;" +
                            "-fx-font-size: 16px;" +
                            "-fx-min-width: 180;" +
                            "-fx-alignment: CENTER_LEFT;" +
                            "-fx-padding: 8 12;");
                }
            });
        }
    }

    private void highlightButton(Button selectedButton) {
        for (Button button : buttons) {
            if (button == selectedButton) {
                button.setStyle("-fx-background-color: #1D4634; -fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-min-width: 180;" +
                        "-fx-alignment: CENTER_LEFT;" +
                        "-fx-padding: 8 12;");
                activeButton = button;
            } else {
                button.setStyle("-fx-background-color: white; -fx-text-fill: #1D4634;" +
                        "-fx-font-size: 16px;" +
                        "-fx-min-width: 180;" +
                        "-fx-alignment: CENTER_LEFT;" +
                        "-fx-padding: 8 12;");
            }
        }
    }

    private void highlightChoiceBox(String selectedOption) {
        if (selectedOption != null) {
            activeChoice = selectedOption;
            Property_management.setStyle("-fx-background-color: #1D4634; -fx-text: white;" +
                    "-fx-font-size: 16px;" +
                    "-fx-padding: 8 12;");
        } else {
            Property_management.setStyle("-fx-background-color: white; -fx-text: #1D4634;" +
                    "-fx-font-size: 16px;" +
                    "-fx-padding: 8 12;");
        }
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

    public void Admin_Console() {
        loadPage("/com/rems/realestatemanagement/AgentProfile-view.fxml");
        highlightButton(Admin_Console);
    }

    public void Property_management() {
        Property_management.setOnAction(event -> {
            String selectedOption = Property_management.getValue();

            switch (selectedOption) {
                case "Property":
                    highlightChoiceBox("Property");
                    loadPage("/com/rems/realestatemanagement/proparty-card.fxml");
                    break;
                case "Add Property":
                    highlightChoiceBox("Add Property");
                    loadPage("/com/rems/realestatemanagement/property.fxml");
                    break;
                default:
                    System.out.println("Unknown option: " + selectedOption);
            }
        });
    }

    public void Clients() {
        loadPage("/com/rems/realestatemanagement/Client.fxml");
        highlightButton(Clients);
    }

    public void Offer() {
        loadPage("/com/rems/realestatemanagement/OffersView.fxml");
        highlightButton(Offer);
    }

    public void Interactions() {
        loadPage("/com/rems/realestatemanagement/Interactions.fxml");
        highlightButton(Interactions);
    }

    public void Contacts() {
        loadPage("/com/rems/realestatemanagement/contactUs.fxml");
    }
}