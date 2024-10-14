package com.payroll_app.project.dto;

import org.springframework.stereotype.Component;

import com.payroll_app.project.enums.Department;

@Component
public class ReviewPayrollDisplay {
	
	private int id;
	private String name;
	private Department department;
	private double grossPay;
	private double deduction;
	private double netPay;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public double getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}
	public double getDeduction() {
		return deduction;
	}
	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}
	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
