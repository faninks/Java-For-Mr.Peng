package com.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

public class DBUtil {

	/**
	 * 返回通过sql语句查询出数据,但是只限一个数据, 或数据的第一条
	 * @param sql 针对 select 类型的sql语句的执行
	 * @param clazz 查询结果的数据类
	 * @param args sql语句中的未知值 (?)
	 * @return
	 */
	public static <T> T get(String sql, Class<T> clazz, Object... args) {
		T obj = null;

		Connection conn = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		try {
			// 1> 获取 ResultSet 对象
			conn = getConnection();
			ppst = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; ++i)
				ppst.setObject(i + 1, args[i]);
			rs = ppst.executeQuery();
			// 2> 获取 ResultSetMetaData 对象
			ResultSetMetaData rsmd = rs.getMetaData();
			// 3> 创建 Map<String, Object> 对象;
			// 键: sql查询项的列的别名 ; 值: 列的值
			Map<String, Object> values = new HashMap<>();
			// 4> 处理结果集
			if (rs.next())
				for (int i = 0; i < rsmd.getColumnCount(); ++i) {
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(columnLabel);
					values.put(columnLabel, columnValue);
				}
			// 5> 通过反射赋值创建得到该对象
			if (values.size() > 0) {
				obj = clazz.newInstance();
				for (Map.Entry<String, Object> entry : values.entrySet()) {
					ReflectionUtils.setFieldValue(obj, entry.getKey(), entry.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(rs, conn, ppst);
		}
		return obj;
	}
	
	/**
	 * 返回通过sql语句查询出数据,以 TreeSet的形式返回
	 * @param sql 针对 select 类型的sql语句的执行
	 * @param clazz 查询结果的数据类
	 * @param args sql语句中的未知值 (?)
	 * @return
	 */
	public static <T> TreeSet<T> getValues(String sql, Class<T> clazz, Object... args) {
		TreeSet<T> objs = new TreeSet<>();
		T obj = null;

		Connection conn = null;
		PreparedStatement ppst = null;
		ResultSet rs = null;
		try {
			// 1> 获取 ResultSet 对象
			conn = getConnection();
			ppst = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; ++i)
				ppst.setObject(i + 1, args[i]);
			rs = ppst.executeQuery();
			// 2> 获取 ResultSetMetaData 对象
			ResultSetMetaData rsmd = rs.getMetaData();
			// 3> 创建 Map<String, Object> 对象;
			// 键: sql查询项的列的别名 ; 值: 列的值
			Map<String, Object> values = new HashMap<>();
			// 4> 处理结果集
			while (rs.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); ++i) {
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object columnValue = rs.getObject(columnLabel);
					values.put(columnLabel, columnValue);
				}
				// 5> 通过反射赋值创建得到该对象
				if (values.size() > 0) {
					obj = clazz.newInstance();
					for (Map.Entry<String, Object> entry : values.entrySet()) {
						ReflectionUtils.setFieldValue(obj, entry.getKey(), entry.getValue());
					}
				}
				values.clear();
				objs.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(rs, conn, ppst);
		}
		return objs;
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
	 * 通用的版本更新方法，适用于 update, delete, insert方法
	 * @param sql
	 */
	public static void update(String sql) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = getConnection();
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(null, conn, st);
		}
	}

	/**
	 * 获取与数据库的连接
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