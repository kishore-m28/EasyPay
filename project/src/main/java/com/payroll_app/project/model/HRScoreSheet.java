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
@Table(name="hr_scoresheet")
public class HRScoreSheet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int communicationScore;
	private int attitudeScore;
	private int interpersonalScore;
	private int adaptabilityScore;
	
	@Enumerated(EnumType.STRING)
	private InterviewStatus status;
	private boolean isSelectedForOffer;
	
	@OneToOne
	private JobApplication jobApplication;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommunicationScore() {
		return communicationScore;
	}

	public void setCommunicationScore(int communicationScore) {
		this.communicationScore = communicationScore;
	}

	public int getAttitudeScore() {
		return attitudeScore;
	}

	public void setAttitudeScore(int attitudeScore) {
		this.attitudeScore = attitudeScore;
	}

	public int getInterpersonalScore() {
		return interpersonalScore;
	}

	public void setInterpersonalScore(int interpersonalScore) {
		this.interpersonalScore = interpersonalScore;
	}

	public int getAdaptabilityScore() {
		return adaptabilityScore;
	}

	public void setAdaptabilityScore(int adaptabilityScore) {
		this.adaptabilityScore = adaptabilityScore;
	}

	public InterviewStatus getStatus() {
		return status;
	}

	public void setStatus(InterviewStatus status) {
		this.status = status;
	}

	public boolean isSelectedForOffer() {
		return isSelectedForOffer;
	}

	public void setSelectedForOffer(boolean isSelectedForOffer) {
		this.isSelectedForOffer = isSelectedForOffer;
	}

	public JobApplication getJobApplication() {
		return jobApplication;
	}

	public void setJobApplication(JobApplication jobApplication) {
		this.jobApplication = jobApplication;
	}

	@Override
	public String toString() {
		return "HRScoreSheet [id=" + id + ", communicationScore=" + communicationScore + ", attitudeScore="
				+ attitudeScore + ", interpersonalScore=" + interpersonalScore + ", adaptabilityScore="
				+ adaptabilityScore + ", status=" + status + ", isSelectedForOffer=" + isSelectedForOffer
				+ ", jobApplication=" + jobApplication + "]";
	}


	
}
