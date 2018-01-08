package com.Servlet;

import java.sql.SQLException;
import java.util.TreeSet;

import com.dao.LeaveEventDao;
import com.model.LeaveEvent;

public class LeaveEventServlet {
	
	public static TreeSet<LeaveEvent> quaryFind(String attribute, String ch) {

		TreeSet<LeaveEvent> sts = null;
		try {
			sts = LeaveEventDao.findByValues(attribute, ch);
		} catch (SQLException e) {
			System.out.println("搜索失败，请稍后操作");
		}
		return sts;
	}
}
