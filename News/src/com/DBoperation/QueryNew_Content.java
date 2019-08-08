package com.DBoperation;

import com.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryNew_Content {
    public String querynew_content(int d){

        String sql = "select n_content from news where n_id ="+d;
        String content = null;
        try {

            Connection conn = DBConnection.getCon();
            // 获取结果集
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {content = rs.getString(1);}



            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return content;

    }

}
