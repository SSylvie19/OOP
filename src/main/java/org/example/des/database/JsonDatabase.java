package org.example.des.database;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.example.des.baiviet.*;

public class JsonDatabase implements Database {
    private String name;
    private List<String> danhSach = new ArrayList<>();

    public JsonDatabase(String name) {
        this.name = name;
        danhSach.add("https://blockchain.news");
        danhSach.add("https://www.forbes.com");
        danhSach.add("https://www.coindesk.com");
        danhSach.add("https://www.the-blockchain.com");
        danhSach.add("https://decrypt.co");
        danhSach.add("https://u.today");
        danhSach.add("https://blockworks.co");
        danhSach.add("https://www.bsc.news");
        danhSach.add("https://ripplecoinnews.com");
    }

    @Override
    public void saveToDatabase (BaiViet baiViet){
        try {
            //Ghi thông tin đối tượng vào Stored_File.json
            FileReader fileReader = new FileReader(this.getJsonName());
            JsonParser jsonParser = new JsonParser();
            JsonArray dataArray = (JsonArray) jsonParser.parse(fileReader);

            JsonObject newObject = new JsonObject();
            newObject.addProperty("Link",baiViet.getLink() );
            newObject.addProperty("Nguồn", baiViet.getNguon() );
            newObject.addProperty("Tiêu đề", baiViet.getTieuDe() );
            newObject.addProperty("Chuyên mục", baiViet.getChuyenMuc() );
            newObject.addProperty("Tác giả", baiViet.getTacGia() );
            newObject.addProperty("Ngày tạo", baiViet.getThoigian() );
            newObject.addProperty("Tags", baiViet.getTags() );
            newObject.addProperty("Nội dung", baiViet.getNoidung() );

            // Add the new object to the JsonArray
            dataArray.add(newObject);
            int Size = dataArray.size();

            FileWriter fw = new FileWriter(this.getJsonName());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("[");
            bw.newLine();
            for (int i=0; i<Size-1; i++){
                String o = dataArray.get(i).toString();
                bw.write(o);
                bw.write(",");
                bw.newLine();
            }
            String o = dataArray.get(Size-1).toString();
            bw.write(o);
            bw.newLine();
            bw.write("]");
            bw.close();
            fw.close();

            //Ghi thông tin đối tượng vào Stored_File.csv
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.getCsvName(),true));
            writer.write(
                    baiViet.getLink() + ", " +
                            baiViet.getNguon() + ", " +
                            baiViet.getTieuDe() + ", " +
                            baiViet.getChuyenMuc() + ", " +
                            baiViet.getTacGia() + ", " +
                            baiViet.getThoigian() + ", " +
                            baiViet.getTags() + ", " +
                            baiViet.getNoidung()
            );
            writer.newLine();
            writer.close();

        } catch (Exception e) {

        }
    }

    @Override
    public int checkSource(String url) {
        if (url.indexOf("https://blockchain.news/news") >= 0) {
            // Dùng hàm check kiểm tra xem bài viết đã có trong file Stored_File.json
            // Nếu đã có thì chỉ thông báo "Bài viết đã có trong cơ sở dữ liệu từ trước."
            if ((this.checkInDatabase(url)) == true) return 0;

                // Nếu chưa có thì khởi tạo đối tượng thuộc loại bài viết đó
                // Dùng phương thức saveToStoredFile() để lưu thông tin bài viết vào file Stored_File.json
                // Thông báo "Cập nhật thông tin bài viết thành công !"
            else {
                this.saveToDatabase(new Blockchain(url));
                return 1;
            }

        } else if (url.indexOf("https://www.forbes.com/sites/digital-assets") >= 0) {
            if ((this.checkInDatabase(url)) == true) return 0;
            else {
                this.saveToDatabase(new Forbes(url));
                return 1;
            }
        } else if (url.indexOf("https://www.coindesk.com") >= 0) {
            if ((this.checkInDatabase(url)) == true) return 0;
            else {
                this.saveToDatabase(new Coindesk(url));
                return 1;
            }
        }else if (url.indexOf("https://www.the-blockchain.com/") >= 0) {
            if ((this.checkInDatabase(url)) == true) return 0;
            else {
                this.saveToDatabase(new The_blockchain(url));
                return 1;
            }
        }else if (url.indexOf("https://decrypt.co/") >= 0) {
            if ((this.checkInDatabase(url)) == true) return 0;
            else {
                this.saveToDatabase(new Decrypt(url));
                return 1;
            }
        }else if (url.indexOf("https://u.today/") >= 0) {
            if ((this.checkInDatabase(url)) == true) return 0;
            else {
                this.saveToDatabase(new U_today(url));
                return 1;
            }
        }else if (url.indexOf("https://blockworks.co/news/") >= 0) {
            if ((this.checkInDatabase(url)) == true) return 0;
            else {
                this.saveToDatabase(new Blockworks(url));
                return 1;
            }
        }else if (url.indexOf("https://www.bsc.news/post/") >= 0) {
            if ((this.checkInDatabase(url)) == true) return 0;
            else {
                this.saveToDatabase(new Bsc_news(url));
                return 1;
            }
        }else if (url.indexOf("https://ripplecoinnews.com/") >= 0) {
            if ((this.checkInDatabase(url)) == true) return 0;
            else {
                this.saveToDatabase(new Ripplecoinnews(url));
                return 1;
            }
        }else {
            return 2;
        }
    }

    @Override
    public boolean checkInDatabase(String url) {
        try {
            FileReader fileReader = new FileReader(this.getJsonName());
            JsonParser jsonParser = new JsonParser();
            JsonArray dataArray = (JsonArray) jsonParser.parse(fileReader);

            int Size = dataArray.size();
            int xn = 0;

            for (int i = 0; i < Size; i++) {
                JsonObject o = (JsonObject) dataArray.get(i);
                String s = o.get("Link").toString();
                if (s.indexOf(url) >= 0) {
                    xn = 1;
                }
            }

            if (xn == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
        }
        return false;
    }


    public String getJsonName() {
        return name+".json";
    }

    public String getCsvName() {
        return name+".csv";
    }

    public List<String> getDanhSach() {
        return danhSach;
    }
}
