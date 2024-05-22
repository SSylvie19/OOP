package org.example.des.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.FileReader;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class MacDinhController extends ScenceController implements Initializable {
    @FXML
    private AnchorPane macdinh;

    @FXML
    private ComboBox chonnguon_tk_cbb;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList(DB.getDanhSach());
        chonnguon_tk_cbb.setItems(list);
        set_dataArray_tk();
    }

    private JsonArray dataArray_tk3 = new JsonArray();
    private JsonArray dataArray_tk2 = new JsonArray();
    private JsonArray dataArray_tk1 = new JsonArray();
    private JsonArray dataArray_tk = new JsonArray();

    public void set_dataArray_tk() {
        try {
            FileReader fileReader_tk = new FileReader(DB.getJsonName());
            JsonParser jsonParser_tk = new JsonParser();
            dataArray_tk = (JsonArray) jsonParser_tk.parse(fileReader_tk);
        } catch (Exception e) {

        }
    }

    /* Controller cho giao diện Tìm kiếm mặc định */

    // Phương thức để chọn ra các chuyên mục tương ứng với nguồn đã chọn có trong file Stored_File.json
    // và đưa vào comboBox
    @FXML
    public void chonnguon_tk(ActionEvent event) {
        try {
            String nguon_tk = (String) chonnguon_tk_cbb.getValue();

            for (int i = dataArray_tk1.size() - 1; i >= 0; i--) {
                dataArray_tk1.remove(i);
            }
            chuyenmuc_tk_cbb.setValue(null);
            ta_tk.clear();

            Set<String> chuyenmuc_tk_set = new HashSet<>();
            int Size = dataArray_tk.size();
            for (int i = 0; i < Size; i++) {
                JsonObject o = (JsonObject) dataArray_tk.get(i);
                String s = o.get("Nguồn").toString();
                if (s.indexOf(nguon_tk) >= 0) {
                    chuyenmuc_tk_set.add(o.get("Chuyên mục").toString());
                    dataArray_tk1.add(o);
                }
            }
            ObservableList<String> list = FXCollections.observableArrayList(chuyenmuc_tk_set);
            chuyenmuc_tk_cbb.setItems(list);
            chuyenmuc_tk_set.clear();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private ComboBox chuyenmuc_tk_cbb;

    // Phương thức để chọn ra các tác giả tương ứng với chuyên mục đã chọn
    // và đưa vào comboBox
    @FXML
    public void chuyenmuc_tk(ActionEvent event) {
        try {
            String chuyenmuc_tk = (String) chuyenmuc_tk_cbb.getValue();

            for (int i = dataArray_tk2.size() - 1; i >= 0; i--) {
                dataArray_tk2.remove(i);
            }
            tacgia_tk_cbb.setValue(null);
            ta_tk.clear();

            Set<String> tacgia_tk_set = new HashSet<>();
            int Size = dataArray_tk1.size();
            for (int i = 0; i < Size; i++) {
                JsonObject o = (JsonObject) dataArray_tk1.get(i);
                String s = o.get("Chuyên mục").toString();
                if (s.indexOf(chuyenmuc_tk) >= 0) {
                    tacgia_tk_set.add(o.get("Tác giả").toString());
                    dataArray_tk2.add(o);
                }
            }
            ObservableList<String> list = FXCollections.observableArrayList(tacgia_tk_set);
            tacgia_tk_cbb.setItems(list);
            tacgia_tk_set.clear();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private ComboBox tacgia_tk_cbb;

    // Phương thức để chọn ra các tiêu đề tương ứng với tác giả đã chọn
    // và đưa vào comboBox
    @FXML
    public void tacgia_tk(ActionEvent event) {
        try {
            String tacgia_tk = (String) tacgia_tk_cbb.getValue();

            for (int i = dataArray_tk3.size() - 1; i >= 0; i--) {
                dataArray_tk3.remove(i);
            }
            baiviet_tk_cbb.setValue(null);
            ta_tk.clear();

            Set<String> baiviet_tk_set = new HashSet<>();
            int Size = dataArray_tk2.size();
            for (int i = 0; i < Size; i++) {
                JsonObject o = (JsonObject) dataArray_tk2.get(i);
                String s = o.get("Tác giả").toString();
                if (s.indexOf(tacgia_tk) >= 0) {
                    baiviet_tk_set.add(o.get("Tiêu đề").toString());
                    dataArray_tk3.add(o);
                }
            }
            ObservableList<String> list = FXCollections.observableArrayList(baiviet_tk_set);
            baiviet_tk_cbb.setItems(list);
            baiviet_tk_set.clear();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private ComboBox baiviet_tk_cbb;

    @FXML
    private TextArea ta_tk;

    // Phương thức để in ra nội dung bài viết tương ứng với tiêu đề đã chọn
    @FXML
    public void baiviet_tk(ActionEvent event) {
        try {
            String baiviet_tk = (String) baiviet_tk_cbb.getValue();

            //set scroll về null
            int Size = dataArray_tk3.size();
            for (int i = 0; i < Size; i++) {
                JsonObject o = (JsonObject) dataArray_tk3.get(i);
                String s = o.get("Tiêu đề").toString();
                if (s.indexOf(baiviet_tk) >= 0) {
                    ta_tk.setText(" Link: " + o.get("Link").toString() + "\n\n"+
                            " Nguồn: " + o.get("Nguồn").toString() + "\n\n"+
                            " Tiêu đề: " + o.get("Tiêu đề").toString() + "\n\n"+
                            " Chuyên mục: " + o.get("Chuyên mục").toString() + "\n\n"+
                            " Tác giả: " + o.get("Tác giả").toString() + "\n\n"+
                            " Ngày tạo: " + o.get("Ngày tạo").toString() + "\n\n"+
                            " Tags: " + o.get("Tags").toString() + "\n\n"+
                            " Nội dung: " + "\n\n"  + o.get("Nội dung").toString()
                    );
                }
            }
        } catch (Exception e) {

        }
    }

    @FXML
    private Button tieptuc_tk_btn;

    // Phương thức để xóa các nội dung trên giao diện khi nhấn Button Xóa
    @FXML
    public void tieptuc_tk(ActionEvent event) {
        chonnguon_tk_cbb.setValue(null);
        chuyenmuc_tk_cbb.setValue(null);
        tacgia_tk_cbb.setValue(null);
        baiviet_tk_cbb.setValue(null);
        ta_tk.clear();
    }

    /* Controller cho giao diện Tìm kiếm mặc định */


}
