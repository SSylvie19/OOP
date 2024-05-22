package org.example.des.baiviet;

//Cần import những thư viện này của Joup
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class The_blockchain extends BaiViet {

    public The_blockchain(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            //Link bài
            this.setLink(url);

            //Nguồn bài viết
            this.setNguon("https://www.the-blockchain.com");

            //Tiêu đề
            String td= doc.select("h1.entry-title").text();
            this.setTieuDe(td);

            //Chuyên mục
            Elements cm = doc.select("li.entry-category");
            String cmuc = cm.getLast().text();
            this.setChuyenMuc(cmuc);

            //Tác giả
            String tgia = doc.select("div.td-post-author-name > a").text();
            this.setTacGia(tgia);

            //Thời gian
            String thoiGian = "";
            Elements tg = doc.select("time.entry-date.updated.td-module-date");
            for (Element tgText : tg) {
                String ndText = tgText.text() ;
                thoiGian = thoiGian + ndText + " ";
                break;
            }
            this.setThoigian(thoiGian);

            //Tags
            this.setTags("Không có");


            //Nội dung bài viết
            String noidung = "";

            Elements noidungbv = doc.select("p");
            for (Element nd : noidungbv) {
                String ndText = nd.text() ;
                noidung = noidung + ndText + " ";
            }
            this.setNoidung(noidung);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
