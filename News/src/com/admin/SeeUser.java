package com.admin;

import com.database.DBConnection;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SeeUser extends JFrame {
    public SeeUser(){

    }
    // 创建标签、文本框、密码框对象
    JLabel JLloginname = new JLabel("用户名：");
    JLabel JLpassword = new JLabel("密    码：");
    JLabel JLsex = new JLabel("性别");
    JLabel JLname = new JLabel("姓名");
    JLabel JLbirthday = new JLabel("生日");
    JLabel JLemail = new JLabel("邮箱");
    JTextField JTloginname = new JTextField();
    JTextField JTpassword = new JTextField();
    JTextField JTsex = new JTextField();
    JTextField JTname = new JTextField();
    JTextField JTbirthday = new JTextField();
    JTextField JTemail = new JTextField();
// 创建下拉列表框对象
    public SeeUser(int d) {
        this.setTitle("用户信息");
        this.setLayout(null);

        this.setResizable(false); // 禁止改变窗口大小
        // 用户名
        JLloginname.setBounds(100, 20, 60, 20);
        JTloginname.setBounds(200, 20, 100, 20);
        this.add(JLloginname);
        this.add(JTloginname);
        JTloginname.setEditable(false);
        // 密码
        JLpassword.setBounds(100, 40, 60, 20);
        JTpassword.setBounds(200, 40, 100, 20);
        this.add(JLpassword);
        this.add(JTpassword);
        JTpassword.setEditable(false);
        //性别
        JLsex.setBounds(100, 60, 60, 20);
        JTsex.setBounds(200, 60, 100, 20);
        this.add(JLsex);
        this.add(JTsex);
        JTsex.setEditable(false);
        //姓名
        JLname.setBounds(100, 80, 60, 20);
        JTname.setBounds(200, 80, 100, 20);
        this.add(JLname);
        this.add(JTname);
        JTname.setEditable(false);
        //生日
        JLbirthday.setBounds(100, 100, 60, 20);
        JTbirthday.setBounds(200, 100, 100, 20);
        this.add(JLbirthday);
        this.add(JTbirthday);
        JTbirthday.setEditable(false);
        //邮箱
        JLemail.setBounds(100, 120, 60, 20);
        JTemail.setBounds(200, 120, 100, 20);
        this.add(JLemail);
        this.add(JTemail);
        JTemail.setEditable(false);

        this.setVisible(true);
        this.setSize(400, 250); // 设置窗口的大小
        this.setLocationRelativeTo(null);// 窗体居中显示

        String sql1 = "select * from user where u_id =" + d;
        try {
            Statement stm = DBConnection.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql1);
            if (rs.next()) {
                JTloginname.setText(rs.getString(2));
                JTpassword.setText(rs.getString(3));
                JTname.setText(rs.getString(4));
                JTsex.setText(rs.getString(5));
                JTbirthday.setText(rs.getString(6));
                JTemail.setText(rs.getString(7));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
}