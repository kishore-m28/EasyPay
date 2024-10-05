package com.payroll_app.project.model;

import java.time.LocalDate;

import com.payroll_app.project.enums.WorkStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Work {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String details;
	
	private LocalDate assignedDate;
	
	@Enumerated(EnumType.STRING)
	private WorkStatus status;
	
	@ManyToOne
	private Employee employee;
	@ManyToOne
	private Manager manager;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	public LocalDate getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(LocalDate assignedDate) {
		this.assignedDate = assignedDate;
	}
	public WorkStatus getStatus() {
		return status;
	}
	public void setStatus(WorkStatus status) {
		this.status = status;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	@Override
	public String toString() {
		return "Work [id=" + id + ", details=" + details + ", assignedDate=" + assignedDate + ", status=" + status
				+ ", employee=" + employee + ", manager=" + manager + "]";
	}
	
}
