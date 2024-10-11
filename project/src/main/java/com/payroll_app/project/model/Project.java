package com.payroll_app.project.model;

import java.time.LocalDate;

import com.payroll_app.project.enums.ProjectStatus;
import com.payroll_app.project.enums.ProjectType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	@Column(length = 2000)
	private String projectDescription;

	@Enumerated(EnumType.STRING)
	private ProjectType projectType;

	private LocalDate startDate;

	private LocalDate estimatedEndDate;

	private LocalDate completedDate;

	@Enumerated(EnumType.STRING)
	private ProjectStatus status;

	@ManyToOne
	private Manager manager;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEstimatedEndDate() {
		return estimatedEndDate;
	}

	public void setEstimatedEndDate(LocalDate estimatedEndDate) {
		this.estimatedEndDate = estimatedEndDate;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public LocalDate getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(LocalDate completedDate) {
		this.completedDate = completedDate;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

}
