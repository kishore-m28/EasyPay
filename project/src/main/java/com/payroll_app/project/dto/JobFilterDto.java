package com.payroll_app.project.dto;

import org.springframework.stereotype.Component;

import com.payroll_app.project.enums.City;
import com.payroll_app.project.enums.JobType;

@Component
public class JobFilterDto {
	
	private City city;
	private JobType jobType;
	
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public JobType getJobType() {
		return jobType;
	}
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
}
