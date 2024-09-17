package com.payroll_app.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.payroll_app.project.model.Employee;
 

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	@Query("select e from Employee e JOIN e.user u where u.username=?1")
	Employee findByUsername(String empUsername);

	@Query("SELECT COUNT(DISTINCT s.employee.id) FROM Salary s WHERE s.grossPay < :amount AND s.id IN (SELECT MAX(s2.id) FROM Salary s2 GROUP BY s2.employee.id)")
    long countEmployeesWithSalaryLessThan(@Param("amount") double amount);

    @Query("SELECT COUNT(DISTINCT s.employee.id) FROM Salary s WHERE s.grossPay > :amount AND s.id IN (SELECT MAX(s2.id) FROM Salary s2 GROUP BY s2.employee.id)")
    long countEmployeesWithSalaryGreaterThan(@Param("amount") double amount);
 
}
