package com.model;

import java.sql.Timestamp;

public class HardworkEvent implements Comparable<HardworkEvent> {
	private String staffNum;
	private int grade;
	private Timestamp time;
	private String interval;

	@Override
	public int compareTo(HardworkEvent o) {
		if (o.getStaffNum().equals(staffNum) && o.getTime().equals(time))
			return 0;
		else
			return o.getTime().compareTo(time);
	}

	@Override
	public String toString() {
		return "加班员工号: " + staffNum + ", 加班级别" + grade + ", 加班日期" + time + ", 加班时长" + interval;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + grade;
		result = prime * result + ((interval == null) ? 0 : interval.hashCode());
		result = prime * result + ((staffNum == null) ? 0 : staffNum.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		HardworkEvent other = (HardworkEvent) obj;
		if (grade != other.grade)
			return false;
		if (interval == null) {
			if (other.interval != null)
				return false;
		} else if (!interval.equals(other.interval))
			return false;
		if (staffNum == null) {
			if (other.staffNum != null)
				return false;
		} else if (!staffNum.equals(other.staffNum))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	public String getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(String staffNum) {
		this.staffNum = staffNum;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

}
