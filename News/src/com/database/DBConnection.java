package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection con = null;
	private static String url = "jdbc:mysql://*******/News";
	private static String user = "root";
	private static String password = "*******";
	//**是你的数据库ip和端口号和密码
	public static Connection getCon() {
		// 加载数据库驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		// 获取数据库连接
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
		}
		// 返回Connection对象
		return con;
	}

}
