package com.management;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.visitor.SerachNew_visitor;

public class visitormanagement extends JFrame implements ActionListener{
    JMenuBar menubar = new JMenuBar(); // 创建菜单栏对象
    // 一级菜单
    JMenu functionNewMenu = new JMenu("新闻功能");
    JMenu otherMenu = new JMenu("其他");
    //二级菜单

    JMenuItem serchNew = new JMenuItem("搜索新闻");


    JMenuItem quitMenu = new JMenuItem("退出");
    JLabel label = new JLabel();

    public visitormanagement() {
        this.setTitle("访客平台");
        this.setLayout(new CardLayout());
        this.setJMenuBar(menubar); // 将菜单栏组件添加到容器
        this.setResizable(false); // 设置窗口大小不可变
        // this.setUndecorated(true); //设置frame边框不可见
        // 一级菜单添加到菜单栏及事件监听
        menubar.add(functionNewMenu);

        menubar.add(otherMenu);
        functionNewMenu.addActionListener(this);

        otherMenu.addActionListener(this);

        // 二级菜单添加及事件监听

        functionNewMenu.add(serchNew);



        serchNew.addActionListener(this);

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
        if (e.getSource() == serchNew) {
            new SerachNew_visitor();

        }

        // 退出
        if (e.getSource() == quitMenu) {
            System.exit(0);

        }
    }
}
