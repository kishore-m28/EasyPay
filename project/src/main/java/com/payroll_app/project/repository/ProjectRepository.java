package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.enums.ProjectStatus;
import com.payroll_app.project.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

	@Query("select p from EmployeeProject ep JOIN ep.employee e JOIN ep.project p where e.id=?1")
	List<Project> getProjectByEmployeeId(int eid);
	
	@Query("select p from Project p JOIN p.manager m JOIN m.user u where u.username=?1")
	List<Project> getProjectByManagerUsername(String name);


	@Query("select p.projectType, count(e.id)"
			+ " from EmployeeProject ep "
			+ " JOIN ep.employee e "
			+ " JOIN ep.project p "
			+ " group by p.projectType")
	List<Object[]> getEmployeeCountByProjectType();
	
	@Query( "select count(p) from Project p where p.status=?1" )
	int getCountByStatus(ProjectStatus inProgress);

	@Query("select p from Project p where status!='COMPLETED' ")
	Page<Project> findActiveProjects(Pageable pageable);
	
	@Query("select p from Project p where status='COMPLETED' ")
	Page<Project> findCompletedProjects(Pageable pageable);
	
}
