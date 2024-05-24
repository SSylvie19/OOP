package org.example.des.database;

import org.example.des.baiviet.BaiViet;

public interface Database {

    public void saveToDatabase(BaiViet baiViet);

    public boolean checkInDatabase(String url);

    public int checkSource(String url);
}
