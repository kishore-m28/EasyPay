package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
	
	@Query("select s from Salary s JOIN s.employee e where e.id=?1")
	List<Salary> getSalaryByEmployeeId(int id);

}
