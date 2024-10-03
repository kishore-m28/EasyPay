package com.payroll_app.project.dto;

public class SkillsCompareDto {
	
	private String jobSkill1;
	
	private String jobSkill2;
	
	private String jobSkill3;
	
	private String resumeSkill1;
	
	private String resumeSkill2;
	
	private String resumeSkill3;

	

	public SkillsCompareDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SkillsCompareDto(String jobSkill1, String jobSkill2, String jobSkill3, String resumeSkill1,
			String resumeSkill2, String resumeSkill3) {
		super();
		this.jobSkill1 = jobSkill1;
		this.jobSkill2 = jobSkill2;
		this.jobSkill3 = jobSkill3;
		this.resumeSkill1 = resumeSkill1;
		this.resumeSkill2 = resumeSkill2;
		this.resumeSkill3 = resumeSkill3;
	}

	public String getJobSkill1() {
		return jobSkill1;
	}

	public void setJobSkill1(String jobSkill1) {
		this.jobSkill1 = jobSkill1;
	}

	public String getJobSkill2() {
		return jobSkill2;
	}

	public void setJobSkill2(String jobSkill2) {
		this.jobSkill2 = jobSkill2;
	}

	public String getJobSkill3() {
		return jobSkill3;
	}

	public void setJobSkill3(String jobSkill3) {
		this.jobSkill3 = jobSkill3;
	}

	public String getResumeSkill1() {
		return resumeSkill1;
	}

	public void setResumeSkill1(String resumeSkill1) {
		this.resumeSkill1 = resumeSkill1;
	}

	public String getResumeSkill2() {
		return resumeSkill2;
	}

	public void setResumeSkill2(String resumeSkill2) {
		this.resumeSkill2 = resumeSkill2;
	}

	public String getResumeSkill3() {
		return resumeSkill3;
	}

	public void setResumeSkill3(String resumeSkill3) {
		this.resumeSkill3 = resumeSkill3;
	}
	
	
	
	

}
