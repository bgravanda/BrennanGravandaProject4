package bsu.comp152.brennangravandaproject4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StoreApplication extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(StoreApplication.class.getResource("StoreDisplay.fxml"));
        Scene scene=null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            System.out.println("Error loading FXMl or exception in controller class");
        }
        stage.setTitle("Showing Store");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}