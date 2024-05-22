package org.example.des.baiviet;

//Cần import những thư viện này của Joup
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Ripplecoinnews extends BaiViet {
    public Ripplecoinnews(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            //Link bài
            this.setLink(url);

            //Nguồn bài viết
            this.setNguon("https://ripplecoinnews.com");

            //Tiêu đề
            String td= doc.select("h1").text();
            this.setTieuDe(td);

            //Chuyên mục
            String cmuc = doc.select("span.mvp-post-cat.left").text();
            this.setChuyenMuc(cmuc);

            //Tác giả
            String tg = doc.select("span.author-name.vcard.fn.author").text();
            this.setTacGia(tg);

            //Thời gian
            String tGian = doc.select("time.post-date.updated").text();
            this.setThoigian(tGian);

            //Tags
            this.setTags("Không có");


            //Nội dung bài viết
            String noidung = "";
            Elements noidungbv = doc.select("p");
            for (int e = 3; e < noidungbv.size()-14; e++) {
                String ndText = noidungbv.get(e).text();
                noidung = noidung + ndText + " ";
            }
            this.setNoidung(noidung);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
