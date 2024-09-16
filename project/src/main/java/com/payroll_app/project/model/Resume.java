package com.payroll_app.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Resume {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private double sslc;
	
	private double hsc;
	
	private int cgpa;
	
	private String skill1;
	
	private String skill2;
	
	private String skill3;
	
	private String certification1;
	
	private String certification2;
	
	private String project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSslc() {
		return sslc;
	}

	public void setSslc(double sslc) {
		this.sslc = sslc;
	}

	public double getHsc() {
		return hsc;
	}

	public void setHsc(double hsc) {
		this.hsc = hsc;
	}

	public int getCgpa() {
		return cgpa;
	}

	public void setCgpa(int cgpa) {
		this.cgpa = cgpa;
	}

	public String getSkill1() {
		return skill1;
	}

	public void setSkill1(String skill1) {
		this.skill1 = skill1;
	}

	public String getSkill2() {
		return skill2;
	}

	public void setSkill2(String skill2) {
		this.skill2 = skill2;
	}

	public String getSkill3() {
		return skill3;
	}

	public void setSkill3(String skill3) {
		this.skill3 = skill3;
	}

	public String getCertification1() {
		return certification1;
	}

	public void setCertification1(String certification1) {
		this.certification1 = certification1;
	}

	public String getCertification2() {
		return certification2;
	}

	public void setCertification2(String certification2) {
		this.certification2 = certification2;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Resume [id=" + id + ", sslc=" + sslc + ", hsc=" + hsc + ", cgpa=" + cgpa + ", skill1=" + skill1
				+ ", skill2=" + skill2 + ", skill3=" + skill3 + ", certification1=" + certification1
				+ ", certification2=" + certification2 + ", project=" + project + "]";
	}
	
	
	
	
	
	

}
