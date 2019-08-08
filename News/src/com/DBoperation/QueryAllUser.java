package com.DBoperation;


import com.entity.user;
import com.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryAllUser {
    public List <user> QueryAllUser(String  n_key) {


        List<user> list = new ArrayList<user>();
        // 关联两张表查询
        String sql = "select * from user";

        try {
            Statement stm = DBConnection.getCon().createStatement();
            // 结果集
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                user user = new user();
                // 跨表列号相加
                user.setU_id(rs.getInt(1));
                user.setU_loginname(rs.getString(2));
                user.setU_password(rs.getString(3));
                user.setU_name(rs.getString(4));
                user.setU_sex(rs.getString(5));
                user.setU_birthday(rs.getString(6));
                user.setU_email(rs.getString(7));
                list.add(user);
            }
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
