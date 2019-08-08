package com.user;

import com.DBoperation.*;
import com.database.DBConnection;
import com.entity.discuss;
import com.entity.user;

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

public class SerchDiscuss extends JFrame {
    user user = new user();
    public Object[][] rowData1;
    public Object[] column1 = {"id","标题","评论内容", "评论时间"}; // 表头
    public void serchDiscuss() {
        String sql1 = "select u_name from user where u_loginname ='"+user.getU_loginname()+"'";
        try {
            Statement stm = DBConnection.getCon().createStatement();
            // 结果集
            ResultSet rs = stm.executeQuery(sql1);
            while (rs.next()) {

                user.setU_name(rs.getString(1));

            }

            stm.close();
        }
        catch (SQLException e){
            e.printStackTrace();}


        Object[][] rowData1 = queryData2(user.getU_name());
            this.setVisible(false);

            JFrame jf = new JFrame("查询结果");
            jf.setLocationRelativeTo(null);
            JPanel panel = new JPanel();
        JTable table1 = new JTable(rowData1, column1) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            ;

        };
        // 设置表格内容颜色
        table1.setForeground(Color.BLACK); // 字体颜色
        table1.setFont(new Font(null, Font.PLAIN, 14)); // 字体样式
        table1.setSelectionBackground(Color.DARK_GRAY); // 选中后字体背景
        table1.setSelectionForeground(Color.LIGHT_GRAY); // 选中后字体颜色
        table1.setGridColor(Color.GRAY); // 网格颜色

        // 设置表头
        // 设置表头字体样式
        table1.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        // 表头名称字体颜色
        table1.getTableHeader().setForeground(Color.RED);
        // 不允许手动改变列宽
        table1.getTableHeader().setResizingAllowed(false);
        // 不允许拖动重新排列各列
        table1.getTableHeader().setReorderingAllowed(false);

        // 设置行高30
        table1.setRowHeight(30);
        // 列宽设置为100
        table1.getColumnModel().getColumn(1).setPreferredWidth(80);

        // 设置滚动面板视口大小
        table1.setPreferredScrollableViewportSize(new Dimension(550, 400));
        // 把表格放到滚动面板中（自动添加到顶部）
        JScrollPane scrollPane = new JScrollPane(table1);
        panel.add(scrollPane);
        jf.add(panel);

        jf.pack();
        jf.setContentPane(panel);
        jf.setVisible(true);
            JPopupMenu m_popupMenu = new JPopupMenu();
            JMenuItem deleteMenItem = new JMenuItem();
            JMenuItem alterMenItem = new JMenuItem();
            alterMenItem.setText("  修改评论 ");
            deleteMenItem.setText("  删除评论 ");
            m_popupMenu.add(alterMenItem);
            m_popupMenu.add(deleteMenItem);
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        //通过点击位置找到点击为表格中的行
                        int focusedRowIndex = table1.rowAtPoint(e.getPoint());
                        if (focusedRowIndex == -1) {
                            return;
                        }
                        //将表格所选项设为当前右键点击的行
                        table1.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                        //弹出菜单
                        m_popupMenu.show(table1, e.getX(), e.getY());



                    }
                }
            });
        //修改评论
        alterMenItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int a = table1.getSelectedRow();
                Object c = table1.getValueAt(a, 0);
                int d = (int) c;
                jf.setVisible(false);
                new AlterDiscussByUser(d);
            }
        });//删除评论
        deleteMenItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int a = table1.getSelectedRow();
                Object c = table1.getValueAt(a, 0);
                int d = (int) c;
                new DeleteDiscussByD_id(d);
                jf.setVisible(false);
                serchDiscuss();
            }
        });
    }
    public Object[][] queryData2(String  d) {

        QueryDiscussByUserName queryDiscussByUserid = new QueryDiscussByUserName();

        List<discuss> list1 = queryDiscussByUserid.queryDiscussByUserid(d);
        rowData1 = new Object[list1.size()][column1.length];

        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < column1.length; j++) {
                rowData1[i][0] = list1.get(i).getD_id();
                rowData1[i][1] = list1.get(i).getD_title();
                rowData1[i][2] = list1.get(i).getD_content();
                rowData1[i][3] = list1.get(i).getD_date();

            }

        }
        return rowData1;


    }

}
