package com.user;

import com.database.DBConnection;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.entity.*;
import java.sql.Statement;
public class AlterUserByUser extends JFrame implements ActionListener {
    user user = new user();
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
    JButton alter = new JButton("修改");
    JButton readd = new JButton("重置");
    JButton cancle = new JButton("取消");
    public AlterUserByUser(){
        this.setTitle("修改用户");
        this.setLayout(null);

        this.setResizable(false); // 禁止改变窗口大小
        // 用户名
        JLloginname.setBounds(100, 20, 60, 20);
        JTloginname.setBounds(200, 20, 100, 20);
        JTloginname.setText(user.getU_loginname());
        JTloginname.setEditable(false);
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



        alter.setBounds(120, 160, 60, 20);
        cancle.setBounds(280, 160, 60, 20);
        readd.setBounds(200, 160, 60, 20);
        this.add(alter);
        this.add(cancle);
        this.add(readd);
        this.setVisible(true);
        this.setSize(400, 230); // 设置窗口的大小
        this.setLocationRelativeTo(null);// 窗体居中显示
        alter.addActionListener(this);
        cancle.addActionListener(this);
        readd.addActionListener(this);

        String sql1 = "select * from user where u_loginname  ='"+user.getU_loginname()+"'";
        try {
            Statement stm = DBConnection.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql1);
            if (rs.next()){
                JTpassword.setText(rs.getString(3));
                JTsex.setText(rs.getString(5));
                JTname.setText(rs.getString(4));
                JTbirthday.setText(rs.getString(6));
                JTemail.setText(rs.getString(7));
            }
        }
        catch (SQLException e1){
            e1.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == alter) {
            String loginname = JTloginname.getText();
            String password = JTpassword.getText();
            String sex = JTsex.getText();
            String name = JTname.getText();
            String birthday = JTbirthday.getText();
            String email = JTemail.getText();
            try {
                Statement stm = DBConnection.getCon().createStatement();
                String sql = "update  user set u_password='"+ password + "',u_sex='" + sex + "',u_name='"
                        + name + "',u_birthday='" + birthday + "',u_email='" + email + "'where u_loginname = '"+loginname+"'";
                int i = stm.executeUpdate(sql);
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "修改成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                }
                stm.close();
            }


            catch (SQLException e1){
                e1.printStackTrace();
            }

        }
        if (e.getSource() == readd){

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

