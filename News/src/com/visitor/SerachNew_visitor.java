package com.visitor;

import com.DBoperation.AddClickNew;
import com.DBoperation.QueryAllNews;
import com.DBoperation.QueryNewsByCategory;
import com.DBoperation.QueryNewsByKey;
import com.database.DBConnection;
import com.entity.category;
import com.entity.news;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class SerachNew_visitor extends JFrame implements ActionListener {
    static int d;
    private Object[][] rowData;
    private Object[] column = { "编号","标题",  "类别", "日期", "点击量", "发布人" }; // 表头
    JLabel JLKey = new JLabel("新闻关键字");
    JTextField JTKey = new JTextField(16);
    JLabel JLcategory = new JLabel("新闻类别");
    JPanel epanel = new JPanel();
    JPanel wpanel = new JPanel();
    JPanel spanel = new JPanel();
    JComboBox<String> JC = new JComboBox<String>();
    JButton searchBtn = new JButton("查询新闻");
    JButton searchAllBtn = new JButton("查询全部");
    JButton JTcategory = new JButton("类别查询");
    JButton nextBtn = new JButton("重置");
    JButton cancelBtn = new JButton("取消");
    public SerachNew_visitor(){
        this.setTitle("查询新闻");
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
        JTcategory.addActionListener(this);
        spanel.add(JLcategory);
        spanel.add(JC);
        spanel.add(JTcategory);
        epanel.add(searchBtn);
        epanel.add(nextBtn);
        epanel.add(cancelBtn);

        this.add(epanel, BorderLayout.EAST);
        this.add(wpanel, BorderLayout.WEST);
        this.add(spanel,BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);// 窗体居中显示
        this.setVisible(true);
        /*每次打开复选框自动查询数据*/
        String sql = "select * from category";
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



    }
    public void setTable(String  key) {
        this.setVisible(false);
        JFrame jf = new JFrame("查询结果");
        JPanel spanel = new JPanel();
        jf.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        jf.setLayout(new BorderLayout());

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
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 15));
        // 表头名称字体颜色
        table.getTableHeader().setForeground(Color.RED);
        // 不允许手动改变列宽
        table.getTableHeader().setResizingAllowed(true);
        // 不允许拖动重新排列各列
        table.getTableHeader().setReorderingAllowed(false);
        // 设置行高30
        table.setRowHeight(30);
        // 列宽设置为100
        table.getColumnModel().getColumn(1).setPreferredWidth(100);

        // 设置滚动面板视口大小
        table.setPreferredScrollableViewportSize(new Dimension(550, 400));
        // 把表格放到滚动面板中（自动添加到顶部）
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        jf.add(panel);
        jf.add(panel,BorderLayout.NORTH);

        jf.add(spanel,BorderLayout.SOUTH);
        jf.pack();
        //jf.setContentPane(panel);
        jf.setVisible(true);

        //设置右键菜单

        JPopupMenu m_popupMenu = new JPopupMenu();

        JMenuItem searchMenItem = new JMenuItem();
        searchMenItem.setText("  查看  ");







        m_popupMenu.add(searchMenItem);




        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
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
            public void actionPerformed(ActionEvent evt) {
                int a = table.getSelectedRow();
                Object c = table.getValueAt(a,0);
                d = (int )c;
                new AddClickNew(d);
                new SeeNew_Visitor(d);
            }
        });

        //判断是否为鼠标的BUTTON3按钮，BUTTON3为鼠标右键





    }


    /**
     * 生成表格数据
     *
     * @return 表格数据二维数组
     */
    public Object[][] queryData(String key) {
        QueryAllNews queryAllnews = new QueryAllNews();
        QueryNewsByKey queryNewsByKey = new QueryNewsByKey();
        QueryNewsByCategory queryNewsByCategory = new QueryNewsByCategory();
        if (key.equals("all")) {
            List<news> list = queryAllnews.queryAllnews();
            rowData = new Object[list.size()][column.length];
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < column.length; j++) {
                    rowData[i][0] = list.get(i).getN_id();
                    rowData[i][1] = list.get(i).getN_title();
                    rowData[i][2] = list.get(i).getN_category();
                    rowData[i][3] = list.get(i).getN_date();
                    rowData[i][4] = list.get(i).getN_clicknum();
                    rowData[i][5] = list.get(i).getN_admin();
                }
            }
            return rowData;
        }
        if (key.equals("category")) {
            String box = (String) JC.getSelectedItem();
            List<news> list = queryNewsByCategory.queryNewsByKey(box);
            rowData = new Object[list.size()][column.length];
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < column.length; j++) {
                    rowData[i][0] = list.get(i).getN_id();
                    rowData[i][1] = list.get(i).getN_title();
                    rowData[i][2] = list.get(i).getN_category();
                    rowData[i][3] = list.get(i).getN_date();
                    rowData[i][4] = list.get(i).getN_clicknum();
                    rowData[i][5] = list.get(i).getN_admin();
                }
            }
            return rowData;
        }
        if (!(queryNewsByKey.queryNewsByKey(key) == null)) {
            List<news> list = queryNewsByKey.queryNewsByKey(key);
            rowData = new Object[list.size()][column.length];
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < column.length; j++) {
                    rowData[i][0] = list.get(i).getN_id();
                    rowData[i][1] = list.get(i).getN_title();
                    rowData[i][2] = list.get(i).getN_category();
                    rowData[i][3] = list.get(i).getN_date();
                    rowData[i][4] = list.get(i).getN_clicknum();
                    rowData[i][5] = list.get(i).getN_admin();
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
        if (e.getSource() ==JTcategory){
            String key2="category";
            setTable(key2);
        }


    }
}
