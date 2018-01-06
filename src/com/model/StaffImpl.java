package com.model;


public class StaffImpl implements Staff{
	// 职工号  职务号  工资提升率(<10) 部门  部门  职务  基本工资  姓名  性别  年龄  身份证号
	// A、个人信息、
	private String staffNum;// 职工号
	private String idNum;	// 身份证号
	private String name;
	private String sex;
	private String age;
	private Department department;	// 部门
	// B、工资情况、
	private double wages;
	private int wagesGrade;
	// C、请假情况、
	private double leaveTime;
	// D、加班情况、
	private double hwTime[];
	
	@Override
	public String toString() { 
		//A、个人信息、B、工资情况、C、请假情况、D、加班情况
		return "A、个人信息、\n";
	}
	
	public StaffImpl() {
		super();
	}
}