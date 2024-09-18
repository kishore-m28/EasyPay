package com.payroll_app.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll_app.project.model.ComplianceReport;

public interface ComplianceReportRepository extends JpaRepository<ComplianceReport, Integer>{
	Optional<ComplianceReport> findByComplianceId(int complianceId);
}
