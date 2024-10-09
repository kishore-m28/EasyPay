package com.payroll_app.project.dto;

import java.time.LocalDate;

public class JobSeekerInterviewDto {
	
	private int applicationId;
	private String applicantName;
	private LocalDate applyDate;
	private String jobTitle;
	private String screenTestStatus;
	public JobSeekerInterviewDto(int applicationId, String applicantName, LocalDate applyDate, String jobTitle,
			String screenTestStatus) {
		super();
		this.applicationId = applicationId;
		this.applicantName = applicantName;
		this.applyDate = applyDate;
		this.jobTitle = jobTitle;
		this.screenTestStatus = screenTestStatus;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public LocalDate getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getScreenTestStatus() {
		return screenTestStatus;
	}
	public void setScreenTestStatus(String screenTestStatus) {
		this.screenTestStatus = screenTestStatus;
	}
	@Override
	public String toString() {
		return "JobSeekerInterviewDto [applicationId=" + applicationId + ", applicantName=" + applicantName
				+ ", applyDate=" + applyDate + ", jobTitle=" + jobTitle + ", screenTestStatus=" + screenTestStatus
				+ "]";
	}
	
	
}