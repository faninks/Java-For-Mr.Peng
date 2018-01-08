package com.Servlet;

import com.model.Job;
import com.model.Staff;

public class Utils {

	public static double getWages(int wagesGrade, Job job) {
        double income = (1 + wagesGrade*0.01) * job.getBaseWages(); // 总收入为工资和加班补贴之和  
        double tax = 0;  
        double rate = 0; // 税率  
        double afterTax = 0; // 税后工资  
        if (income < 2000) { // 总收入为2000元以下，则免征税  
            rate = 0;  
        } else if (income >= 2000 && income < 2500) {  
            rate = 0.05;  
        } else if (income >= 2500 && income < 4000) {  
            rate = 0.1;  
        } else if (income >= 4000 && income < 7000) {  
            rate = 0.15;  
        } else if (income >= 7000 && income < 22000) {  
            rate = 0.2;  
        }else if(income >=22000){  
            rate = 0.3;  
        }  
        tax = income * rate; // 计算税收  
        afterTax = income - tax; // 计算税后工资 
		return afterTax;
	}
	
	public static double getRealWages(Staff st) {
		//(工资提升率*(基本工资*(考勤率)+加班工资))-税收
		// 总收入为工资和加班补贴之和  
		double income = (1 + 0.01 * st.getWagesGrade()) * st.getJob().getBaseWages();
        double tax = 0;  
        double rate = 0; // 税率  
        double afterTax = 0; // 税后工资  
        if (income < 2000) { // 总收入为2000元以下，则免征税  
            rate = 0;  
        } else if (income >= 2000 && income < 2500) {  
            rate = 0.05F;  
        } else if (income >= 2500 && income < 4000) {  
            rate = 0.1F;  
        } else if (income >= 4000 && income < 7000) {  
            rate = 0.15F;  
        } else if (income >= 7000 && income < 22000) {  
            rate = 0.2F;  
        }else if(income >=22000){  
            rate = 0.3F;  
        }  
        tax = income * rate; // 计算税收  
        afterTax = income - tax; // 计算税后工资 
		return afterTax;
	}
}
