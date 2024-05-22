package org.example.des.baiviet;

//Cần import những thư viện này của Joup
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//Cần import những thư viện này của gson


public class Decrypt extends BaiViet {
    public Decrypt(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            //Link bài
            this.setLink(url);

            //Nguồn bài viết
            this.setNguon("https://decrypt.co");

            //Tiêu đề
            String td= doc.select("h1").text();
            this.setTieuDe(td);

            //Chuyên mục
            int i = 0; String cmuc = "";
            Elements chuyenmuc = doc.select("span");
            for (Element cmText : chuyenmuc) {
                //noidung = noidung + ndText + " ";
                if(i==257){
                    cmuc  = cmText.text(); break;
                }
                ++i;
            }
            this.setChuyenMuc(cmuc);

            //Tác giả
            String tg = doc.select("span.underline").getFirst().text();
            this.setTacGia(tg);

            //Thời gian
            String tGian = doc.select("time").getLast().text();
            this.setThoigian(tGian);

            //Tags
            this.setTags("Không có");


            //Nội dung bài viết
            String noidung = doc.select("h2").get(1).text() + " ";
            Elements noidungbv = doc.select("p");
            for (int e = 249; e < noidungbv.size()-3; e++) {
                String ndText = noidungbv.get(e).text();
                noidung = noidung + ndText + " ";
            }
            this.setNoidung(noidung);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
