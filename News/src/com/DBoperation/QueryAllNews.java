package com.DBoperation;

import com.entity.news;
import com.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * 查询所有新闻数据
 *
 * @return 查找到的数据生成的list集合
 */
public class QueryAllNews {

    public List<news> queryAllnews() {

        String sql = "select * from news ";
        List<news> list = new ArrayList<news>();
        try {
            Connection conn = DBConnection.getCon();
            // 获取结果集
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

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
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


}
