package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.TreeSet;

import com.db.DBUtil;
import com.model.HardworkEvent;

public class HardworkEventDao {
	public static String[] values = { "职工号", "加班日期", "加班级别", "累计时间" };

	public static TreeSet<HardworkEvent> doQuery(ResultSet rs) throws SQLException {
		TreeSet<HardworkEvent> sts = new TreeSet<HardworkEvent>();
		while (rs.next()) {
			HardworkEvent sf = new HardworkEvent();
			sf.setStaffNum(rs.getString("职工号"));
			sf.setTime(rs.getTimestamp("加班日期"));
			sf.setGrade(rs.getInt("加班级别"));
			sf.setInterval(rs.getString("累计时间"));
			sts.add(sf);
		}
		return sts;
	}

	public static TreeSet<HardworkEvent> findHardworkEvent(String ch) throws SQLException {
		TreeSet<HardworkEvent> sts = new TreeSet<>(), temp;
		for (int i = 0; i < values.length; i++) {
			temp = findByValues(values[i], ch);
			for (Iterator<HardworkEvent> iterator = temp.iterator(); iterator.hasNext();)
				sts.add(iterator.next());
		}
		return sts;
	}

	public static TreeSet<HardworkEvent> findByValues(String str, String str2) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from hardworking where " + str + " like ?;";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, "%" + str2 + "%");
		// 执行SQL语句
		ResultSet rs = psmt.executeQuery();
		return doQuery(rs);
	}
}
