package com.Servlet;

import java.sql.SQLException;
import java.util.TreeSet;

import com.dao.HardworkEventDao;
import com.model.HardworkEvent;

public class HardworkEventServlet {
	
	public static TreeSet<HardworkEvent> quaryFind(String attribute, String ch) {

		TreeSet<HardworkEvent> sts = null;
		try {
			sts = HardworkEventDao.findByValues(attribute, ch);
		} catch (SQLException e) {
			System.out.println("搜索失败，请稍后操作");
		}
		return sts;
	}
}
