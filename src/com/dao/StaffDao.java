package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;
import java.util.Iterator;

import com.Servlet.Utils;
import com.Utils.DBUtil;
import com.model.Staff;

public class StaffDao {
	public static String[] values = {"姓名","性别","职工号","年龄","工资级别"};

	public static TreeSet<Staff> doQuery(ResultSet rs) throws SQLException {
		TreeSet<Staff> sts = new TreeSet<Staff>();
		while (rs.next()) {
			Staff sf = new Staff();
			sf.setName(rs.getString("姓名"));
			sf.setAge(rs.getInt("年龄"));
			sf.setSex(rs.getString("性别"));
			sf.setStaffNum(rs.getString("职工号"));
			sf.setWagesGrade(rs.getInt("工资级别"));
			String[] jobn = rs.getString("职工号").split("y");
			sf.setJob(JobDao.findByJobnum(jobn[1]).get(0));
			sf.setWages(Utils.getWages(sf.getWagesGrade(), sf.getJob()));
			sts.add(sf);
		}
		return sts;
	}

	public static TreeSet<Staff> findAllStaff() throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select DISTINCT * from staff;";
		PreparedStatement psmt = conn.prepareStatement(sql);
		// 执行SQL语句
		ResultSet rs = psmt.executeQuery();
		return doQuery(rs);
	}

	public static TreeSet<Staff> findStaff(String ch) throws SQLException {
		TreeSet<Staff> sts = new TreeSet<>(), temp; 
		for(int i=0; i<values.length; i++) {
			temp = findByValues(values[i], ch);
			for (Iterator<Staff> iterator = temp.iterator(); iterator.hasNext();)
				sts.add(iterator.next());
		}
		return sts;
	}

	public static TreeSet<Staff> findByValues(String str, String str2) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from staff where " + str + " like ?;";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, "%" + str2 + "%");
		// 执行SQL语句
		ResultSet rs = psmt.executeQuery();
		return doQuery(rs);
	}

	public static TreeSet<Staff> findByValues(String[] values,String str, String str2) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select ";
		if(values.length>0)
		sql += values[0];
		for(int i=1; i<values.length; i++)
			sql += ","+values[i];
		sql +=" from staff where " + str + " like ?;";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, "%" + str2 + "%");
		// 执行SQL语句
		ResultSet rs = psmt.executeQuery();
		return doQuery(rs);
	}
}
