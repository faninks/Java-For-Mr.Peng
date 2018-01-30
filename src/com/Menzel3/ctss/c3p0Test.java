package com.Menzel3.ctss;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c3p0Test {

    private static DataSource dataSource = null;
    
    static {
        try {
        	// dataSource = new ComboPooledDataSource();
        	dataSource = new ComboPooledDataSource("SQLServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * 获取数据库连接对象
     */
    public static Connection getConnection(){
    	Connection conn = null;
    	try {
    	conn = dataSource.getConnection();
    	conn.setAutoCommit(false);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return conn;
    }
    
	public static void main(String[] args) {
		Connection conn = c3p0Test.getConnection();
		System.out.println(conn);
		JdbcUtils.release(conn, null, null);
	}

}
