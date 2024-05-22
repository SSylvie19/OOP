package org.example.des;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            URL fxmlLocation = getClass().getResource("TrangChu.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found");
                return;
            }
            System.out.println("FXML file found at: " + fxmlLocation);

            Parent root = FXMLLoader.load(fxmlLocation);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML file: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
