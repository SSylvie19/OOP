package org.example.des.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TrangChuController extends ScenceController implements Initializable {

    @FXML
    private AnchorPane trangchu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("TrangChuController initialized");
    }
}
