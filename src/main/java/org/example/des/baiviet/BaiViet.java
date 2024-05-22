package org.example.des.baiviet;

//Cần import những thư viện này của Joup
import java.io.BufferedWriter;
import java.io.FileWriter;

//Cần import những thư viện này của gson
import com.google.gson.*;
import java.io.FileReader;

// BaiViet là class cha
public class BaiViet {

    // Các thuốc tính của 1 bài viết
    private String link;
    private String nguon;
    private String tieuDe;
    private String chuyenMuc;
    private String tacGia;
    private String thoigian;
    private String tags;
    private String noidung;


    // Cài đặt các phương thức getter and setter cho các thuộc tính
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getNguon() {
        return nguon;
    }

    public void setNguon(String nguon) {
        this.nguon = nguon;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getChuyenMuc() {
        return chuyenMuc;
    }

    public void setChuyenMuc(String chuyenMuc) {
        this.chuyenMuc = chuyenMuc;
    }

}
