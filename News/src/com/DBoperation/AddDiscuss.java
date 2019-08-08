package com.DBoperation;

import com.database.DBConnection;
import com.user.SeeNewsByUser;
import com.entity.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddDiscuss extends JFrame  implements ActionListener {
    user user1 = new user();
    news news = new news();
    JLabel JLcontent= new JLabel("评论内容:");
    JLabel JLname = new JLabel("评论人:");

    JTextArea JTcontent = new JTextArea(3,20);
    JTextField JTname = new JTextField();
    JButton release = new JButton("发布");
    JButton readd = new JButton("重置");
    JButton cancle = new JButton("取消");
    public AddDiscuss(int d){
        this.setTitle("添加评论");
        this.setLayout(null);

        JLname.setText("姓名");
        this.setResizable(false); // 禁止改变窗口大小
        JTcontent.setLineWrap(true);        //激活自动换行功能
        JTcontent.setWrapStyleWord(true);            // 激活断行不断字功能
        JScrollPane js = new JScrollPane(JTcontent,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // 作者
        JLname.setBounds(100, 20, 60, 20);
        JTname.setBounds(200, 20, 100, 20);
        this.add(JLname);
        this.add(JTname);
        JTname.setEditable(false);
        // 内容
        JLcontent.setBounds(100, 40, 60, 90);
        js.setBounds(205, 40, 90, 135);
        this.add(JLcontent);
        this.add(js);
        release.setBounds(100, 200, 60, 20);
        cancle.setBounds(240, 200, 60, 20);
        readd.setBounds(170, 200, 60, 20);

        this.add(release);
        this.add(cancle);
        this.add(readd);
        this.setVisible(true);
        this.setSize(400, 250); // 设置窗口的大小
        this.setLocationRelativeTo(null);// 窗体居中显示
        release.addActionListener(this);
        cancle.addActionListener(this);
        readd.addActionListener(this);
        String sql1 = "select u_id,u_name from user where u_loginname ='"+user1.getU_loginname()+"'";
        try {
            Statement stm = DBConnection.getCon().createStatement();
            // 结果集
            ResultSet rs = stm.executeQuery(sql1);
            while (rs.next()) {
                user1.setU_id(rs.getInt(1));
                user1.setU_name(rs.getString(2));

            }
            JTname.setText(user1.getU_name());
            stm.close();
        }
        catch (SQLException e){
            e.printStackTrace();}

            release.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String sql2 = "select n_title from news where n_id = "+d;
                try {   Statement stm = DBConnection.getCon().createStatement();
                    ResultSet rs1 = stm.executeQuery(sql2);

                    if (rs1.next()){
                        news.setN_title(rs1.getString(1));
                    }

                    String content = JTcontent.getText();
                    String sql = "insert into discuss (d_newsid,d_content,d_userid,d_username,d_newtitle)" +
                            " values ("+d+",'"+content+"',"+user1.getU_id()+",'"+user1.getU_name()+"','"+news.getN_title()+
                            "')";
                    int i = stm.executeUpdate(sql);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "添加成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);

                        new SeeNewsByUser(d);
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                    }
                    stm.close();
                }
                catch (SQLException e){
                e.printStackTrace();}

            }
        });


        }       public void actionPerformed(ActionEvent e) {
        if (e.getSource() == readd){
            JTcontent.setText(null);
        }
        if (e.getSource() == cancle){
            setVisible(false);
        }
    }
    }




