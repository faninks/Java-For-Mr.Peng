package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=AI_COMPANY_DATA";
	private static final String userName="sa";
	private static final String userPwd="123456";
	private static Connection conn=null;
	
	static {
		try {
			//1.加载驱动程序
			Class.forName(driverName);
			//2.获得数据库的连接
			conn=DriverManager.getConnection(dbURL, userName, userPwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return conn;
	}

}
