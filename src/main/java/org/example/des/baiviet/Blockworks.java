package org.example.des.baiviet;

//Cần import những thư viện này của Joup
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Blockworks extends BaiViet {
    public Blockworks(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            //Link bài
            this.setLink(url);

            //Nguồn bài viết
            this.setNguon("https://blockworks.co");

            //Tiêu đề
            String td= doc.select("h1").text();
            this.setTieuDe(td);

            //Chuyên mục
            String cmuc = doc.select("li.flex.flex-row.flex-grow-0.flex-shrink-0.items-center").get(2).text();
            this.setChuyenMuc(cmuc);

            //Tác giả
            String tg = doc.select("a.link-gray").get(0).text();
            this.setTacGia(tg);

            //Thời gian
            String tGian = doc.select("div.flex.justify-start.items-start.relative.gap-1.uppercase").get(0).text();
            this.setThoigian(tGian);

            //Tags
            String tags = doc.select("ul.flex.flex-row.flex-wrap.gap-3.divide").text();
            this.setTags(tags);


            //Nội dung bài viết
            String noidung = "";
            Elements noidungbv = doc.select("p");
            for (int e = 0; e < noidungbv.size()-35; e++) {
                String ndText = noidungbv.get(e).text();
                noidung = noidung + ndText + " ";
            }
            this.setNoidung(noidung);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
