package org.example.des.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TimKiemController extends ScenceController {
    @FXML
    private AnchorPane timkiem;

    @FXML
    private Button macdinh_btn;

    @FXML
    private Button tukhoa_btn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchtoMacDinh(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/org/example/des/MacDinh.fxml");
        if (resource == null) {
            System.out.println("MacDinh.fxml not found");
            return;
        }
        System.out.println("Switching to MacDinh.fxml");
        root = FXMLLoader.load(resource);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoTuKhoa(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/org/example/des/TuKhoa.fxml");
        if (resource == null) {
            System.out.println("TuKhoa.fxml not found");
            return;
        }
        System.out.println("Switching to TuKhoa.fxml");
        root = FXMLLoader.load(resource);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
