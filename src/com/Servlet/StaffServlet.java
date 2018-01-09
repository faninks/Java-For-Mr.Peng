package com.Servlet;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.TreeSet;

import com.dao.StaffDao;
import com.model.Staff;

//1.员工查询
//A、个人信息、B、工资情况、C、请假情况、D、加班情况
//3.工资全览
//A、所有部门工资一览、B、所有员工工资一览、C、工资提升率分布显示
public class StaffServlet {
	TreeSet<Staff> all;

	public StaffServlet() {
		try {
			all = StaffDao.findAllStaff();
		} catch (SQLException e) {
			System.out.println("连接失败");
		}
	}

	// B、所有员工工资一览、
	public String showOneForAll() {
		String str = "B、所有员工工资一览、\n";
		double sum = 0;
		for (Iterator<Staff> iterator = all.iterator(); iterator.hasNext();) {
			Staff staff = iterator.next();
			str += staff.getName() + staff.getWages() + staff.getJob().getJobName() + "\n";
			sum += staff.getWages();
		}
		return str + "共计:" + sum + "\n";
	}

	public static TreeSet<Staff> quaryFind(String ch) {

		TreeSet<Staff> sts = null;
		String[] str = ch.trim().split("[ ]{1,}");
		try {
			if (str.length == 1)
				sts = StaffDao.findStaff(ch);
			else if (str.length > 2)
				System.out.println("输入异常");
			else {
				if ("姓名".equals(str[0]) || "性别".equals(str[0]) || "职工号".equals(str[0]) || "年龄".equals(str[0])
						|| "工资级别".equals(str[0]))
					sts = StaffDao.findByValues(str[0], str[1]);
			}
		} catch (SQLException e) {
			System.out.println("搜索失败，请稍后操作");
		}
		return sts;
	}

}
