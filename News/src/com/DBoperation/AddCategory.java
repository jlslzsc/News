package com.DBoperation;

import com.database.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddCategory extends JFrame implements ActionListener {
    JLabel JLtitle = new JLabel("类别:");
    JTextField JTtitle = new JTextField();
    JButton release = new JButton("添加");
    JButton readd = new JButton("重置");
    JButton cancle = new JButton("取消");
    public AddCategory()

    {
        this.setTitle("添加类别");
        this.setLayout(null);
        this.setResizable(false); // 禁止改变窗口大小
        // 用户名
        JLtitle.setBounds(100, 20, 60, 20);
        JTtitle.setBounds(200, 20, 100, 20);
        this.add(JLtitle);
        this.add(JTtitle);
        release.setBounds(150, 50, 60, 20);
        cancle.setBounds(250, 50, 60, 20);
        readd.setBounds(200, 50, 60, 20);
        this.add(release);
        this.add(cancle);
        this.add(readd);
        this.setVisible(true);
        this.setSize(400, 120); // 设置窗口的大小
        this.setLocationRelativeTo(null);// 窗体居中显示
        release.addActionListener(this);
        cancle.addActionListener(this);
        readd.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == release) {
            String loginname = JTtitle.getText();

            // 检索用户
            String sql = "select * from category where c_name = '" + loginname + "'";
            // 打开数据库连接并创建Statement对象
            try {
                Statement stm = DBConnection.getCon().createStatement();
                ResultSet rs = stm.executeQuery(sql);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "该类别已存在！", "提示信息", JOptionPane.WARNING_MESSAGE);
                } else {
                    sql = "INSERT INTO category(c_name) VALUES ('" + loginname + "')";
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
            JTtitle.setText(null);


        }
        if (e.getSource() == cancle){
            setVisible(false);
        }
    }

}
