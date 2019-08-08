package com.DBoperation;

import com.entity.category;
import com.database.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.entity.*;
public class AddNews extends JFrame implements ActionListener {
    JLabel JLtitle = new JLabel("标题:");
    JLabel JLcontent = new JLabel("内容:");
    JLabel JLcategory = new JLabel("类别:");
    JLabel JLkey = new JLabel("关键字:");
    JLabel JLadminname = new JLabel("发布者:");
    JTextField JTtitle = new JTextField();
    JTextArea JTcontent = new JTextArea(3,20);
    JTextField JTkey = new JTextField();
    JTextField JTadminname = new JTextField();
    // 创建按钮对象
    JButton release = new JButton("发布");
    JButton readd = new JButton("重置");
    JButton cancle = new JButton("取消");
    // 创建下拉列表框对象
    JComboBox<String> JC = new JComboBox<String>();
    JComboBox<String> JC1 = new JComboBox<String>();
    public AddNews(){
        this.setTitle("发布新闻");
        this.setLayout(null);
        String sql = "select * from category";
        String sql2 = "select a_name from admin where a_supid=1";
        //类别下拉框
        try {
            Statement stm = DBConnection.getCon().createStatement();
            // 结果集
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                category category = new category();
                // 跨表列号相加
                category.setC_name(rs.getString(2));
                JC.addItem(new String(category.getC_name()));

            }
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //发布者下拉框
        try {
            Statement stm = DBConnection.getCon().createStatement();
            // 结果集

            ResultSet rs1 = stm.executeQuery(sql2);
            while (rs1.next()) {
                admin admin = new admin();
                // 跨表列号相加
                admin.setA_name(rs1.getString(1));
                JC1.addItem(new String(admin.getA_name()));

            }

            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.setResizable(false); // 禁止改变窗口大小
        JTcontent.setLineWrap(true);        //激活自动换行功能
        JTcontent.setWrapStyleWord(true);            // 激活断行不断字功能
        JScrollPane js = new JScrollPane(JTcontent,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        // 标题
        JLtitle.setBounds(100, 20, 60, 20);
        JTtitle.setBounds(200, 20, 100, 20);
        this.add(JLtitle);
        this.add(JTtitle);

        // 内容
        JLcontent.setBounds(100, 40, 60, 90);
        js.setBounds(205, 40, 90, 85);
        this.add(JLcontent);
        this.add(js);
        //类别
        JLcategory.setBounds(100, 130, 60, 20);
        JC.setBounds(200, 130, 130, 20);
        this.add(JLcategory);
        this.add(JC);
        //关键字
        JLkey.setBounds(100, 150, 60, 20);
        JTkey.setBounds(200, 150, 100, 20);
        this.add(JLkey);
        this.add(JTkey);
        //发布者姓名
        JLadminname.setBounds(100, 170, 60, 20);
        JC1.setBounds(200, 170, 100, 20);
        this.add(JLadminname);
        this.add(JC1);

        release.setBounds(120, 200, 60, 20);
        cancle.setBounds(280, 200, 60, 20);
        readd.setBounds(200, 200, 60, 20);

        this.add(release);
        this.add(cancle);
        this.add(readd);
        this.setVisible(true);
        this.setSize(400, 250); // 设置窗口的大小
        this.setLocationRelativeTo(null);// 窗体居中显示
        release.addActionListener(this);
        cancle.addActionListener(this);
        readd.addActionListener(this);


    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() ==release){
            String title = JTtitle.getText();
            String content = JTcontent.getText();
            String category = (String)JC.getSelectedItem();
            String key = JTkey.getText();
            String adminname1 =(String)JC1.getSelectedItem();

            // 检索用户
            String sql = "select * from news where n_title = '" + title + "'";

            // 打开数据库连接并创建Statement对象
            try {
                Statement stm = DBConnection.getCon().createStatement();
                ResultSet rs = stm.executeQuery(sql);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "该标题已存在！", "提示信息", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    sql = "insert into news (n_title,n_content,n_category,n_clicknum,n_key,n_admin) values ('" + title + "','"
                            + content+ "','" + category + "',0,'" + key+ "','"+adminname1+"')";
                    int i = stm.executeUpdate(sql);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "添加成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                stm.close();
            }

            catch (SQLException e1){
                e1.printStackTrace();
            }

        }
        if (e.getSource() == readd){
            JTtitle.setText(null);
            JTcontent.setText(null);
            JTtitle.setText(null);
            JTkey.setText(null);


        }
        if (e.getSource() == cancle){
            setVisible(false);
        }
    }
}
