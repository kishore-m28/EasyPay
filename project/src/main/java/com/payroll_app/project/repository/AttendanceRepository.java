package com.payroll_app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll_app.project.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

}
