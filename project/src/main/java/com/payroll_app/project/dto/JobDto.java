package com.payroll_app.project.dto;

import java.time.LocalDate;

public class JobDto {
	
	private String tile;
	private String location;
	private String jobType;
	private LocalDate postingDate;
	 
	 
	public String getTile() {
		return tile;
	}
	public void setTile(String tile) {
		this.tile = tile;
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
