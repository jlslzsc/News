package com.admin;

import com.DBoperation.DeleteUser;
import com.DBoperation.QueryAllUser;
import com.DBoperation.QueryUser;
import com.entity.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SerachUser extends JFrame implements ActionListener {
    private Object[][] rowData;
    private Object[] column = { "id","用户名", "密码", "性名", "姓别", "生日", "邮箱" }; // 表头
    JLabel JLKey = new JLabel("用户名");
    JTextField JTKey = new JTextField(16);

    JPanel epanel = new JPanel();
    JPanel wpanel = new JPanel();

    JButton searchBtn = new JButton("查询用户");
    JButton searchAllBtn = new JButton("查询全部");
    JButton nextBtn = new JButton("重置");
    JButton cancelBtn = new JButton("取消");
    public SerachUser(){
        this.setTitle("查询用户");
        this.setLayout(new BorderLayout());
        this.setLocation(400, 400);
        // 标签和输入框
        wpanel.add(searchAllBtn);
        wpanel.add(JLKey);
        wpanel.add(JTKey);
        // 三个按钮添加事件
        searchAllBtn.addActionListener(this);
        searchBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        epanel.add(searchBtn);
        epanel.add(nextBtn);
        epanel.add(cancelBtn);

        this.add(epanel, BorderLayout.EAST);
        this.add(wpanel, BorderLayout.WEST);

        this.pack();
        this.setLocationRelativeTo(null);// 窗体居中显示
        this.setVisible(true);
    }

    public void setTable(String  key) {

        this.setVisible(false);

        JFrame jf = new JFrame("查询结果");
        jf.setLocationRelativeTo(null);
        JPanel panel = new JPanel();

        // 表格所有行
        Object[][] rowData = queryData(key);

        JTable table = new JTable(rowData, column){
            public boolean isCellEditable(int row, int column) {return false;};

        };


        // 设置表格内容颜色
        table.setForeground(Color.BLACK); // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14)); // 字体样式
        table.setSelectionBackground(Color.DARK_GRAY); // 选中后字体背景
        table.setSelectionForeground(Color.LIGHT_GRAY); // 选中后字体颜色
        table.setGridColor(Color.GRAY); // 网格颜色

        // 设置表头
        // 设置表头字体样式
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        // 表头名称字体颜色
        table.getTableHeader().setForeground(Color.RED);
        // 不允许手动改变列宽
        table.getTableHeader().setResizingAllowed(false);
        // 不允许拖动重新排列各列
        table.getTableHeader().setReorderingAllowed(false);

        // 设置行高30
        table.setRowHeight(30);
        // 列宽设置为100
        table.getColumnModel().getColumn(1).setPreferredWidth(80);

        // 设置滚动面板视口大小
        table.setPreferredScrollableViewportSize(new Dimension(550, 400));
        // 把表格放到滚动面板中（自动添加到顶部）
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        jf.add(panel);

        jf.pack();
        jf.setContentPane(panel);
        jf.setVisible(true);
        //设置右键菜单

        JPopupMenu m_popupMenu = new JPopupMenu();

        JMenuItem searchMenItem = new JMenuItem();
        searchMenItem.setText("  查看  ");
        JMenuItem alterMenItem = new JMenuItem();
        alterMenItem.setText("  修改  ");
        JMenuItem deleteMenItem = new JMenuItem();
        deleteMenItem.setText("  删除  ");





        m_popupMenu.add(alterMenItem);
        m_popupMenu.add(deleteMenItem);
        m_popupMenu.add(searchMenItem);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    //通过点击位置找到点击为表格中的行
                    int focusedRowIndex = table.rowAtPoint(e.getPoint());
                    if (focusedRowIndex == -1) {
                        return;
                    }
                    //将表格所选项设为当前右键点击的行
                    table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    //弹出菜单
                    m_popupMenu.show(table, e.getX(), e.getY());






                }
            }
        });
        searchMenItem.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int a = table.getSelectedRow();
                Object c = table.getValueAt(a,0);
                int d = (int )c;
                new SeeUser(d);
            }
        });
        alterMenItem.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //该操作需要做的事
                int a = table.getSelectedRow();
                Object c = table.getValueAt(a,0);
                int d = (int )c;
                jf.setVisible(false);
                new AlterUserByAdmin(d);

            }
        });
        deleteMenItem.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int a = table.getSelectedRow();
                Object c = table.getValueAt(a,0);
                int d = (int )c;
                new DeleteUser(d);
                jf.setVisible(false);
                setTable("all");
            }
        });
    }

    /**
     * 生成表格数据
     *
     * @return 表格数据二维数组
     */
    public Object[][] queryData(String key) {
        QueryUser queryUser = new QueryUser();
        QueryAllUser queryAllUser = new QueryAllUser();
        if (key.equals("all")) {
            List<user> list = queryAllUser.QueryAllUser(key);

            rowData = new Object[list.size()][column.length];
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < column.length; j++) {
                    rowData[i][0] = list.get(i).getU_id();
                    rowData[i][1] = list.get(i).getU_loginname();
                    rowData[i][2] = list.get(i).getU_password();
                    rowData[i][3] = list.get(i).getU_name();
                    rowData[i][4] = list.get(i).getU_sex();
                    rowData[i][5] = list.get(i).getU_birthday();
                    rowData[i][6] = list.get(i).getU_email();
                }
            }
            return rowData;
        }
        if (!(queryUser.queryUser(key) == null)) {
            List<user> list = queryUser.queryUser(key);
            rowData = new Object[list.size()][column.length];
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < column.length; j++) {
                    rowData[i][0] = list.get(i).getU_id();
                    rowData[i][1] = list.get(i).getU_loginname();
                    rowData[i][2] = list.get(i).getU_password();
                    rowData[i][3] = list.get(i).getU_name();
                    rowData[i][4] = list.get(i).getU_sex();
                    rowData[i][5] = list.get(i).getU_birthday();
                    rowData[i][6] = list.get(i).getU_email();
                }
            }



        }
        return rowData;


    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchBtn){
            String key = null;
            key = JTKey.getText();
            if (!key.isEmpty()){
                setTable(key);

            }
            else {
                JOptionPane.showMessageDialog(null, "输入数据错误！");
            }

        }
        if (e.getSource() == searchAllBtn){

            String key1="all";
            setTable(key1);
        }
        if (e.getSource() == nextBtn){
            JLKey.setText(null);
        }
        if (e.getSource() == cancelBtn){
            this.setVisible(false);
        }

    }

}
