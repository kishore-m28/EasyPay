package com.payroll_app.project.dto;

import org.springframework.stereotype.Component;

import com.payroll_app.project.enums.Department;
import com.payroll_app.project.enums.Designation;

@Component
public class EmployeeDisplayDto {
	
	private int id;
	private String name;
	private Department department;
	private Designation designation;
	private int workingDays;
	private int leave;
	
	
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
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	public int getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(int workingDays) {
		this.workingDays = workingDays;
	}
	public int getLeave() {
		return leave;
	}
	public void setLeave(int leave) {
		this.leave = leave;
	}
	
}
