package com.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {

	public static void main(String[] args) {
		update("INSERT INTO department (职务号, 部门, 职务, 基本工资)" + "VALUES ('003x002', '零食部', '扫地僧', 88888880)");
	}

	/**
	 * 释放Connection, ResultSet 和 Statement资源
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void release(ResultSet rs, 
			Connection conn, Statement st) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (st != null)
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 释放Connection 和 Statement资源
	 * @param conn
	 * @param st
	 */
	public static void release(Connection conn, Statement st) {
		if (st != null)
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 通用的版本更新方法，适用于 update, delete, insert方法
	 * @param sql
	 */
	public static void update(String sql) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, st);
		}
	}

	/**
	 * 
	 * @return 与数据库的连接
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		// 准备连接数据库的四个字符串
		String driver, jdbcURL, userName, userPwd;
		// 创建 Properties 对象
		Properties pro = new Properties();
		// 读取输入流
		try {
			pro.load(DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		// 加载对应输入流
		driver = pro.getProperty("driver");
		jdbcURL = pro.getProperty("jdbcURL");
		userName = pro.getProperty("userName");
		userPwd = pro.getProperty("userPwd");
		// 加载数据库驱动程序(通过Drvier实现类的静态代码块内注册驱动)
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		// 获得数据库的连接
		return DriverManager.getConnection(jdbcURL, userName, userPwd);
	}
}

// older0
// private static final String driver =
// "com.microsoft.sqlserver.jdbc.SQLServerDriver";
// private static final String jdbcURL =
// "jdbc:sqlserver://localhost:1433;DatabaseName=pengchong";
// private static final String userName = "sa";
// private static final String userPwd = "123456";
// private static Connection conn = null;
//
// public static Connection getConnection() {
// try {
// Class.forName(driver);
// conn = DriverManager.getConnection(jdbcURL, userName, userPwd);
// } catch (ClassNotFoundException e) {
// e.printStackTrace();
// } catch (SQLException e) {
// e.printStackTrace();
// }
// return conn;
// }
