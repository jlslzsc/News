package com.DBoperation;

import com.database.DBConnection;
import com.entity.discuss;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryDiscussByUserName {
    public List<discuss> queryDiscussByUserid(String d_username) {
        java.util.List<com.entity.discuss> list = new ArrayList<com.entity.discuss>();
        // 关联两张表查询
        String sql = "select * from discuss where d_username='"+d_username+"'";

        try {
            Statement stm = DBConnection.getCon().createStatement();
            // 结果集
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                com.entity.discuss discuss = new com.entity.discuss();
                // 跨表列号相加
                discuss.setD_id(rs.getInt(1));
                discuss.setD_newsid(rs.getInt(2));
                discuss.setD_userid(rs.getInt(3));
                discuss.setD_content(rs.getString(4));
                discuss.setD_date(rs.getString(5));
                discuss.setD_username(rs.getString(6));
                discuss.setD_title(rs.getString(7));
                list.add(discuss);
            }
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
