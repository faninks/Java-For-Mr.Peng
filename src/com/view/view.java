package com.view;

import java.util.TreeSet;
import java.util.Iterator;
import java.util.Scanner;

import com.Servlet.HardworkEventServlet;
import com.Servlet.LeaveEventServlet;
import com.Servlet.StaffServlet;
import com.model.Staff;

public class view {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		views();
	}

	public static void views() {
		Scanner cin = new Scanner(System.in);
		String ch;
		System.out.println("欢迎进入工资管理系统");
		while (true) {
			ch = "";
			System.out.println("1.员工查询");
			System.out.println("2.部门查询");
			System.out.println("3.工资全览");
			System.out.println("quit.退出系统");
			System.out.println("请输入你的选择");
			ch = cin.nextLine();
			if ("1".equals(ch)) {
				view0(cin);
			} else if ("2".equals(ch)) {
				view1(cin);
			} else if ("3".equals(ch)) {
				view2(cin);
			} else if ("quit".equals(ch)) {
				break;
			} else {
				System.out.println("输入异常,请重新输入");
			}
		}
		cin.close();
	}

	// 1.员工查询
	// A、个人信息、B、工资情况、C、请假情况、D、加班情况
	public static void view0(Scanner cin) {
		boolean flag, flag2;
		String ch;
		while (true) {
			System.out.println("-----------------------------------------");
			System.out.println("请输入(可部分)用户信息格式如下:");
			System.out.println("姓名|性别|职工号|年龄|工资级别 +[空格]+[信息] , 或输入信息");
			System.out.println("后退请输入q");
			ch = cin.nextLine();
			if ("q".equals(ch.trim())||"Q".equals(ch.trim()))
				break;
			else {
				TreeSet<Staff> sts = StaffServlet.quaryFind(ch);
				if (sts.isEmpty())
					System.out.println("搜索结果为空哦~");
				else {
					flag = false;
					flag2 = false;
					System.out.println("是否显示详细的请假记录？(1/0|y/n)");
					ch = cin.nextLine();
					if ("1".equals(ch.trim()) || "y".equals(ch.trim()))
						flag = true;
					System.out.println("是否显示详细的加班记录？(1/0|y/n)");
					ch = cin.nextLine();
					if ("1".equals(ch.trim()) || "y".equals(ch.trim()))
						flag2 = true;
					System.out.println("-----------------------------------------");
					for (Iterator<Staff> iterator = sts.iterator(); iterator.hasNext();) {
						Staff staff = iterator.next();
						ch = staff.toString() + "\n";
						if (flag) {
							staff.setLvEvents(LeaveEventServlet.quaryFind("职工号", staff.getStaffNum()));
							ch += staff.toStringLeaveEvent().trim() + "\n";
						}
						if (flag2) {
							staff.setHwEvents(HardworkEventServlet.quaryFind("职工号", staff.getStaffNum()));
							ch += staff.toStringhardworkEvent().trim() + "\n";
						}
						System.out.print(ch);
					}
				}
				System.out.println("是否继续(1/0|y/n)");
				ch = cin.nextLine();
				if ("1".equals(ch) || "y".equals(ch))
					continue;
				else
					break;
			}
		}
	}

	// 2.部门查询
	// A、人员一览、B、加班一览、C、请假一览、D、部门详情、
	public static void view1(Scanner cin) {
		String ch = "";
		while (true) {
			System.out.println("-----------------------------------------");
			System.out.println("请输入选项: ");
			System.out.println("A、人员一览、");
			System.out.println("B、加班一览、");
			System.out.println("C、请假一览、");
			System.out.println("D、部门详情、");
			System.out.println("q、后退");
			ch = cin.nextLine();
			switch(ch.trim()) {
				case "A":case "a":
					
				case "B":case "b":
				case "C":case "c":
				case "D":case "d":
			}
			if("q".equals(ch.trim())||"Q".equals(ch.trim()))
				break;
		}
	}

	// 3.工资全览
	// A、所有部门工资一览、B、所有员工工资一览、C、工资提升率分布显示
	public static void view2(Scanner cin) {
		while (true) {
			System.out.println("A、所有部门工资一览、");
			System.out.println("B、所有员工工资一览、");
			System.out.println("C、工资提升率分布显示、");
			System.out.println("D、后退");
		}
	}
}
