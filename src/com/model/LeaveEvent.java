package com.model;

import java.sql.Timestamp;

public class LeaveEvent implements Comparable<LeaveEvent>{
	private String staffNum;
	private String reason;
	private Timestamp time;
	private String interval;
	
	@Override
	public int compareTo(LeaveEvent o) {
		if(o.getStaffNum().equals(staffNum)&&o.getTime().equals(time))
			return 0;
		else
			return o.getTime().compareTo(time);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((interval == null) ? 0 : interval.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
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
		LeaveEvent other = (LeaveEvent) obj;
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

	@Override
	public String toString() {
		return "请假员工号: " + staffNum + ", 原由: " + reason + ", 请假时间: " + 
				time + ", 其请假时长: " + interval;
	}

	public String getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(String staffNum) {
		this.staffNum = staffNum;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
