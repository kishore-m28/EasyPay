package com.payroll_app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll_app.project.model.LeaveRecord;
public interface LeaveRepository extends JpaRepository<LeaveRecord, Integer>{
	
}
 
