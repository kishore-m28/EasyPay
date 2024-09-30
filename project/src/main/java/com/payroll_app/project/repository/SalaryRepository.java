package com.payroll_app.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.payroll_app.project.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
	
	@Query("select s from Salary s JOIN s.employee e where e.id=?1")
	List<Salary> getSalaryByEmployeeId(int id);
	
Optional<Salary> findByEmployeeId(int employeeId);
	
	@Query("SELECT AVG(s.grossPay) FROM Salary s WHERE s.id IN (SELECT MAX(s2.id) FROM Salary s2 GROUP BY s2.employee.id)")
	Double findAverageSalary();
	
	@Query("SELECT s FROM Salary s WHERE s.employee.id = :employeeId ORDER BY s.createdAt DESC") //this will still return 2 records if the created_at date is same 
    Salary findTopByEmployeeIdOrderByCreatedAtDesc(@Param("employeeId") int employeeId);

	List<Salary> findByEmployeeIdIn(List<Integer> employeeIds);

}
