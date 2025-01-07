package com.rems.realestatemanagement.Controller;

import com.rems.realestatemanagement.models.Property;
import com.rems.realestatemanagement.models.Role;
import com.rems.realestatemanagement.models.User;
import com.rems.realestatemanagement.models.services.PropertyDAOImp;
import com.rems.realestatemanagement.models.services.UsersDAOImp;
import com.rems.realestatemanagement.session.UserSession;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DashBoardControllet {

    public javafx.scene.image.ImageView show_Image;
    public javafx.scene.image.ImageView Image_Property_view;
    public Label Proparty_name_M;
    public Label Proparty_Price_M;
    public Label Proparty_Location_M;
    @FXML
    private Label property_Location;
    @FXML
    private GridPane statsGrid;

    @FXML
    private StackPane monthlyChartContainer;

    @FXML
    private StackPane distributionChartContainer;

    @FXML
    private VBox recentAgentsContainer;

    @FXML
    Label Total_Agents;

    @FXML
    Label Active_Properties;

    @FXML
    Label Total_Sales;

    @FXML
    Label Average_Sale_Price;

    @FXML
    Label Last_property;

    @FXML
    Label property_Price;

    @FXML
    StackPane Card_Last_Property;

    // private RoleDOAImp roleDOAImp = new RoleDOAImp();
    private final PropertyDAOImp propertyDAOImp = new PropertyDAOImp();

    @FXML
    public void initialize() throws InstantiationException, IllegalAccessException {
        // Add window resize listener
        Platform.runLater(() -> {
            Scene scene = statsGrid.getScene();
            scene.widthProperty().addListener((obs, oldVal, newVal) -> {
                adjustLayoutForScreenSize(newVal.doubleValue());
            });

            // Initial layout adjustment
            adjustLayoutForScreenSize(scene.getWidth());
        });

        // Initialize charts and load data



        setupCharts();
        showCardLastProparty();
        showCardMost_expensive_properties();
    }

    private void adjustLayoutForScreenSize(double width) {
        // Adjust grid layout for different screen sizes
        statsGrid.getColumnConstraints().clear();

        if (width < 800) {
            // Mobile layout - single column
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100);
            statsGrid.getColumnConstraints().add(column);
            statsGrid.setHgap(10);

            // Adjust chart container layout
            monthlyChartContainer.getParent().setStyle("-fx-min-height: 300;");
            distributionChartContainer.getParent().setStyle("-fx-min-height: 300;");
        } else if (width < 1200) {
            // Tablet layout - two columns
            for (int i = 0; i < 2; i++) {
                ColumnConstraints column = new ColumnConstraints();
                column.setPercentWidth(50);
                statsGrid.getColumnConstraints().add(column);
            }
            statsGrid.setHgap(15);
        } else {
            // Desktop layout - four columns
            for (int i = 0; i < 4; i++) {
                ColumnConstraints column = new ColumnConstraints();
                column.setPercentWidth(25);
                statsGrid.getColumnConstraints().add(column);
            }
            statsGrid.setHgap(20);
        }
    }


    private void setupCharts() throws InstantiationException, IllegalAccessException {

        List<Property> properties = propertyDAOImp.getAllProperties();
        UsersDAOImp usersDOA = new UsersDAOImp();
        List<User> users = usersDOA.getAllUsers();

        int availableCount = 0;
        double totalSales = 0.0;
        int salesCount = 0;
        int totalAgents = 0;

        for (User user : users) {
            if (user.getRoleId() == 1) {
                totalAgents++;
            }
        }

        for (Property p : properties) {
            if ("Available".equals(p.getState())) {
                availableCount++;
            } else if ("Sold".equals(p.getState()) || "Rented".equals(p.getState())) {
                totalSales += p.getPrice();
                salesCount++;
            }
        }

        int finalAvailableCount = availableCount;
        double finalTotalSales = totalSales;
        int finalSalesCount = salesCount;
        int finalTotalAgents = totalAgents;

        Platform.runLater(() -> Total_Agents.setText(String.valueOf(finalTotalAgents)));
        Platform.runLater(() -> Active_Properties.setText(String.valueOf(finalAvailableCount)));
        Platform.runLater(() -> Total_Sales.setText(String.valueOf(finalTotalSales)));
        Platform.runLater(() -> {
            if (finalSalesCount > 0) {
                Average_Sale_Price.setText(String.format("%.2f", finalTotalSales / finalSalesCount));
            } else {
                Average_Sale_Price.setText("N/A");
            }
        });

        System.out.println("Total Agents: " + finalTotalAgents);
        System.out.println("Available Properties: " + finalAvailableCount);
        System.out.println("Total Sales Value: " + finalTotalSales);
        System.out.println("Average Sale Price: " + (finalSalesCount > 0 ? finalTotalSales / finalSalesCount : "N/A"));
    }


    public void showCardLastProparty() {

        List<Property> properties = propertyDAOImp.getAllProperties();

        Property latestProperty = properties.stream()
                .max(Comparator.comparing(Property::getlistingDate))
                .orElse(null);

        if (latestProperty != null) {
            Last_property.setText(latestProperty.getPropertyName());
            property_Price.setText(String.valueOf(latestProperty.getPrice()));
            property_Location.setText(latestProperty.getLocation());

            String imagePath = latestProperty.getImageProperty();
            if (imagePath != null && !imagePath.isEmpty()) {
                try {
                    javafx.scene.image.Image image = new javafx.scene.image.Image(imagePath);
                    show_Image.setImage(image);
                } catch (Exception e) {
                    System.err.println("Error loading image: " + e.getMessage());
                    show_Image.setImage(null);
                }
            } else {
                show_Image.setImage(null);
            }


        } else {
            Last_property.setText("No properties found");
            show_Image.setImage(null);
        }
    }

    public void showCardMost_expensive_properties() {
        List<Property> properties = propertyDAOImp.getAllProperties();

        Property latestProperty = properties.stream()
                .max(Comparator.comparing(Property::getPrice))
                .orElse(null);

        if (latestProperty != null) {
            Proparty_name_M.setText(latestProperty.getPropertyName());
            Proparty_Price_M.setText(String.valueOf(latestProperty.getPrice()));
            Proparty_Location_M.setText(latestProperty.getLocation());

            String imagePath = latestProperty.getImageProperty();
            if (imagePath != null && !imagePath.isEmpty()) {
                try {
                    javafx.scene.image.Image image = new javafx.scene.image.Image(imagePath);
                    Image_Property_view.setImage(image);
                } catch (Exception e) {
                    System.err.println("Error loading image: " + e.getMessage());
                    Image_Property_view.setImage(null);
                }
            } else {
                Image_Property_view.setImage(null);
            }


        } else {
            Proparty_name_M.setText("No properties found");
            Image_Property_view.setImage(null);
        }
    }

}