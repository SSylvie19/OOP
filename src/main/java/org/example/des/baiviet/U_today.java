package org.example.des.baiviet;

//Cần import những thư viện này của Joup
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class U_today extends BaiViet {
    public U_today(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            //Link bài
            this.setLink(url);

            //Nguồn bài viết
            this.setNguon("https://u.today");

            //Tiêu đề
            String td= doc.select("h1").text();
            this.setTieuDe(td);

            //Chuyên mục
            String cmuc = doc.select("li.breadcrumbs__item").get(2).text();
            this.setChuyenMuc(cmuc);

            //Tác giả
            String tg = doc.select("div.article__author-name").get(0).text();
            this.setTacGia(tg);

            //Thời gian
            String tGian = doc.select("div.humble.article__short-humble").get(1).text();
            this.setThoigian(tGian);

            //Tags
            this.setTags("Không có");


            //Nội dung bài viết
            String noidung = doc.select("div.article__author-desc").getFirst().text() + " ";
            Elements noidungbv = doc.select("p");
            for (int e = 0; e < noidungbv.size()-2; e++) {
                String ndText = noidungbv.get(e).text();
                noidung = noidung + ndText + " ";
            }
            this.setNoidung(noidung);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
