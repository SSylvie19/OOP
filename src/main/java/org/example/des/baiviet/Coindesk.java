package org.example.des.baiviet;

//Cần import những thư viện này của Joup
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Coindesk extends BaiViet {
    public Coindesk(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            //Link bài
            this.setLink(url);

            //Nguồn bài viết
            this.setNguon("https://www.coindesk.com");

            //Tiêu đề
            String td= doc.select("h1.typography__StyledTypography-sc-owin6q-0.kbFhjp").text();
            this.setTieuDe(td);

            //Chuyên mục
            String cmuc = doc.select("span.typography__StyledTypography-sc-owin6q-0.kRnPCl").text();
            this.setChuyenMuc(cmuc);

            //Tác giả
            String tg = doc.select("div.at-authors").text();
            this.setTacGia(tg);

            //Thời gian
            String thoiGian = ""; int i=0;

            Elements time = doc.select("span.typography__StyledTypography-sc-owin6q-0.iOUkmj");

            for (Element nd : time) {
                if (i==24) {
                    String ndText = nd.text();
                    thoiGian = thoiGian + ndText;
                    ++i;
                }else{
                    ++i;
                };
            }
            this.setThoigian(thoiGian);

            //Tags
            String tags = " "; int a = 0;

            Elements tagsElement = doc.select("span.typography__StyledTypography-sc-owin6q-0.hirYAs");
            for (Element tag : tagsElement) {
                if (a>=5) {
                    String ndText = tag.text();
                    tags = tags + ndText + "   ";
                    ++a;
                }else{
                    ++a;
                };
            }
            this.setTags(tags);


            //Nội dung bài viết
            String noidung = "";

            Elements noidungbv = doc.select("div.common-textstyles__StyledWrapper-sc-18pd49k-0.eSbCkN");
            for (Element nd : noidungbv) {
                String ndText = nd.text();
                noidung = noidung + ndText + " ";
            }
            this.setNoidung(noidung);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
