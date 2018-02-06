package com.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.Menzel3.Dao.JdbcDaoImpl;
import com.Menzel3.ctss.JdbcUtils;
import com.model.Staff;

public class StaffDaoImpl extends JdbcDaoImpl<Staff> {
	
	/**
	 * select * from
	 */
	public static void main(String[] args) {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnetion();
			StaffDaoImpl sdi = new StaffDaoImpl();
			String sql = "Update staff set 职工号='520' where 姓名='吾爱龙'";
			sdi.update(connection, sql);
			List<Staff> list = sdi.getForList(connection, "select 姓名 name from staff");
			for (Iterator<Staff> iterator = list.iterator(); iterator.hasNext();) {
				Staff staff = iterator.next();
				System.out.println(staff.getName());
			}
			System.out.println("-----------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.release(connection, null, null);
		}
	}
}
