package org.example.des.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.des.database.JsonDatabase;

import java.io.IOException;
import java.net.URL;

public class ScenceController {

    public final JsonDatabase DB = new JsonDatabase("Stored_File");

    @FXML
    private Button timkiem_btn;

    @FXML
    private Button trangchu_btn;

    @FXML
    private Button truyxuat_btn;

    @FXML
    private Button tuvan_btn;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToTrangChu(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/org/example/des/TrangChu.fxml");
        if (resource == null) {
            System.out.println("TrangChu.fxml not found");
            return;
        }
        System.out.println("Switching to TrangChu.fxml");
        root = FXMLLoader.load(resource);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoTimKiem(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/org/example/des/TimKiem.fxml");
        if (resource == null) {
            System.out.println("TimKiem.fxml not found");
            return;
        }
        System.out.println("Switching to TimKiem.fxml");
        root = FXMLLoader.load(resource);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTrichXuat(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/org/example/des/TrichXuat.fxml");
        if (resource == null) {
            System.out.println("TrichXuat.fxml not found");
            return;
        }
        System.out.println("Switching to TrichXuat.fxml");
        root = FXMLLoader.load(resource);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTuVan(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/org/example/des/TuVan.fxml");
        if (resource == null) {
            System.out.println("Tuvan.fxml not found");
            return;
        }
        System.out.println("Switching to Tuvan.fxml");
        root = FXMLLoader.load(resource);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
