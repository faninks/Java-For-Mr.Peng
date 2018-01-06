package com.model;

import java.util.ArrayList;

public class DepartmentImpl implements Department{
	// 部门  员工  职位
	private ArrayList<Staff> staffs;

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
}
