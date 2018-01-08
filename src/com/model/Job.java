package com.model;

public class Job {
	private String jobNum;
	private String jobName;
	private String department;
	private double baseWages;
	
	public String getJobNum() {
		return jobNum;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getBaseWages() {
		return baseWages;
	}
	public void setBaseWages(double baseWages) {
		this.baseWages = baseWages;
	}
	@Override
	public String toString() {
		return "Job [jobNum=" + jobNum + ", jobName=" + jobName + ", department=" + department + ", baseWages="
				+ baseWages + "]";
	}
}
