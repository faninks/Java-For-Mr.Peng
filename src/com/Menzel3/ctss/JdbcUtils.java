package com.Menzel3.ctss;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;


/**
 * @title JDBC 连接数据库的三种方式及释放内存的小工具
 * @author White3 首先，超古老的方法我没有写，其次，如果有更优于连接池的方法，请荐之，谢了。
 * @one 静态存储于类中 由于数据库的信息写死了，故弃之。
 * @two 存储于.properties文件中 由于每次连接数据库都要进行io操作，十分消耗内存，弃之。
 * @san 数据库连接池{举例:dbcp(Spring组织推荐的单线程操作), c3p0(Hibernate组织推荐的多线程异步操作)连接池}
 *      使用数据库连接池时，需配置相关文件，提示一下数据库连接池的Connection对象使用close将使连接由使用状态变为等待状态，不是真的成nothing...
 *      目前最佳方案
 */

public class JdbcUtils {
	private static DataSource dataSource = null;
	private static Properties properties = null;
	
	static {
		properties = new Properties();
		try {
			properties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("dbcp.properties"));
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("------start connecting to server------");
		Connection conn = JdbcUtils.getConnetion();
		System.out.println(conn);
		JdbcUtils.release(conn, null, null);
		System.out.println("------close connecting to server------");
	}
	
	/**
	 * 获取数据库连接池的 Connection对象
	 * @return
	 */
	public static Connection getConnetion() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 释放 connection, resultSet, statement对象
	 * 
	 * @param connection
	 * @param resultSet
	 * @param statement
	 */
	public static void release(Connection connection, ResultSet resultSet, Statement statement) {
		if (connection != null)
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (statement != null)
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (resultSet != null)
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}

/*
class one {
	private static String username = "sa";
	private static String password = "123456";
	private static String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=pengchong";
	static Connection conn = null;

	public static Connection getConnection() {
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}

class two {
	public static Connection ConnectiongetConnection() {
		Connection conn = null;
		// 准备连接数据库的四个字符串
		String driverClassName, url, username, password;
		// 创建 Properties 对象
		Properties pro = new Properties();
		// 读取输入流
		try {
			pro.load(DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			// 加载对应输入流
			driverClassName = pro.getProperty("driverClassName");
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
			// 加载数据库驱动程序(通过Drvier实现类的静态代码块内注册驱动)
			Class.forName(driverClassName);
			// 获得数据库的连接
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}

*/