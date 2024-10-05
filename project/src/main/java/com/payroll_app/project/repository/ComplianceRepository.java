package com.payroll_app.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll_app.project.exception.InvalidComplianceTypeException;
import com.payroll_app.project.model.Compliance;

public interface ComplianceRepository extends JpaRepository<Compliance, Integer>{

	Optional<Compliance> findByComplianceType(String complianceType) throws InvalidComplianceTypeException;
}
