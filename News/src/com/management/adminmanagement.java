package com.management;

import com.admin.SerachCategory;
import com.admin.SerachUser;
import com.admin.SerachNew_admin;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.DBoperation.*;

public class adminmanagement extends JFrame implements ActionListener {

    JMenuBar menubar = new JMenuBar(); // 创建菜单栏对象
    // 一级菜单
    JMenu functionNewMenu = new JMenu("新闻功能");
    JMenu searchUserMenu = new JMenu("人员管理功能");
    JMenu otherMenu = new JMenu("其他");
    //二级菜单
    JMenuItem addNew = new JMenuItem("发布新闻");
    JMenuItem serachNew = new JMenuItem("搜索新闻");



    JMenuItem searchUser = new JMenuItem("查询人员");
    JMenuItem addCategory = new JMenuItem("添加类别");
    JMenuItem searchCategory = new JMenuItem("查询类别");

    JMenuItem quitMenu = new JMenuItem("退出");
    JLabel label = new JLabel();

    public adminmanagement() {
        this.setTitle("管理人员平台");
        this.setLayout(new CardLayout());
        this.setJMenuBar(menubar); // 将菜单栏组件添加到容器
        this.setResizable(false); // 设置窗口大小不可变
        // this.setUndecorated(true); //设置frame边框不可见
        // 一级菜单添加到菜单栏及事件监听
        menubar.add(functionNewMenu);
        menubar.add(searchUserMenu);
        menubar.add(otherMenu);
        functionNewMenu.addActionListener(this);
        searchUserMenu.addActionListener(this);
        otherMenu.addActionListener(this);

        // 二级菜单添加及事件监听
        functionNewMenu.add(addNew);
        functionNewMenu.add(serachNew);
        searchUserMenu.add(searchCategory);



        addNew.addActionListener(this);
        addCategory.addActionListener(this);


        searchUserMenu.add(searchUser);

        searchUserMenu.add(addCategory);
        searchUser.addActionListener(this);
        searchCategory.addActionListener(this);
        serachNew.addActionListener(this);

        otherMenu.add(quitMenu);
        quitMenu.addActionListener(this);

        label.setIcon(new ImageIcon("images/主界面.jpeg"));
        this.add(label);

        this.setVisible(true);
        this.setSize(580, 400); // 设置窗口的大小
        this.setLocationRelativeTo(null);// 窗体居中显示

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }
        });

    }

    @Override
    /**
     * 事件监听
     */
    public void actionPerformed(ActionEvent e) {


        // 搜索新闻
        if (e.getSource() == serachNew) {
            new SerachNew_admin();

        }

        // 添加类别
        if (e.getSource()== addCategory){
            new AddCategory();
        }

        //查询人员
        if (e.getSource() == searchUser) {
            new SerachUser();
        }
        //查询类别
        if (e.getSource() == searchCategory){
           new SerachCategory();
        }
        //添加新闻
        if (e.getSource()==addNew){
            new AddNews();
        }
         // 退出
        if (e.getSource() == quitMenu) {
            System.exit(0);
            // new UsingExit().setVisible(true);
        }
    }
}
