package com.payroll_app.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.EmployeeProject;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Integer>{

	@Query("select ep from EmployeeProject ep join ep.employee e where e.id=?1")
	Optional<EmployeeProject> findEmployeeProject(int eid);

}
