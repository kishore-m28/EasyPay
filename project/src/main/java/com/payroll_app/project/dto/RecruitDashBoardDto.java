package com.payroll_app.project.dto;

public class RecruitDashBoardDto 
{
	
	private int countJob;
	

	private int countJobApplication;
	
	private int countJobSeeker;
	
	private String jobTitle;

	

	public RecruitDashBoardDto(int countJob, int countJobApplication, int countJobSeeker, String jobTitle) {
		super();
		this.countJob = countJob;
		this.countJobApplication = countJobApplication;
		this.countJobSeeker = countJobSeeker;
		this.jobTitle = jobTitle;
	}

	public int getCountJob() {
		return countJob;
	}

	public void setCountJob(int countJob) {
		this.countJob = countJob;
	}

	public int getCountJobApplication() {
		return countJobApplication;
	}

	public void setCountJobApplication(int countJobApplication) {
		this.countJobApplication = countJobApplication;
	}

	public int getCountJobSeeker() {
		return countJobSeeker;
	}

	public void setCountJobSeeker(int countJobSeeker) {
		this.countJobSeeker = countJobSeeker;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	
	

	
	

}
