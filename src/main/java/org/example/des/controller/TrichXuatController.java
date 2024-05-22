package org.example.des.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class TrichXuatController extends ScenceController implements Initializable {

    @FXML
    private AnchorPane truyxuat;

    @FXML
    private ComboBox<String> chonnguon_tx_cbb;

    @FXML
    private Label label_tx;

    @FXML
    private TextField Link;

    @FXML
    private Button motrang_tx_btn;

    @FXML
    private Button tieptheo_tx_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList(DB.getDanhSach());
        chonnguon_tx_cbb.setItems(list);
    }

    @FXML
    public void motrang_tx(ActionEvent event) {
        String nguon_tx = chonnguon_tx_cbb.getValue();
        try {
            URI uri = new URI(nguon_tx);
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(uri);
                } else {
                    System.out.println("Không thể mở trình duyệt web trên máy tính này.");
                }
            } else {
                System.out.println("Không thể mở trình duyệt web trên máy tính này.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void capNhat(ActionEvent event) {
        System.out.println("cap nhat");
        String url = Link.getText();
        switch (DB.CheckInDatabase(url)) {
            case 0:
                label_tx.setText("Bài viết đã có trong cơ sở dữ liệu từ trước.");
                break;
            case 1:
                label_tx.setText("Cập nhật thông tin bài viết thành công !");
                break;
            default:
                label_tx.setText("Phần mềm chưa cập nhật nguồn website này.");
        }
    }

    @FXML
    public void tieptheo_tx(ActionEvent event) {
        chonnguon_tx_cbb.setValue(null);
        Link.clear();
        label_tx.setText("");
    }
}
