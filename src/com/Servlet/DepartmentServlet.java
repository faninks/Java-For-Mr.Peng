package com.Servlet;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.TreeSet;

import com.dao.DepartmentDao;
import com.dao.LeaveEventDao;
import com.dao.StaffDao;
import com.model.Department;
import com.model.HardworkEvent;
import com.model.LeaveEvent;
import com.model.Staff;

//2.部门查询
//A、部门一览、B、人员一览、C、加班一览、D、请假一览、
public class DepartmentServlet {
	TreeSet<Department> all;

	public DepartmentServlet() {
		try {
			all = DepartmentDao.findAllDepartment();
		} catch (SQLException e) {
			System.out.println("搜索失败，请稍后重试");
		}
	}

	// A、部门一览
	public String showAll() {
		String tos = "A、部门一览\n";
		for (Iterator<Department> iterator = all.iterator(); iterator.hasNext();) {
			Department department = iterator.next();
			tos += department.getName() + " ";
		}
		return tos;
	}

	// A、所有部门工资一览、
	public String showAllForOne() {
		String tos = "A、所有部门工资一览\n";
		double sums = 0;
		if (all == null)
			System.out.println("搜索失败,请稍后重试");
		for (Iterator<Department> iterator = all.iterator(); iterator.hasNext();) {
			Department department = iterator.next();
			double sum = 0;
			tos += department.getName() + "  ";
			TreeSet<Staff> ss = StaffServlet.quaryFind(StaffDao.values[2] + " " + department.getdNum());
			for (Iterator<Staff> iterator2 = ss.iterator(); iterator2.hasNext();)
				sum += iterator2.next().getWages();
			tos += sum + "\n";
			sums += sum;
		}
		return tos + "共计:" + sums + "\n";
	}

	// B、人员一览、
	public String showStaffs() {
		String tos = "B、人员一览\n";
		if (all == null)
			System.out.println("搜索失败,请稍后重试");
		for (Iterator<Department> iterator = all.iterator(); iterator.hasNext();) {
			Department department = iterator.next();
			tos += department.getName() + "\n";
			TreeSet<Staff> ss = StaffServlet.quaryFind(StaffDao.values[2] + " " + department.getdNum());
			for (Iterator<Staff> iterator2 = ss.iterator(); iterator2.hasNext();)
				tos += iterator2.next().toString().trim() + "\n";
			tos += "\n";
		}
		return tos;
	}
	
	// C、加班一览、
	public String showLvEvts() {
		String tos = "C、加班一览、\n";
		if (all == null)
			System.out.println("搜索失败，请稍后重试");
		for (Iterator<Department> iterator = all.iterator(); iterator.hasNext();) {
			Department dp = iterator.next();
			tos += dp.getName() + "\n";
			TreeSet<HardworkEvent> ss = HardworkEventServlet.quaryFind(LeaveEventDao.values[0], dp.getdNum().trim());
			for (Iterator<HardworkEvent> iterator2 = ss.iterator(); iterator2.hasNext();)
				tos += iterator2.next().toString().trim() + "\n";
			tos += "\n";
		}
		return tos;
	}

	// D、请假一览、
	public String showHdwEvts() {
		String tos = "D、请假一览、\n";
		if (all == null)
			System.out.println("搜索失败，请稍后重试");
		for (Iterator<Department> iterator = all.iterator(); iterator.hasNext();) {
			Department dp = iterator.next();
			tos += dp.getName().trim() + "\n";
			TreeSet<LeaveEvent> ss = LeaveEventServlet.quaryFind(LeaveEventDao.values[0], dp.getdNum().trim());
			for (Iterator<LeaveEvent> iterator2 = ss.iterator(); iterator2.hasNext();)
				tos += iterator2.next().toString().trim() + "\n";
			tos += "\n";
		}
		return tos;
	}

	public String showDistributedWages() {
		String tos = "C、工资提升率分布显示、\n";
		double sums = 0;
		if (all == null)
			System.out.println("搜索失败,请稍后重试");
		for (Iterator<Department> iterator = all.iterator(); iterator.hasNext();) {
			Department department = iterator.next();
			double sum = 0;
			int max = 0, min = 10, mid = 0;
			tos += department.getName() + "\n";
			TreeSet<Staff> ss = StaffServlet.quaryFind(StaffDao.values[2] + " " + department.getdNum());
			for (Iterator<Staff> iterator2 = ss.iterator(); iterator2.hasNext();) {
				Staff s = iterator2.next();
				mid += s.getWagesGrade();
				if(s.getWages()>max) max = s.getWagesGrade();
				else if(s.getWages()<min) min = s.getWagesGrade();
				sum += s.getWages();
			}
			tos += "总工资: " + sum + "  平均工资: " + sum/ss.size() 
				+ "\n最大工资提升率: " + max + "最小工资提升率: " + min + "平均工资提升率: " + mid/ss.size() + "\n\n";
			sums += sum;
		}
		return tos + "共计:" + sums + "\n";
	}
}
