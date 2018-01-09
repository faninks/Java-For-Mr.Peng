package com.model;

import java.util.Iterator;
import java.util.TreeSet;

public class Department implements Comparable<Department>{
	// 部门  员工  职位
	private TreeSet<Staff> staffs;
	private String name;
	private String dNum; // 002x001 -> 002 = dNum
	private int num;	// temp
	
	public int getNum() {
		return num;
	}
	
	@Override
	public int compareTo(Department o) {
		if(o.getNum()<this.num)
			return 1;
		else if(o.getdNum().equals(dNum))
			return 0;
		return -1;
	}

	@Override
	public String toString() {
		String str = "部门名称: " + name;
		if(staffs!=null) {
			str += " 人员如下:\n";
			for (Iterator<Staff> iterator = staffs.iterator(); iterator.hasNext();) {
				Staff staff = iterator.next();
				str += staff.toString() + "\n";
			}
		}
		return str;
	}
	
	public String toStringEasy() {
		String str = "部门名称: " + name;
		if(staffs!=null) {
			str += " 人员如下:\n";
			for (Iterator<Staff> iterator = staffs.iterator(); iterator.hasNext();) {
				Staff staff = iterator.next();
				str += staff.getJob().getJobName() + staff.getName() + "\n";
			}
		}
		return str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dNum == null) ? 0 : dNum.hashCode());
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
		Department other = (Department) obj;
		if (dNum == null) {
			if (other.dNum != null)
				return false;
		} else if (!dNum.equals(other.dNum))
			return false;
		return true;
	}

	public TreeSet<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(TreeSet<Staff> staffs) {
		this.staffs = staffs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getdNum() {
		return dNum;
	}

	public void setdNum(String dNum) {
		this.dNum = dNum.trim();
		this.num = Integer.valueOf(dNum.split("x")[0]);
	}
	
}
