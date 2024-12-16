package com.rems.realestatemanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rems/realestatemanagement/Fxml/OffersView.fxml"));
        Scene scene = new Scene(loader.load());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Offers & Agreements");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}