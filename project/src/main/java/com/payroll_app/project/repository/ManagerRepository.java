package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{

	
	@Query("select e from EmployeeProject ep JOIN ep.employee e "
			+ "JOIN ep.project p JOIN p.manager m "
			+ "JOIN m.user u where u.username=?1")
	List<Employee> getEmployeeByManagerUsername(String username);

	
	@Query("select count(e.id) from EmployeeProject ep join ep.employee e join ep.project p join p.manager m join m.user u where u.username=?1 group by u.username")
	int getCountOfEmployeeByManagerUsername(String name);

	
	
	/*@Query("SELECT e.manager.id FROM Employee e JOIN e.user u where u.username=?1")
	Manager findManagerByEmployeeUsername(String loggedInUsername);*/

  
	
}
