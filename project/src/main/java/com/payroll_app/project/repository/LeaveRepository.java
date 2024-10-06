package com.payroll_app.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.LeaveRecord;
public interface LeaveRepository extends JpaRepository<LeaveRecord, Integer>{

	@Query("select l from LeaveRecord l where l.manager.user.username=?1")
	Page<LeaveRecord> getAll(String name, Pageable pageable);
	
}
 
 