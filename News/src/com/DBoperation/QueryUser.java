package com.DBoperation;
import java.sql.*;

import com.database.*;
import com.entity.user;

import java.util.ArrayList;
import java.util.List;
public class QueryUser {
    public List <user> queryUser(String loginname) {

        user user = new user();
        List<user> list = new ArrayList<>();

        // 关联两张表查询
        String sql = "select * from user where u_loginname ='" +loginname +"'";

        try {
            Statement stm = DBConnection.getCon().createStatement();
            // 结果集
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
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
