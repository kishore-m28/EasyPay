package com.payroll_app.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.EmployeeProject;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Integer>{

	@Query("select ep from EmployeeProject ep join ep.employee e where e.id=?1")
	Optional<EmployeeProject> findEmployeeProject(int eid);

	@Query("select e from EmployeeProject ep JOIN ep.employee e "
			+ "JOIN ep.project p JOIN p.manager m "
			+ "JOIN m.user u where u.username=?1")
	List<Employee> getEmployeeByManagerUsername(String mUsername);
	
	@Query("select e from EmployeeProject ep join ep.employee e join ep.project p where p.name=?1")
	List<Employee> findEmployeesByProjectTitle(String title);

}
