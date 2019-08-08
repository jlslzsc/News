package com.DBoperation;

import com.database.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.user.*;
public class AlterDiscussByUser extends JFrame implements ActionListener {
    JLabel JLcontent = new JLabel("内容:");
    JTextArea JTcontent = new JTextArea(3,20);
    JButton release = new JButton("修改");
    JButton readd = new JButton("重置");
    JButton cancle = new JButton("取消");
    public AlterDiscussByUser(int d){
        JFrame jf = new JFrame("修改内容");
        jf.setLayout(null);
        JLcontent.setBounds(100, 20, 60, 20);
        JTcontent.setLineWrap(true);        //激活自动换行功能
        JTcontent.setWrapStyleWord(true);            // 激活断行不断字功能
        JScrollPane js = new JScrollPane(JTcontent,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setBounds(205, 20, 120, 205);
        jf.add(JLcontent);
        jf.add(js);
        jf.setSize(400, 320); // 设置窗口的大小
        jf.setLocationRelativeTo(null);// 窗体居中显示
        release.setBounds(120, 250, 60, 20);
        cancle.setBounds(280, 250, 60, 20);
        readd.setBounds(200, 250, 60, 20);
        jf.add(release);
        jf.add(cancle);
        jf.add(readd);
        release.addActionListener(this);
        cancle.addActionListener(this);
        readd.addActionListener(this);
        jf.setVisible(true);
        String sql1 = "select d_content from discuss where d_id =" + d;
        try {
            Statement stm = DBConnection.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql1);
            if (rs.next()) {
                JTcontent.setText(rs.getString(1));

            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        release.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SerchDiscuss serchDiscuss = new SerchDiscuss();
                String content = JTcontent.getText();
                // 打开数据库连接并创建Statement对象
                try {
                    Statement stm = DBConnection.getCon().createStatement();
                    String sql = "update  discuss set d_content='" + content + "'where d_id="+ d;

                    int i = stm.executeUpdate(sql);
                    if (i > 0) {

                        JOptionPane.showMessageDialog(null, "修改成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                        jf.setVisible(false);
                        serchDiscuss.serchDiscuss();
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
            JTcontent.setText(null);



        }
        if (e.getSource() == cancle) {
            setVisible(false);
        }
    }

}
