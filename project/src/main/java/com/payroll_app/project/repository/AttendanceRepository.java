package com.payroll_app.project.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	@Query("select count(a.employee.id) from Attendance a where a.manager.user.username=?1 and a.attendanceDate=?2")
	Integer getPresentCount(String name, LocalDate today);

	@Query("SELECT COUNT(e.id) FROM EmployeeProject ep JOIN ep.employee e JOIN ep.project p JOIN p.manager m  WHERE m.user.username = ?1 AND e.id NOT IN (SELECT a.employee.id FROM Attendance a WHERE a.manager.user.username = ?1 AND a.attendanceDate = ?2)")
	Integer getAbsentCount(String name, LocalDate today);

	
}
