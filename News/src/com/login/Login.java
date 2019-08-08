package com.login;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.entity.*;

import javax.swing.*;

import com.database.DBConnection;
import com.management.*;

public class Login extends Frame implements ActionListener {
	 user user = new user();
	 static int supid ;
	private static final long serialVersionUID = 1L;
	// 记录登录次数
	private int flag = 0;
	// 创建标签、文本框、密码框对象
	JLabel JLloginname = new JLabel("用户名：");
	JLabel JLpassword = new JLabel("密    码：");
	JTextField JTloginname = new JTextField();
	JPasswordField JTpassword = new JPasswordField();
	// 创建按钮对象
	JButton login = new JButton("登录");
	JButton cancle = new JButton("取消");
	JButton visitor = new JButton("访客");
	JButton registered = new JButton("用户注册");
	JLabel identity = new JLabel("身    份：");
	// 创建下拉列表框对象
	JComboBox<String> JC = new JComboBox<String>();

	public Login() {

		this.setTitle("新闻发布系统");
		this.setLayout(null);
		this.setResizable(false); // 禁止改变窗口大小

		// 姓名
		JLloginname.setBounds(100, 50, 60, 20);
		JTloginname.setBounds(200, 50, 100, 20);
		this.add(JLloginname);
		this.add(JTloginname);

		// 密码
		JLpassword.setBounds(100, 100, 60, 20);
		JTpassword.setBounds(200, 100, 100, 20);
		this.add(JLpassword);
		this.add(JTpassword);

		// 身份
		identity.setBounds(100, 150, 60, 20);
		JC.setBounds(200, 150, 100, 20);
		JC.addItem(new String("用户"));
		JC.addItem(new String("管理员"));
		this.add(identity);
		this.add(JC);

		// 登录、取消
		login.setBounds(50, 200, 60, 20);
		cancle.setBounds(140, 200, 60, 20);
		visitor.setBounds(220, 200, 60, 20);
		registered.setBounds(320, 200, 60, 20);

		login.addActionListener(this);
		cancle.addActionListener(this);
		visitor.addActionListener(this);
		registered.addActionListener(this);
		this.add(login);
		this.add(cancle);
		this.add(visitor);
		this.add(registered);

		this.setVisible(true);
		this.setSize(400, 250); // 设置窗口的大小
		this.setLocationRelativeTo(null);// 窗体居中显示

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			}
		});

	}

	// 身份验证
	private boolean logindb(String password, String sql) {
		// 查询数据库
		ResultSet rs = null;

		// 验证用户名是否存在
		try {
			rs = DBConnection.getCon().createStatement().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 验证密码是否存在
		try {
			if (rs.next() && rs.getString(1).equals(password)) {
				rs.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 处理登录事件
		if (e.getSource() == login) {

			// 将文本框中包含的文本传给字符串name
			String name = JTloginname.getText();
			String password = new String(JTpassword.getPassword());


			// 将当前所选项传给字符串box
			String box = (String) JC.getSelectedItem();
			String loginsql = null;

			if (box.equals("用户")) {
				loginsql = "select u_password from user where u_loginname ='" + name + "'";
				user.setU_loginname(name);
				// 登录成功后进入，否则返回提示信息
				if (logindb(password, loginsql)) {
					this.setVisible(false);
					new usermanagement();
				} else {
					flag++;
					if (flag >= 3) {
						JOptionPane.showMessageDialog(this, "输入三次错误，退出登录！", "提示信息", JOptionPane.WARNING_MESSAGE);
						System.exit(0);
					}

					JOptionPane.showMessageDialog(this, "输入有误，请重新输入！", "提示信息", JOptionPane.WARNING_MESSAGE);
				}

			}
			if (box.equals("管理员")) {
				admin admin = new admin();
				admin.setA_loginname(name);
				String sql = "select a_supid from admin where a_loginname = '"+name +"'";
				loginsql = "select a_password from admin where a_loginname ='" + name + "'";
				if (logindb(password, loginsql)){


					try{
						Statement stm = DBConnection.getCon().createStatement();
						// 结果集

						ResultSet rs = stm.executeQuery(sql);
						if (rs.next())
						 supid = rs.getInt(1);

					}
					catch (SQLException e1){
					e1.printStackTrace();
					}
					if (supid == 1) {
						this.setVisible(false);
						new adminmanagement(); }

					if (supid == 2){
						new superAdminManagement();
					}
				}

				else {
					flag++;
					if (flag >= 3) {
						JOptionPane.showMessageDialog(this, "输入三次错误，退出登录！", "提示信息", JOptionPane.WARNING_MESSAGE);
						System.exit(0);
					}
					JOptionPane.showMessageDialog(this, "输入有误，请重新输入！", "提示信息", JOptionPane.WARNING_MESSAGE);
				}

			}

		}

		// 处理取消事件
		if (e.getSource() == cancle)

		{
			System.out.println("退出成功！");
			System.exit(0);
		}
		if(e.getSource() == registered)
		{
			System.out.println("注册！");
			new registered();

		}
		if (e.getSource()== visitor){
			this.setVisible(false);
			new visitormanagement();
		}



	}

	public static void main(String[] args) {
		new Login();
		// new StudentManagement();
		//new registered();
	}

}