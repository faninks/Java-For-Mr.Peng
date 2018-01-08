package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.TreeSet;

import com.db.DBUtil;
import com.model.Department;

public class DepartmentDao {
	public static String[] values = { "职务号", "部门", "职务", "基本工资" };

	public static TreeSet<Department> findDepartment(String ch) throws SQLException {
		TreeSet<Department> sts = new TreeSet<>(), temp;
		for (int i = 0; i < values.length; i++) {
			temp = findByValues(values[i], ch);
			for (Iterator<Department> iterator = temp.iterator(); iterator.hasNext();)
				sts.add(iterator.next());
		}
		return sts;
	}

	public static TreeSet<Department> findByValues(String str, String str2) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from department where " + str + " like ?;";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, "%" + str2 + "%");
		// 执行SQL语句
		ResultSet rs = psmt.executeQuery();
		return doQuery(rs);
	}
	
	public static TreeSet<Department> findAllDepartment(String str, String str2) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from department where " + str + " like ?;";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, "%" + str2 + "%");
		// 执行SQL语句
		ResultSet rs = psmt.executeQuery();
		return doQuery(rs);
	}

	public static TreeSet<Department> doQuery(ResultSet rs) throws SQLException {
		TreeSet<Department> sts = new TreeSet<Department>();
		while (rs.next()) {
			Department sf = new Department();
			String ch = rs.getString(values[0]);
			sf.setdNum(ch.split("x")[0]);
			sf.setStaffs(StaffDao.findByValues(values[0], ch));
			sts.add(sf);
		}
		return sts;
	}
}
