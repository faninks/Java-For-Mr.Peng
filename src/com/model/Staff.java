package com.model;

import java.util.TreeSet;

public class Staff implements Comparable<Staff>{
	// 职工号  职务号  工资提升率(<10) 部门  部门  职务  基本工资  姓名  性别  年龄  身份证号
	// A、个人信息、
	private String staffNum = "";// 职工号
	private String name = "";
	private String sex = "";
	private int age = 0;
	private Job job = null;
	// B、工资情况、
	private double wages = 0;
	private int wagesGrade = 0;
	// C、请假情况、
	private TreeSet<LeaveEvent> lvEvents = null;
	// D、加班情况、
	private TreeSet<HardworkEvent> hwEvents = null;


	@Override
	public int compareTo(Staff o) {
		if(o.getStaffNum().equals(this.staffNum) || this.staffNum.equals(o.getStaffNum()))
			return 0;
		else {
			if(o.getWages()>this.wages)
				return 1;
			else 
				return -1;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((hwEvents == null) ? 0 : hwEvents.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((lvEvents == null) ? 0 : lvEvents.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((staffNum == null) ? 0 : staffNum.hashCode());
		long temp;
		temp = Double.doubleToLongBits(wages);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + wagesGrade;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		if (staffNum == null) {
			if (other.staffNum != null)
				return false;
		} else if (!staffNum.equals(other.staffNum))
			return false;
		return true;
	}
	
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

	public TreeSet<LeaveEvent> getLvEvents() {
		return lvEvents;
	}

	public void setLvEvents(TreeSet<LeaveEvent> lvEvents) {
		this.lvEvents = lvEvents;
	}

	public TreeSet<HardworkEvent> getHwEvents() {
		return hwEvents;
	}

	public void setHwEvents(TreeSet<HardworkEvent> hwEvents) {
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
		if(this.job==null)
			return "A、个人信息、\n" + "姓名: " + name + "  性别: " + sex + "  年龄: " + age +"\n部门: " + "  职工号: " + staffNum
					+ "\nB、工资情况、\n" + "基本工资: " + wages + "  工资级别: " + wagesGrade
					+ "\nC、请假情况、\n" + "\nD、加班情况";
		return "A、个人信息、\n" + "姓名: " + name + "  性别: " + sex + "  年龄: " + age +"\n部门: " + job.getDepartment() + "  职务: " + job.getJobName() + "  职工号: " + staffNum
		+ "\nB、工资情况、\n" + "基本工资: " + wages + "  工资级别: " + wagesGrade
		+ "\nC、请假情况、\n" + "\nD、加班情况";
	}
	
	public Staff() {
		super();
	}
}