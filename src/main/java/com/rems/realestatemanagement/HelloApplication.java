package com.rems.realestatemanagement;
import com.rems.realestatemanagement.models.services.RolePermissionService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        initializeData();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeData() {
        try {
            RolePermissionService rolePermissionService = new RolePermissionService();
            rolePermissionService.setUpRolesAndPermissions();
            System.out.println("Roles and permissions initialized successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}