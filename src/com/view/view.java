package com.view;

import java.util.TreeSet;
import java.util.Iterator;
import java.util.Scanner;

import com.Servlet.StaffServlet;
import com.model.Staff;

public class view {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		views();
	}
	//1.员工查询
	//A、个人信息、B、工资情况、C、请假情况、D、加班情况
	//2.部门查询
	//A、人员一览、B、加班一览、C、请假一览
	//3.工资全览
	//A、所有部门工资一览、B、所有员工工资一览、C、工资提升率分布显示
	public static void views() {
		Scanner cin = new Scanner(System.in);
		String ch = "";
		System.out.println("欢迎进入工资管理系统");
		while(true) {
			System.out.println("1.员工查询");
			System.out.println("2.部门查询");
			System.out.println("3.工资全览");
			System.out.println("quit.退出系统");
			System.out.println("请输入你的选择");
			ch = cin.nextLine();
			if("1".equals(ch)) {
				while(true) {
					System.out.println("-----------------------------------------");
					System.out.println("请输入(部分)用户信息格式如下:");
					System.out.println("姓名|性别|职工号|年龄|工资级别 +[空格]+[信息] , 或输入信息");
					System.out.println("后退请输入q");
					ch = cin.nextLine();
					if("q".equals(ch))
						break;
					else {
						TreeSet<Staff> sts = StaffServlet.quaryFind(ch);
						if(sts.isEmpty())
							System.out.println("搜索结果为空哦~");
						else {
							System.out.println("-----------------------------------------");
							for (Iterator<Staff> iterator = sts.iterator(); iterator.hasNext();) {
								Staff staff = iterator.next();
								System.out.println(staff.toString());
							}
						}
						System.out.println("是否继续(1/0||y/n)");
						ch = cin.nextLine();
						if("1".equals(ch)||"y".equals(ch))
							continue;
						else {
							break;
						}
					}
				}
			}else if("2".equals(ch)) {
				System.out.println("A、人员一览、");
				System.out.println("B、加班一览、");
				System.out.println("C、请假一览、");
				System.out.println("D、后退");
			}else if("3".equals(ch)) {
				System.out.println("A、所有部门工资一览、");
				System.out.println("B、所有员工工资一览、");
				System.out.println("C、工资提升率分布显示、");
				System.out.println("D、后退");
			}else if("quit".equals(ch)) {
				break;
			}else {
				System.out.println("输入异常,请重新输入");
			}
		}
		cin.close();
	}
}
