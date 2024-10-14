package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

	@Query("select p from EmployeeProject ep JOIN ep.employee e JOIN ep.project p where e.id=?1")
	List<Project> getProjectByEmployeeId(int eid);
	
	@Query("select p from Project p JOIN p.manager m JOIN m.user u where u.username=?1")
	List<Project> getProjectByManagerUsername(String name);


	@Query("select p.projectType, count(e.id)"
			+ " from EmployeeProject ep "
			+ " JOIN ep.employee e "
			+ " JOIN ep.project p JOIN p.manager m WHERE m.user.username=?1"
			+ " group by p.projectType")
	List<Object[]> getEmployeeCountByProjectType(String username);

	
}
