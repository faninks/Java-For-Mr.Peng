package com.model;

import java.util.ArrayList;

public class Staff{
	// 职工号  职务号  工资提升率(<10) 部门  部门  职务  基本工资  姓名  性别  年龄  身份证号
	// A、个人信息、
	private String staffNum;// 职工号
	private String name;
	private String sex;
	private int age;
	private Job job;
	// B、工资情况、
	private double wages;
	private int wagesGrade;
	// C、请假情况、
	private ArrayList<LeaveEvent> lvEvents;
	// D、加班情况、
	private ArrayList<HardworkEvent> hwEvents;
	
	public String getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(String staffNum) {
		this.staffNum = staffNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int i) {
		this.age = i;
	}

	public double getWages() {
		return wages;
	}

	public void setWages(double wages) {
		this.wages = wages;
	}

	public int getWagesGrade() {
		return wagesGrade;
	}

	public void setWagesGrade(int wagesGrade) {
		this.wagesGrade = wagesGrade;
	}

	public ArrayList<LeaveEvent> getLvEvents() {
		return lvEvents;
	}

	public void setLvEvents(ArrayList<LeaveEvent> lvEvents) {
		this.lvEvents = lvEvents;
	}

	public ArrayList<HardworkEvent> getHwEvents() {
		return hwEvents;
	}

	public void setHwEvents(ArrayList<HardworkEvent> hwEvents) {
		this.hwEvents = hwEvents;
	}
	
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public String toString() { 
		//A、个人信息、B、工资情况、C、请假情况、D、加班情况
		return "A、个人信息、\n" + "姓名: " + name + "  性别: " + sex + "  年龄: " + age +"\n部门: " + job.getDepartment() + "  职务: " + job.getJobName() + "  职工号: " + staffNum
		+ "\nB、工资情况、\n" + "基本工资: " + wages + "  工资级别: " + wagesGrade
		+ "\nC、请假情况、\n" + "\nD、加班情况";
	}
	
	public Staff() {
		super();
	}
}