package com.dao;

import java.sql.Connection;

import com.Menzel3.Dao.JdbcDaoImpl;
import com.Menzel3.ctss.JdbcUtils;
import com.model.Staff;

public class StaffDaoImpl extends JdbcDaoImpl<Staff>{
/**
 * select * from 
 */
	public static void main(String[] args) {
		Connection connection = JdbcUtils.getConnetion(); 
		StaffDaoImpl sdi = new StaffDaoImpl();
		String sql = "Insert ";
		sdi.update(connection, sql, "");
	}
}
