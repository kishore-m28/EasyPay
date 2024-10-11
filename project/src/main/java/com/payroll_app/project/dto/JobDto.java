package com.payroll_app.project.dto;

import java.time.LocalDate;

public class JobDto {
	
	private int id;
	private String jobTitle;
	private String location;
	private String jobType;
	private LocalDate postingDate;
	 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public LocalDate getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(LocalDate postingDate) {
		this.postingDate = postingDate;
	}
	
	

}
