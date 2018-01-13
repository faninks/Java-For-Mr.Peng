package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.TreeSet;

import com.Utils.DBUtil;
import com.model.Department;

public class DepartmentDao {
	public static String[] values = { "部门号", "部门名称" };

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
		String sql = "select * from organization where " + str + " like ?;";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, "%" + str2 + "%");
		// 执行SQL语句
		ResultSet rs = psmt.executeQuery();
		return doQuery(rs);
	}

	public static TreeSet<Department> findByValue(String str, String str2) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from organization where " + str + " like ?;";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, "%" + str2 + "%");
		// 执行SQL语句
		ResultSet rs = psmt.executeQuery();
		return doQuery(rs);
	}
	
	public static TreeSet<Department> findAllDepartment() throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from organization;";
		PreparedStatement psmt = conn.prepareStatement(sql);
		// 执行SQL语句
		ResultSet rs = psmt.executeQuery();
		return doBaseQuery(rs);
	}
	
	public static TreeSet<Department> doBaseQuery(ResultSet rs) throws SQLException {
		TreeSet<Department> sts = new TreeSet<Department>();
		while (rs.next()) {
			Department sf = new Department();
			sf.setdNum(rs.getString(values[0]));
			sf.setName(rs.getString(values[1]));
			sts.add(sf);
		}
		return sts;
	}
	
	public static TreeSet<Department> doQuery(ResultSet rs) throws SQLException {
		TreeSet<Department> sts = new TreeSet<Department>();
		while (rs.next()) {
			Department sf = new Department();
			sf.setdNum(rs.getString(values[0]));
			sf.setStaffs(StaffDao.findByValues(values[0], sf.getdNum()));
			sts.add(sf);
		}
		return sts;
	}
}
