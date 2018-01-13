package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.Utils.DBUtil;
import com.model.Job;

public class JobDao {

	public static ArrayList<Job> doQuery(ResultSet rs) throws SQLException {
		ArrayList<Job> jobs = new ArrayList<>();
		while (rs.next()) {
			Job job = new Job();
			job.setJobNum(rs.getString("职务号"));
			job.setDepartment(rs.getString("部门"));
			job.setJobName(rs.getString("职务"));
			job.setBaseWages(rs.getDouble("基本工资"));
			jobs.add(job);
		}
		return jobs;
	}
	
	public static ArrayList<Job> findByJobnum(String jobn) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "" + "select * from dbo.department where 职务号  like ?;";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, "%"+ jobn + "%");
		// 执行SQL语句
		ResultSet rs = psmt.executeQuery();
		return doQuery(rs);
	}
	
	public static void main(String[] args) {
		try {
			ArrayList<Job> jj = findByJobnum("001");
			for (Iterator<Job> iterator = jj.iterator(); iterator.hasNext();) {
				Job job = iterator.next();
				System.out.println(job.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
