package org.example.des.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.FileReader;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class TuKhoaController extends ScenceController implements Initializable {
    @FXML
    private AnchorPane tukhoa;

    /* Controller cho giao diện Tìm kiếm từ khóa */

    @FXML
    private TextField tukhoa_tktk_tf;

    @FXML
    private Button timkiem_tktk_btn;

    @FXML
    private Label thongbao_tktk_lb;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList(DB.getDanhSach());
        set_dataArray_tk();
    }

    private JsonArray dataArray_tk4 = new JsonArray();
    private JsonArray dataArray_tk = new JsonArray();

    public void set_dataArray_tk() {
        try {
            FileReader fileReader_tk = new FileReader(DB.getJsonName());
            JsonParser jsonParser_tk = new JsonParser();
            dataArray_tk = (JsonArray) jsonParser_tk.parse(fileReader_tk);
        } catch (Exception e) {

        }
    }

    @FXML
    public void timkiem_tktk(ActionEvent event) {
        try {
            String tukhoa = tukhoa_tktk_tf.getText();
            int xn = 0;

            for (int i = dataArray_tk4.size() - 1; i >= 0; i--) {
                dataArray_tk4.remove(i);
            }
            //noidung_tktk_sp.setContent(null);
            ta_tktk.clear();

            Set<String> baiviet_tktk_set = new HashSet<>();
            int Size = dataArray_tk.size();
            for (int i = 0; i < Size; i++) {
                JsonObject o = (JsonObject) dataArray_tk.get(i);
                if ((o.get("Link").toString().indexOf(tukhoa) >= 0) || (o.get("Nguồn").toString().indexOf(tukhoa) >= 0)
                        || (o.get("Tiêu đề").toString().indexOf(tukhoa) >= 0) || (o.get("Chuyên mục").toString().indexOf(tukhoa) >= 0)
                        || (o.get("Tác giả").toString().indexOf(tukhoa) >= 0) || (o.get("Ngày tạo").toString().indexOf(tukhoa) >= 0)
                        || (o.get("Tags").toString().indexOf(tukhoa) >= 0) || (o.get("Nội dung").toString().indexOf(tukhoa) >= 0)) {
                    baiviet_tktk_set.add(o.get("Tiêu đề").toString());
                    dataArray_tk4.add(o);
                    xn = 1;
                }
            }
            ObservableList<String> list = FXCollections.observableArrayList(baiviet_tktk_set);
            baiviet_tktk_cbb.setItems(list);
            baiviet_tktk_set.clear();
            if(xn==0) thongbao_tktk_lb.setText("Không tìm được bài viết nào có từ khóa này.");
            else thongbao_tktk_lb.setText("Đã tìm được bài viết có từ khóa này.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private Button tieptuc_tktk_btn;

    // Phương thức xóa đi các nội dung trên giao diện khi nhấn Button Xóa
    @FXML
    public void tieptuc_tktk(ActionEvent event){
        tukhoa_tktk_tf.clear();
        baiviet_tktk_cbb.setValue(null);
        //noidung_tktk_sp.setContent(null);
        thongbao_tktk_lb.setText(null);
        ta_tktk.clear();
    }

    @FXML
    private ComboBox baiviet_tktk_cbb;

    @FXML
    private TextArea ta_tktk;

     //Phương thức để in ra nội dung vài viết tương ứng với tiêu đề đã chọn
    @FXML
    public void baiviet_tktk(ActionEvent event){
        try{
            String baiviet = (String) baiviet_tktk_cbb.getValue();


            int Size = dataArray_tk4.size();
            for (int i = 0; i < Size; i++) {
                JsonObject o = (JsonObject) dataArray_tk4.get(i);
                String s = o.get("Tiêu đề").toString();
                if (s.indexOf(baiviet) >= 0) {
                    ta_tktk.setText(" Link: " + o.get("Link").toString() + "\n\n"+
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

    /* Controller cho giao diện Tìm kiếm từ khóa */
}
