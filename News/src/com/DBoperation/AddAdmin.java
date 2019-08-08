package com.DBoperation;

import com.database.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddAdmin  extends JFrame implements ActionListener {
    JLabel JLloginname = new JLabel("用户名：");
    JLabel JLpassword = new JLabel("密    码：");
    JLabel JLname = new JLabel("姓名");
    JTextField JTloginname = new JTextField();
    JTextField JTpassword = new JTextField();
    JTextField JTname = new JTextField();
    // 创建按钮对象
    JButton add = new JButton("添加");
    JButton readd = new JButton("重置");
    JButton cancle = new JButton("取消");
    public AddAdmin(){
        this.setTitle("添加管理员");
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
        //姓名
        JLname.setBounds(100, 60, 60, 20);
        JTname.setBounds(200, 60, 100, 20);
        this.add(JLname);
        this.add(JTname);


        add.setBounds(150, 100, 60, 20);
        cancle.setBounds(250, 100, 60, 20);
        readd.setBounds(200, 100, 60, 20);
        this.add(add);
        this.add(cancle);
        this.add(readd);
        this.setVisible(true);
        this.setSize(400, 180); // 设置窗口的大小
        this.setLocationRelativeTo(null);// 窗体居中显示
        add.addActionListener(this);
        cancle.addActionListener(this);
        readd.addActionListener(this);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add){
            String loginname = JTloginname.getText();
            String password = JTpassword.getText();
            String name = JTname.getText();


            String sql = "select * from admin where a_loginname = '" + loginname + "'";
            // 打开数据库连接并创建Statement对象
            try {
                Statement stm = DBConnection.getCon().createStatement();
                ResultSet rs = stm.executeQuery(sql);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "该用户已存在！", "提示信息", JOptionPane.WARNING_MESSAGE);
                } else {
                    sql = "INSERT INTO admin(a_loginname,a_password,a_name,a_supid) VALUES ('" + loginname +
                            "','"+password+"','"+name+"',1)";
                    int i = stm.executeUpdate(sql);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "添加成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                stm.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == readd){
            JTloginname.setText(null);
            JTname.setText(null);
            JTpassword.setText(null);
        }
        if (e.getSource() == cancle){
            setVisible(false);
        }
    }
}
