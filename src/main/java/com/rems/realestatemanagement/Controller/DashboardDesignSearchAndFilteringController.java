package com.rems.realestatemanagement.Controller;

import javafx.animation.*;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashboardDesignSearchAndFilteringController {
    public MenuButton Type;
    public MenuButton Filter;
    public MenuButton Location;
    public ChoiceBox Availability;
    @FXML private Button Admin_Console;
    @FXML private Button Clients;
    @FXML private Button Offer;
    @FXML private Button Interactions;
    @FXML private Button Contacts;
    @FXML private Button Property_management;
    @FXML private VBox propertySubmenu;
    @FXML private Button propertyButton;
    @FXML private Button addPropertyButton;
    @FXML private AnchorPane view;
    @FXML private VBox navigationContainer;
    @FXML private VBox propertyManagementSection;

    private List<Button> buttons;
    private Button activeButton;
    private String activeMenuChoice;
    private boolean isSubmenuAnimating = false;
    private ChangeListener<Number> stageResizeListener;

    private static final String BUTTON_STYLE_NORMAL = "-fx-background-color: white; -fx-text-fill: #1D4634; " +
            "-fx-font-size: 16px; -fx-min-width: 180; -fx-alignment: CENTER_LEFT; " +
            "-fx-padding: 10 15; -fx-background-radius: 5; -fx-border-radius: 5;";

    private static final String BUTTON_STYLE_ACTIVE = "-fx-background-color: #1D4634; -fx-text-fill: white; " +
            "-fx-font-size: 16px; -fx-min-width: 180; -fx-alignment: CENTER_LEFT; " +
            "-fx-padding: 10 15; -fx-background-radius: 5; -fx-border-radius: 5;";

    private static final String SUBMENU_BUTTON_STYLE_NORMAL = "-fx-background-color: white; -fx-text-fill: #1D4634; " +
            "-fx-font-size: 14px; -fx-min-width: 160; -fx-alignment: CENTER_LEFT; " +
            "-fx-padding: 8 12; -fx-background-radius: 4; -fx-border-radius: 4;";

    private static final String SUBMENU_BUTTON_STYLE_ACTIVE = "-fx-background-color: #1D4634; -fx-text-fill: white; " +
            "-fx-font-size: 14px; -fx-min-width: 160; -fx-alignment: CENTER_LEFT; " +
            "-fx-padding: 8 12; -fx-background-radius: 4; -fx-border-radius: 4;";

    @FXML
    public void initialize() {




        buttons = new ArrayList<>();
        buttons.add(Admin_Console);
        buttons.add(Clients);
        buttons.add(Offer);
        buttons.add(Interactions);
        buttons.add(Contacts);
        setupButtonEffects();
        setupPropertyManagement();
        setupResponsiveness();
        Admin_Console();
        propertySubmenu.setVisible(false);
        propertySubmenu.setManaged(false);
    }

    private void setupButtonHoverEffects(Button button, DropShadow shadow) {
        String normalStyle = button == Property_management || button == propertyButton || button == addPropertyButton ?
                SUBMENU_BUTTON_STYLE_NORMAL : BUTTON_STYLE_NORMAL;
        String activeStyle = button == Property_management || button == propertyButton || button == addPropertyButton ?
                SUBMENU_BUTTON_STYLE_ACTIVE : BUTTON_STYLE_ACTIVE;

        button.setOnMouseEntered(e -> {
            if (button != activeButton) {
                button.setEffect(shadow);
                button.setStyle(activeStyle);
            }
        });

        button.setOnMouseExited(e -> {
            if (button != activeButton) {
                button.setEffect(null);
                button.setStyle(normalStyle);
            }
        });
    }


    private void setupResponsiveness() {
        // Add resize listener to adjust UI elements based on window size
        Scene scene = Admin_Console.getScene();
        if (scene != null) {
            scene.widthProperty().addListener((obs, oldVal, newVal) -> {
                double width = newVal.doubleValue();
                adjustUIForWidth(width);
            });

            scene.heightProperty().addListener((obs, oldVal, newVal) -> {
                double height = newVal.doubleValue();
                adjustUIForHeight(height);
            });
        }
    }

    private void adjustUIForWidth(double width) {
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double scaleFactor = Math.min(width / screenWidth, 1.2);

        // Adjust font sizes
        String baseFontSize = String.format("-fx-font-size: %.1fpx;", 16 * scaleFactor);
        String submenuFontSize = String.format("-fx-font-size: %.1fpx;", 14 * scaleFactor);

        // Update button sizes and styles
        buttons.forEach(button -> updateButtonStyle(button, baseFontSize, true));
        updateButtonStyle(Property_management, baseFontSize, true);
        updateButtonStyle(propertyButton, submenuFontSize, false);
        updateButtonStyle(addPropertyButton, submenuFontSize, false);
    }

    private void adjustUIForHeight(double height) {
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        double scaleFactor = Math.min(height / screenHeight, 1.2);

        // Adjust spacing between buttons
        navigationContainer.setSpacing(15 * scaleFactor);
    }

    private void updateButtonStyle(Button button, String fontSize, boolean isMainButton) {
        String baseStyle = isMainButton ? BUTTON_STYLE_NORMAL : SUBMENU_BUTTON_STYLE_NORMAL;
        button.setStyle(baseStyle + fontSize);
    }

    private void setupButtonEffects() {
        DropShadow hoverShadow = new DropShadow();
        hoverShadow.setColor(Color.rgb(0, 0, 0, 0.1));
        hoverShadow.setRadius(10);
        hoverShadow.setSpread(0.1);

        for (Button button : buttons) {
            setupButtonHoverEffects(button, hoverShadow);
        }

        setupButtonHoverEffects(Property_management, hoverShadow);
        setupButtonHoverEffects(propertyButton, hoverShadow);
        setupButtonHoverEffects(addPropertyButton, hoverShadow);
    }

    private void setupPropertyManagement() {
        Property_management.setOnAction(e -> togglePropertySubmenu());

        propertyButton.setOnAction(e -> handlePropertyMenuItemClick("Property"));
        addPropertyButton.setOnAction(e -> handlePropertyMenuItemClick("Add Property"));
    }

    private void togglePropertySubmenu() {
        if (isSubmenuAnimating) return;
        isSubmenuAnimating = true;

        if (!propertySubmenu.isVisible()) {
            // Show submenu with animation
            propertySubmenu.setVisible(true);
            propertySubmenu.setManaged(true);

            // Fade in animation
            FadeTransition fadeIn = new FadeTransition(Duration.millis(200), propertySubmenu);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            // Slide down animation
            TranslateTransition slideDown = new TranslateTransition(Duration.millis(200), propertySubmenu);
            slideDown.setFromY(-20);
            slideDown.setToY(0);

            ParallelTransition parallel = new ParallelTransition(fadeIn, slideDown);
            parallel.setOnFinished(e -> {
                isSubmenuAnimating = false;
                Property_management.setStyle(BUTTON_STYLE_ACTIVE);
            });
            parallel.play();
        } else {
            // Hide submenu with animation
            FadeTransition fadeOut = new FadeTransition(Duration.millis(200), propertySubmenu);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);

            TranslateTransition slideUp = new TranslateTransition(Duration.millis(200), propertySubmenu);
            slideUp.setFromY(0);
            slideUp.setToY(-20);

            ParallelTransition parallel = new ParallelTransition(fadeOut, slideUp);
            parallel.setOnFinished(e -> {
                propertySubmenu.setVisible(false);
                propertySubmenu.setManaged(false);
                isSubmenuAnimating = false;
                if (!isPropertyManagementActive()) {
                    Property_management.setStyle(BUTTON_STYLE_NORMAL);
                }
            });
            parallel.play();
        }
    }

    private void handlePropertyMenuItemClick(String option) {
        activeMenuChoice = option;
        highlightMenuButton(option);

        propertyButton.setStyle(option.equals("Property") ?
                SUBMENU_BUTTON_STYLE_ACTIVE : SUBMENU_BUTTON_STYLE_NORMAL);
        addPropertyButton.setStyle(option.equals("Add Property") ?
                SUBMENU_BUTTON_STYLE_ACTIVE : SUBMENU_BUTTON_STYLE_NORMAL);

        switch (option) {
            case "Property":
                loadPage("/com/rems/realestatemanagement/proparty-card.fxml");
                break;
            case "Add Property":
                loadPage("/com/rems/realestatemanagement/AddProperty.fxml");
                break;
        }
    }

    private void highlightButton(Button selectedButton) {
        Property_management.setStyle(BUTTON_STYLE_NORMAL);
        propertySubmenu.setVisible(false);
        propertySubmenu.setManaged(false);
        activeMenuChoice = null;

        buttons.forEach(button ->
                button.setStyle(button == selectedButton ? BUTTON_STYLE_ACTIVE : BUTTON_STYLE_NORMAL));
        activeButton = selectedButton;
    }

    private boolean isPropertyManagementActive() {
        return activeMenuChoice != null &&
                (activeMenuChoice.equals("Property") || activeMenuChoice.equals("Add Property"));
    }

    private void highlightMenuButton(String selectedOption) {
        if (selectedOption != null) {
            activeMenuChoice = selectedOption;
            Property_management.setStyle(BUTTON_STYLE_ACTIVE);
            propertySubmenu.setVisible(true);
            propertySubmenu.setManaged(true);
            buttons.forEach(button -> button.setStyle(BUTTON_STYLE_NORMAL));
            activeButton = null;
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
        }
    }

    // Navigation methods
    @FXML public void Admin_Console() {
        loadPage("/com/rems/realestatemanagement/AgentProfile-view.fxml");
        highlightButton(Admin_Console);
    }

    @FXML public void Clients() {
        loadPage("/com/rems/realestatemanagement/Client.fxml");
        highlightButton(Clients);
    }

    @FXML public void Offer() {
        loadPage("/com/rems/realestatemanagement/OffersView.fxml");
        highlightButton(Offer);
    }

    @FXML public void Interactions() {
        loadPage("/com/rems/realestatemanagement/Interactions.fxml");
        highlightButton(Interactions);
    }

    @FXML public void Contacts() {
        loadPage("/com/rems/realestatemanagement/contactUs.fxml");
        highlightButton(Contacts);
    }

}