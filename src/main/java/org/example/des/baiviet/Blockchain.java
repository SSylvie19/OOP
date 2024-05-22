package org.example.des.baiviet;

//Cần import những thư viện này của Joup
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


//Tất cả các class con kế thừa class BaiViet đều có cấu trúc và cách hoạt động như sau:


// Blockchain kế thừa các thuộc tính và phương thức của BaiViet
public class Blockchain extends BaiViet {

    // Phương thức khởi tạo nhận vào 1 link url, thực hiện lấy hết thông tin từ bài viết ở đường link đó
    // và set cho các thuộc tính
    public Blockchain(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            //Link bài
            this.setLink(url);

            //Nguồn bài viết
            this.setNguon("https://blockchain.news");

            //Tiêu đề
            String td= doc.select("h1").text();
            this.setTieuDe(td);

            //Chuyên mục
            String cmuc = doc.select("a.badge.text-bg-danger.mb-2").text();
            this.setChuyenMuc(cmuc);

            //Tác giả
            String tg = doc.select("h4 > a").text();
            this.setTacGia(tg);

            //Thời gian
            String tGian = doc.select("ul.list-inline.list-unstyled li.list-inline-item.d-lg-block.my-lg-2").text();
            this.setThoigian(tGian);

            //Tags
            Elements tagsElement = doc.select("a.btn.btn-sm.btn-primary-soft");
            String tags1 = "";
            for (Element tag : tagsElement) {
                String tagsText = tag.text();
                tags1 = tags1 + tagsText + "   ";
            }
            this.setTags(tags1);


            //Nội dung bài viết
            String noidung;

            String noidung1 = doc.select("p.lead").text();
            noidung = noidung1 + " ";

            Elements noidung2 = doc.select("p > span");
            for (Element nd2 : noidung2) {
                String nd2Text = nd2.text();
                noidung = noidung + nd2Text + " ";
            }
            this.setNoidung(noidung);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}