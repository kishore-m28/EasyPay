package com.payroll_app.project.dto;

import org.springframework.stereotype.Component;

@Component
public class EmployeeFilterDto {
	
	private String designation;
	private String department;
	private String city;
	
	
	
	public EmployeeFilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeFilterDto(String designation, String department, String city) {
		super();
		this.designation = designation;
		this.department = department;
		this.city = city;
	}

	public String getDesignation() {
		return designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
