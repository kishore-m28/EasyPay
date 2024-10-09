package com.payroll_app.project.dto;

import com.payroll_app.project.enums.InterviewStatus;

public class JobSeekerInterviewDto {
	
	private int applicationId;
	private String applicantName;
	private String jobTitle;
	private String screenTestStatus;
	private String techStatus;
	private String hrStatus="PENDING";
	public JobSeekerInterviewDto(int applicationId, String applicantName, String jobTitle, String screenTestStatus,
			String techStatus) {
		super();
		this.applicationId = applicationId;
		this.applicantName = applicantName;
		this.jobTitle = jobTitle;
		this.screenTestStatus = screenTestStatus;
		this.techStatus = techStatus;
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
	public String getTechStatus() {
		return techStatus;
	}
	public void setTechStatus(String techStatus) {
		this.techStatus = techStatus;
	}
	public String getHrStatus() {
		return hrStatus;
	}
	public void setHrStatus(String hrStatus) {
		this.hrStatus = hrStatus;
	}
	
}
