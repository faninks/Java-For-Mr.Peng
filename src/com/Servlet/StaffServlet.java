package com.Servlet;

import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.StaffDao;
import com.model.Staff;

//1.员工查询
//A、个人信息、B、工资情况、C、请假情况、D、加班情况
//3.工资全览
//A、所有部门工资一览、B、所有员工工资一览、C、工资提升率分布显示
public class StaffServlet {

	public static ArrayList<Staff> quaryFind(String ch) {
		
		ArrayList<Staff> sts = new ArrayList<>();
		String[] str = ch.split(" ");
		try {
			if(str.length==1)
				sts = StaffDao.findStaff(ch);
			else {
				
			}
		} catch (SQLException e) {
			System.out.println("搜索失败，请稍后操作");
		}
		return sts;
	}

}
