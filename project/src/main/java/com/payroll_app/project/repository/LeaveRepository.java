package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.LeaveRecord;
public interface LeaveRepository extends JpaRepository<LeaveRecord, Integer>{

	@Query("select l from LeaveRecord l where l.manager.user.username=?1")
	List<LeaveRecord> getAll(String name);
	
}
 
 