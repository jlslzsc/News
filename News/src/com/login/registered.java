package com.login;

import javax.swing.*;
import  java.awt.event.*;
import  java.sql.*;
import com.database.*;

public class registered extends JFrame implements ActionListener {
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

    // 创建按钮对象
    JButton login = new JButton("注册");
    JButton readd = new JButton("重置");
    JButton cancle = new JButton("取消");
    // 创建下拉列表框对象


    public registered() {
        this.setTitle("注册");
        this.setLayout(null);

        this.setResizable(false); // 禁止改变窗口大小
        // 用户名
        JLloginname.setBounds(100, 20, 60, 20);
        JTloginname.setBounds(200, 20, 100, 20);
        this.add(JLloginname);
        this.add(JTloginname);

        // 密码
        JLpassword.setBounds(100, 40, 60, 20);
        JTpassword.setBounds(200, 40, 100, 20);
        this.add(JLpassword);
        this.add(JTpassword);
        //性别
        JLsex.setBounds(100, 60, 60, 20);
        JTsex.setBounds(200, 60, 100, 20);
        this.add(JLsex);
        this.add(JTsex);
        //姓名
        JLname.setBounds(100, 80, 60, 20);
        JTname.setBounds(200, 80, 100, 20);
        this.add(JLname);
        this.add(JTname);
        //生日
        JLbirthday.setBounds(100, 100, 60, 20);
        JTbirthday.setBounds(200, 100, 100, 20);
        this.add(JLbirthday);
        this.add(JTbirthday);
        //邮箱
        JLemail.setBounds(100, 120, 60, 20);
        JTemail.setBounds(200, 120, 100, 20);
        this.add(JLemail);
        this.add(JTemail);



        login.setBounds(120, 180, 60, 20);
        cancle.setBounds(280, 180, 60, 20);
        readd.setBounds(200, 180, 60, 20);
        this.add(login);
        this.add(cancle);
        this.add(readd);
        this.setVisible(true);
        this.setSize(380, 250); // 设置窗口的大小
        this.setLocationRelativeTo(null);// 窗体居中显示
        login.addActionListener(this);
        cancle.addActionListener(this);
        readd.addActionListener(this);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String loginname = JTloginname.getText();
            String password = JTpassword.getText();
            String sex = JTsex.getText();
            String name = JTname.getText();
            String birthday = JTbirthday.getText();
            String email = JTemail.getText();

            // 检索用户
            String sql = "select * from user where u_loginname = '" + loginname + "'";
            // 打开数据库连接并创建Statement对象
            try {
                Statement stm = DBConnection.getCon().createStatement();
                ResultSet rs = stm.executeQuery(sql);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "该账号已存在！", "提示信息", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    sql = "insert into user (u_loginname,u_password,u_sex,u_name,u_birthday,u_email)values ('" + loginname + "','"
                            + password+ "','" + sex + "','" + name + "','" + birthday+ "','" + email + "')";
                    int i = stm.executeUpdate(sql);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "添加成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                    }
                    this.setVisible(false);
                }
                stm.close();
            }

            catch (SQLException e1){
                e1.printStackTrace();
            }


        }
        if (e.getSource() == readd){
            JTloginname.setText(null);
            JTpassword.setText(null);
            JTname.setText(null);
            JTsex.setText(null);
            JTbirthday.setText(null);
            JTemail.setText(null);

        }
        if (e.getSource() == cancle){
            setVisible(false);
        }
    }
}
