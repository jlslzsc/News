package com.DBoperation;

import com.database.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.admin.*;
public class AlterCategory extends JFrame implements ActionListener {
    JLabel JLtitle = new JLabel("类别:");
    JTextField JTtitle = new JTextField();
    JButton release = new JButton("修改");
    JButton readd = new JButton("重置");
    JButton cancle = new JButton("取消");

    public AlterCategory(int d){
        JFrame jf = new JFrame("修改类别");

        jf.setLayout(null);
        // 类别
        JLtitle.setBounds(100, 20, 60, 20);
        JTtitle.setBounds(200, 20, 100, 20);
        jf.add(JLtitle);
        jf.add(JTtitle);
        jf.setSize(400, 120); // 设置窗口的大小
        jf.setLocationRelativeTo(null);// 窗体居中显示
        release.setBounds(120, 50, 60, 20);
        cancle.setBounds(280, 50, 60, 20);
        readd.setBounds(200, 50, 60, 20);
        jf.add(release);
        jf.add(cancle);
        jf.add(readd);
        release.addActionListener(this);
        cancle.addActionListener(this);
        readd.addActionListener(this);
        jf.setVisible(true);
        String sql1 = "select * from category where c_id =" + d;//通过之前获得的id查询类别
        try {
            Statement stm = DBConnection.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql1);
            if (rs.next()) {
                JTtitle.setText(rs.getString(2));//得到内容

            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        release.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String loginname = JTtitle.getText();
                // 打开数据库连接并创建Statement对象
                try {
                    Statement stm = DBConnection.getCon().createStatement();
                    String sql = "update  category set c_name='" + loginname + "'where c_id="+ d;

                    int i = stm.executeUpdate(sql);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "修改成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                        jf.setVisible(false);
                        new SerachCategory();
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
            JTtitle.setText(null);
        }
        if (e.getSource() == cancle) {
            setVisible(false);
        }
    }

}
