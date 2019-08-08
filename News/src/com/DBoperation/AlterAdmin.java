package com.DBoperation;
import com.database.DBConnection;
import com.admin.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterAdmin extends JFrame implements ActionListener {




    JLabel JLloginname = new JLabel("用户名：");
    JLabel JLpassword = new JLabel("密    码：");
    JLabel JLname = new JLabel("姓名");
    JTextField JTloginname = new JTextField();
    JTextField JTpassword = new JTextField();
    JTextField JTname = new JTextField();
    // 创建按钮对象
    JButton alter = new JButton("修改");
    JButton readd = new JButton("重置");
    JButton cancle = new JButton("取消");
    public AlterAdmin(int d){
        JFrame jf = new JFrame("修改管理员");
        //this.setTitle("修改管理员");
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
        //姓名
        JLname.setBounds(100, 80, 60, 20);
        JTname.setBounds(200, 80, 100, 20);
        jf.add(JLname);
        jf.add(JTname);


        alter.setBounds(120, 100, 60, 20);
        cancle.setBounds(280, 100, 60, 20);
        readd.setBounds(200, 100, 60, 20);
        jf.add(alter);
        jf.add(cancle);
        jf.add(readd);
        jf.setVisible(true);
        jf.setSize(400, 180); // 设置窗口的大小
        jf.setLocationRelativeTo(null);// 窗体居中显示
        alter.addActionListener(this);
        cancle.addActionListener(this);
        readd.addActionListener(this);
        jf.setVisible(true);
        String sql1 = "select * from admin where a_id  ="+d;
        try {
            Statement stm = DBConnection.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql1);
            if (rs.next()){
                JTloginname.setText(rs.getString(2));
                JTpassword.setText(rs.getString(3));
                JTname.setText(rs.getString(4));
            }
        }
        catch (SQLException e1){
            e1.printStackTrace();
        }
        alter.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String loginname = JTloginname.getText();
                String password = JTpassword.getText();
                String name = JTname.getText();
                // 打开数据库连接并创建Statement对象
                try {
                    Statement stm = DBConnection.getCon().createStatement();
                    String sql = "update  admin set a_loginname='" + loginname + "',a_password='" + password +
                            "',a_name='" + name + "'where a_id=" + d;

                    int i = stm.executeUpdate(sql);
                    if (i > 0) {

                        JOptionPane.showMessageDialog(null, "修改成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                        jf.setVisible(false);
                        new SeeAdminBySuper();


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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == readd){
            JTloginname.setText(null);
            JTname.setText(null);
            JTpassword.setText(null);
        }
        if (e.getSource() == cancle){
            this.dispose();
        }
    }
}
