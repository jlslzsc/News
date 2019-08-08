package com.DBoperation;

import com.entity.news;
import com.database.DBConnection;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryNewsByKey {

        public List<news> queryNewsByKey(String key) {

            String sql = "select * from news where n_title like '%"+key +"%'";

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
                    news.setN_key(rs.getString(7));
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

