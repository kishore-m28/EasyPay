package com.payroll_app.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.model.Employee;
import com.payroll_app.project.repository.AttendanceRepository;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceRepository attendanceRepository;

	public List<Employee> getEmployeesPresent(String managerUsername) {
		return attendanceRepository.getEmployeesPresent(managerUsername);
	}

	public List<Employee> getEmployeesAbsent(String managerUsername) {
		return attendanceRepository.getEmployeesAbsent(managerUsername);
	}

}
