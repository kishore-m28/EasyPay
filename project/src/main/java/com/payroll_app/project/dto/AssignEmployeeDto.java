package com.payroll_app.project.dto;

import java.time.LocalDate;
import java.util.List;

public class AssignEmployeeDto {

	private int projectId;
	
	private List<Integer> employeeIds;
	
	private LocalDate assignedDate;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public List<Integer> getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(List<Integer> employeeIds) {
		this.employeeIds = employeeIds;
	}

	public LocalDate getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(LocalDate assignedDate) {
		this.assignedDate = assignedDate;
	}
	
	
}
