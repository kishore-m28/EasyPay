package com.payroll_app.project.dto;

public class EmployeeGenderCountDto {
	
	private String gender;
	private long noOfEmployee;
	public EmployeeGenderCountDto(String gender, long noOfEmployee) {
		super();
		this.gender = gender;
		this.noOfEmployee = noOfEmployee;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getNoOfEmployee() {
		return noOfEmployee;
	}
	public void setNoOfEmployee(long noOfEmployee) {
		this.noOfEmployee = noOfEmployee;
	}
	

}
