package com.payroll_app.project.model;

import com.payroll_app.project.enums.InterviewStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="technical_scoresheet")
public class TechnicalScoreSheet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int technicalKnowledgeScore;
	private int problemSolvingScore;
	private int codingSkillScore;
	private int communicationScore;
	
	@Enumerated(EnumType.STRING)
	private InterviewStatus status;
	private boolean isSelectedForHR;
	
	@OneToOne
	private JobApplication jobApplication;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTechnicalKnowledgeScore() {
		return technicalKnowledgeScore;
	}

	public void setTechnicalKnowledgeScore(int technicalKnowledgeScore) {
		this.technicalKnowledgeScore = technicalKnowledgeScore;
	}

	public int getProblemSolvingScore() {
		return problemSolvingScore;
	}

	public void setProblemSolvingScore(int problemSolvingScore) {
		this.problemSolvingScore = problemSolvingScore;
	}

	public int getCodingSkillScore() {
		return codingSkillScore;
	}

	public void setCodingSkillScore(int codingSkillScore) {
		this.codingSkillScore = codingSkillScore;
	}

	public int getCommunicationScore() {
		return communicationScore;
	}

	public void setCommunicationScore(int communicationScore) {
		this.communicationScore = communicationScore;
	}

	public InterviewStatus getStatus() {
		return status;
	}

	public void setStatus(InterviewStatus status) {
		this.status = status;
	}

	public boolean isSelectedForHR() {
		return isSelectedForHR;
	}

	public void setSelectedForHR(boolean isSelectedForHR) {
		this.isSelectedForHR = isSelectedForHR;
	}

	public JobApplication getJobApplication() {
		return jobApplication;
	}

	public void setJobApplication(JobApplication jobApplication) {
		this.jobApplication = jobApplication;
	}

	@Override
	public String toString() {
		return "TechnicalScoreSheet [id=" + id + ", technicalKnowledgeScore=" + technicalKnowledgeScore
				+ ", problemSolvingScore=" + problemSolvingScore + ", codingSkillScore=" + codingSkillScore
				+ ", communicationScore=" + communicationScore + ", status=" + status + ", isSelectedForHR="
				+ isSelectedForHR + ", jobApplication=" + jobApplication + "]";
	}
	
}
