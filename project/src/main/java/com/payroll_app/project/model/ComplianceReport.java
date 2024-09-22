package com.payroll_app.project.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "compliance_report")
public class ComplianceReport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private LocalDate reportDate;
	
	@ManyToOne
	private Compliance compliance;
	
	@Column(name = "overall_compliance_level")
    private double overallComplianceLevel;

    @Column(name = "non_compliance_count")
    private int nonComplianceCount;

    @Column(name = "compliance_count")
    private int complianceCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getReportDate() {
		return reportDate;
	}

	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}

	public Compliance getCompliance() {
		return compliance;
	}

	public void setCompliance(Compliance compliance) {
		this.compliance = compliance;
	}

	public double getOverallComplianceLevel() {
		return overallComplianceLevel;
	}

	public void setOverallComplianceLevel(double overallComplianceLevel) {
		this.overallComplianceLevel = overallComplianceLevel;
	}

	public int getNonComplianceCount() {
		return nonComplianceCount;
	}

	public void setNonComplianceCount(int nonComplianceCount) {
		this.nonComplianceCount = nonComplianceCount;
	}

	public int getComplianceCount() {
		return complianceCount;
	}

	public void setComplianceCount(int complianceCount) {
		this.complianceCount = complianceCount;
	}
    
    
    
    
    
    
    
}
