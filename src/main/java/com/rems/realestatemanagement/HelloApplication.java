package com.rems.realestatemanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //contactUs.fxml
        //popup.fxml
        //Property.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/rems/realestatemanagement/Fxml/Property.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Real Estate Management System!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}