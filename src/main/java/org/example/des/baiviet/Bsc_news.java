package org.example.des.baiviet;

//Cần import những thư viện này của Joup
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Bsc_news extends BaiViet {
    public Bsc_news(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            //Link bài
            this.setLink(url);

            //Nguồn bài viết
            this.setNguon("https://www.bsc.news");

            //Tiêu đề
            String td= doc.select("h1").text();
            this.setTieuDe(td);

            //Chuyên mục
            String cmuc = doc.select("p.text-white.py-2.px-2.text-sm.font-medium.bg-violet-500.rounded-tl-xl.rounded-tr-lg.rounded-bl-lg.rounded-br-xl.w-fit").text()
                    + doc.select("p.text-white.py-2.px-2.text-sm.font-medium.bg-background-news-card-hightlighted.rounded-tl-xl.rounded-tr-lg.rounded-bl-lg.rounded-br-xl.w-fit").text();
            this.setChuyenMuc(cmuc);

            //Tác giả
            String tg = doc.select("p.text-sm.font-normal.ml-2").get(0).text();
            this.setTacGia(tg);

            //Thời gian
            String tGian = doc.select("p.text-sm.font-normal.ml-2").get(1).text();
            this.setThoigian(tGian);

            //Tags
            this.setTags("Không có");


            //Nội dung bài viết
            String noidung = "";
            Elements noidungbv = doc.select("p");
            for (int e = 32; e < noidungbv.size()-102; e++) {
                String ndText = noidungbv.get(e).text();
                noidung = noidung + ndText + " ";
            }
            this.setNoidung(noidung);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
