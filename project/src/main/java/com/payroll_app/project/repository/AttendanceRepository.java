package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Attendance;
import com.payroll_app.project.model.Employee;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	@Query("select a.employee e from Attendance a where a.manager.user.username=?1")
	List<Employee> getEmployeesPresent(String managerUsername);

	@Query("select a.employee e from Attendance a where a.manager.user.username=?1")
	List<Employee> getEmployeesAbsent(String managerUsername);

}
