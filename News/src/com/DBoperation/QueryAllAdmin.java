package com.DBoperation;

import com.database.DBConnection;
import com.entity.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryAllAdmin {
    public List<admin> queryAllAdmin() {


        List<admin> list = new ArrayList<admin>();
        // 关联两张表查询
        String sql = "select * from admin";

        try {
            Statement stm = DBConnection.getCon().createStatement();
            // 结果集
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                admin admin = new admin();
                // 跨表列号相加
                admin.setA_id(rs.getInt(1));
                admin.setA_loginname(rs.getString(2));
                admin.setA_password(rs.getString(3));
                admin.setA_name(rs.getString(4));
                list.add(admin);
            }
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
