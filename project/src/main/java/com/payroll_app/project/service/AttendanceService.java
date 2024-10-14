package com.payroll_app.project.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.repository.AttendanceRepository;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceRepository attendanceRepository;

	public Integer getPresentCount(String name, LocalDate today) {
		return attendanceRepository.getPresentCount(name,today);
	}

	public Integer getAbsentCount(String name, LocalDate today) {
		return attendanceRepository.getAbsentCount(name, today);
	}

	

}
