package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.LeaveRecord;
public interface LeaveRepository extends JpaRepository<LeaveRecord, Integer>{


	@Query("select l from LeaveRequest l JOIN l.manager m JOIN m.user u where u.username=?1 and l.status='PENDING'")
	List<LeaveRecord> getLeaveRequests(String username);
	
}
 
