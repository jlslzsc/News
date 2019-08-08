package com.management;

import com.DBoperation.*;
import com.admin.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class superAdminManagement extends JFrame implements ActionListener {
    JMenuBar menubar = new JMenuBar(); // 创建菜单栏对象
    // 一级菜单
    JMenu functionadminMenu = new JMenu("管理员人员功能");
    JMenu otherMenu = new JMenu("其他");
    //二级菜单

    JMenuItem serchAdmin = new JMenuItem("查看管理员");
    JMenuItem addAdmin = new JMenuItem("添加管理员");

    JMenuItem quitMenu = new JMenuItem("退出");
    JLabel label = new JLabel();

    public superAdminManagement() {
        this.setTitle("超级管理员平台");
        this.setLayout(new CardLayout());
        this.setJMenuBar(menubar); // 将菜单栏组件添加到容器
        this.setResizable(false); // 设置窗口大小不可变
        // this.setUndecorated(true); //设置frame边框不可见
        // 一级菜单添加到菜单栏及事件监听
        menubar.add(functionadminMenu);

        menubar.add(otherMenu);
        functionadminMenu.addActionListener(this);

        otherMenu.addActionListener(this);

        // 二级菜单添加及事件监听

        functionadminMenu.add(serchAdmin);
        functionadminMenu.add(addAdmin);
        addAdmin.addActionListener(this);

        serchAdmin.addActionListener(this);

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


        // 搜索人员
        if (e.getSource() == serchAdmin) {
          new SeeAdminBySuper();

        }
        //添加人员
        if (e.getSource() == addAdmin) {
           new AddAdmin();

        }

        // 退出
        if (e.getSource() == quitMenu) {
            System.exit(0);

        }
    }
}
