package com.payroll_app.project.repository;


import java.util.List;

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
    
    @Query("select e from Employee e JOIN e.user u where u.username=?1")
	Employee getEmployee(String loggedInUsername);

	@Query("SELECT e.name, e.email, s.basicPay, s.bonus, s.da, s.hra, s.ma, s.month, s.netPay, s.overTimePay, s.taxDeduction, s.year " +
		       "FROM Salary s JOIN s.employee e JOIN e.user u WHERE u.username = ?1")
		List<Object[]> getEmployeeAndSalaryByUsername(String loggedInUsername);

	/*
	@Query("SELECT e.name, e.email, s.basicPay, s.bonus, s.da, s.hra, s.ma, s.month, s.netPay, s.overTimePay, s.taxDeduction, s.year " +
		       "FROM Salary s JOIN s.employee e WHERE e.id = ?1")
	List<Object[]> getEmployeeAndSalaryByUsername(String loggedInUsername);
*/
	


 
}
