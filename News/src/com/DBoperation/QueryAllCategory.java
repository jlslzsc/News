package com.DBoperation;

import com.database.DBConnection;
import com.entity.category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryAllCategory {
    public List<category> queryAllCategory() {


        List<category> list = new ArrayList<category>();
        // 关联两张表查询
        String sql = "select * from category";

        try {
            Statement stm = DBConnection.getCon().createStatement();
            // 结果集
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                category category = new category();
                // 跨表列号相加
                category.setC_id(rs.getInt(1));
                category.setC_name(rs.getString(2));

                list.add(category);
            }
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
