package com.model;

public class Factory {
	
	public static Staff getStaff() {
		return new StaffImpl();
	}
	
	public static  Department getDepartment() {
		return new DepartmentImpl();
	}
}
