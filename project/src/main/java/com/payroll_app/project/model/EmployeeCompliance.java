package com.payroll_app.project.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EmployeeCompliance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private Compliance compliance;
	
	private String complianceStatus;
	
	private String complianceLevel;
	
	private LocalDate dateChecked;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Compliance getCompliance() {
		return compliance;
	}

	public void setCompliance(Compliance compliance) {
		this.compliance = compliance;
	}

	public String getComplianceStatus() {
		return complianceStatus;
	}

	public void setComplianceStatus(String complianceStatus) {
		this.complianceStatus = complianceStatus;
	}

	public String getComplianceLevel() {
		return complianceLevel;
	}

	public void setComplianceLevel(String complianceLevel) {
		this.complianceLevel = complianceLevel;
	}

	public LocalDate getDateChecked() {
		return dateChecked;
	}

	public void setDateChecked(LocalDate dateChecked) {
		this.dateChecked = dateChecked;
	}
	
	
}
