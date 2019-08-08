package com.admin;

import com.database.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterUserByAdmin extends JFrame implements ActionListener {
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
    JButton login = new JButton("修改");
    JButton readd = new JButton("重置");
    JButton cancle = new JButton("取消");

    // 创建下拉列表框对象
    public AlterUserByAdmin(int d) {
        JFrame jf=new JFrame("修改用户");
        jf.setLayout(null);

        jf.setResizable(false); // 禁止改变窗口大小
        // 用户名
        JLloginname.setBounds(100, 20, 60, 20);
        JTloginname.setBounds(200, 20, 100, 20);
        jf.add(JLloginname);
        jf.add(JTloginname);

        // 密码
        JLpassword.setBounds(100, 40, 60, 20);
        JTpassword.setBounds(200, 40, 100, 20);
        jf.add(JLpassword);
        jf.add(JTpassword);
        //性别
        JLsex.setBounds(100, 60, 60, 20);
        JTsex.setBounds(200, 60, 100, 20);
        jf.add(JLsex);
        jf.add(JTsex);
        //姓名
        JLname.setBounds(100, 80, 60, 20);
        JTname.setBounds(200, 80, 100, 20);
        jf.add(JLname);
        jf.add(JTname);
        //生日
        JLbirthday.setBounds(100, 100, 60, 20);
        JTbirthday.setBounds(200, 100, 100, 20);
        jf.add(JLbirthday);
        jf.add(JTbirthday);
        //邮箱
        JLemail.setBounds(100, 120, 60, 20);
        JTemail.setBounds(200, 120, 100, 20);
        jf.add(JLemail);
        jf.add(JTemail);
        //修改
        login.setBounds(150, 200, 60, 20);
        cancle.setBounds(250, 200, 60, 20);
        readd.setBounds(200, 200, 60, 20);
        jf.add(login);
        jf.add(cancle);
        jf.add(readd);
        jf.setVisible(true);
        jf.setSize(400, 250); // 设置窗口的大小
        jf.setLocationRelativeTo(null);// 窗体居中显示
        login.addActionListener(this);
        cancle.addActionListener(this);
        readd.addActionListener(this);
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
        login.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String loginname = JTloginname.getText();
                String password = JTpassword.getText();
                String name = JTname.getText();
                String biethday = JTbirthday.getText();
                String email = JTemail.getText();
                // 打开数据库连接并创建Statement对象
                try {
                    Statement stm = DBConnection.getCon().createStatement();
                    String sql = "update  user set u_loginname='" + loginname + "',u_password='" + password + "',u_name='" + name
                            + "',u_birthday='" + biethday + "',u_email='" + email + "'where u_id="+ d;
                    int i = stm.executeUpdate(sql);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "修改成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                        jf.setVisible(false);
                        new SerachUser();
                   } else {
                        JOptionPane.showMessageDialog(null, "修改失败！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                    }
                    stm.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }
        public void actionPerformed (ActionEvent e){

            if (e.getSource() == readd) {
                JTloginname.setText(null);
                JTpassword.setText(null);
                JTname.setText(null);
                JTbirthday.setText(null);
                JTemail.setText(null);
            }
            if (e.getSource() == cancle) {
                setVisible(false);
            }
        }


}
