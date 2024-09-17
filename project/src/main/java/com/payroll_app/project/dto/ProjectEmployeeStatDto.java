package com.payroll_app.project.dto;

public class ProjectEmployeeStatDto {
	
	private String projectType;
	private long numberOfEmployee;
	
	public ProjectEmployeeStatDto(String projectType, long numberOfEmployee) {
		super();
		this.projectType = projectType;
		this.numberOfEmployee = numberOfEmployee;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public long getNumberOfEmployee() {
		return numberOfEmployee;
	}
	public void setNumberOfEmployee(long numberOfEmployee) {
		this.numberOfEmployee = numberOfEmployee;
	}
	@Override
	public String toString() {
		return "ProjectEmployeeStatDto [projectType=" + projectType + ", numberOfEmployee=" + numberOfEmployee + "]";
	}
	
}
