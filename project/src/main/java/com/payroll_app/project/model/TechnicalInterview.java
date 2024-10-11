package com.payroll_app.project.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="technical_interview")
public class TechnicalInterview {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private LocalDate date;
	private LocalTime fromTime;
	private LocalTime toTime;
	private String interviewLink;
	
	@OneToOne
	private JobApplication jobApplication;

	@ManyToOne
	private Manager manager;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}

	public LocalTime getToTime() {
		return toTime;
	}

	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
	}

	public String getInterviewLink() {
		return interviewLink;
	}

	public void setInterviewLink(String interviewLink) {
		this.interviewLink = interviewLink;
	}

	public JobApplication getJobApplication() {
		return jobApplication;
	}

	public void setJobApplication(JobApplication jobApplication) {
		this.jobApplication = jobApplication;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "TechnicalInterview [id=" + id + ", date=" + date + ", fromTime=" + fromTime + ", toTime=" + toTime
				+ ", interviewLink=" + interviewLink + ", jobApplication=" + jobApplication + ", manager=" + manager
				+ "]";
	}
}
