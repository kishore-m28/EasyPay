package com.payroll_app.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Employee;
 

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	@Query("select e from Employee e JOIN e.user u where u.username=?1")
	Employee findByUsername(String empUsername);

 
}
