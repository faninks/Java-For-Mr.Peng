package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.TreeSet;

import com.Utils.DBUtil;
import com.model.LeaveEvent;

public class LeaveEventDao {
	public static String[] values = { "职工号", "请假日期", "原由", "请假时长" };

	public static TreeSet<LeaveEvent> findLeaveEvent(String ch) throws SQLException {
		TreeSet<LeaveEvent> sts = new TreeSet<>(), temp;
		for (int i = 0; i < values.length; i++) {
			temp = findByValues(values[i], ch);
			for (Iterator<LeaveEvent> iterator = temp.iterator(); iterator.hasNext();)
				sts.add(iterator.next());
		}
		return sts;
	}
	
	public static TreeSet<LeaveEvent> findByValues(String str, String str2) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from dbo.leave where " + str + " like ?;";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, "%" + str2 + "%");
		// 执行SQL语句
		ResultSet rs = psmt.executeQuery();
		return doQuery(rs);
	}

	public static TreeSet<LeaveEvent> doQuery(ResultSet rs) throws SQLException {
		TreeSet<LeaveEvent> sts = new TreeSet<LeaveEvent>();
		while (rs.next()) {
			LeaveEvent sf = new LeaveEvent();
			sf.setStaffNum(rs.getString("职工号"));
			sf.setTime(rs.getTimestamp("请假日期"));
			sf.setReason(rs.getString("原由"));
			sf.setInterval(rs.getString("请假时长"));
			sts.add(sf);
		}
		return sts;
	}
}
