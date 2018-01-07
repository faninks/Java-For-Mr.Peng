package com.model;

import java.util.ArrayList;

public class Department{
	// 部门  员工  职位
	private ArrayList<Staff> staffs;
	private String name;

	public Department() {
		super();
	}

	public Department(String name) {
		super();
		this.name = name;
	}
	
	public ArrayList<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(ArrayList<Staff> staffs) {
		this.staffs = staffs;
	}

	@Override
	public String toString() {
		return "DepartmentImpl [staffs=" + staffs + "]";
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	} 
}
