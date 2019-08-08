package com.DBoperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.database.DBConnection;
import com.entity.news;

public class QueryNewsByCategory {
    public List<news> queryNewsByKey(String key) {

        String sql = "select * from news where n_category= '"+key +"'";

        List<news> list = new ArrayList<news>();
        try {
            Statement stm = DBConnection.getCon().createStatement();
            // 结果集
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                news news = new news();
                // 跨表列号相加
                news.setN_id(rs.getInt(1));
                news.setN_title(rs.getString(2));
                news.setN_content(rs.getString(3));
                news.setN_category(rs.getString(4));
                news.setN_date(rs.getString(5));
                news.setN_clicknum(rs.getString(6));
                news.setN_admin(rs.getString(8));
                list.add(news);

            }

            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
