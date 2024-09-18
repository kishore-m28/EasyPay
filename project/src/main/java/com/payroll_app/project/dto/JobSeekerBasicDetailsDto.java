package com.payroll_app.project.dto;

import java.time.LocalDate;

public class JobSeekerBasicDetailsDto {
	
	private String name;
	private LocalDate dateOfBirth;
	private String gender;
	private String currentEmployer;
	private String contact;
	private int EasyPayId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	 
	public String getCurrentEmployer() {
		return currentEmployer;
	}
	public void setCurrentEmployer(String currentEmployer) {
		this.currentEmployer = currentEmployer;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getEasyPayId() {
		return EasyPayId;
	}
	public void setEasyPayId(int easyPayId) {
		EasyPayId = easyPayId;
	}
	 
	
	
	

}
