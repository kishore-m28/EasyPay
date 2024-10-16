package com.payroll_app.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payroll_app.project.dto.ProjectEmployeeStatDto;
import com.payroll_app.project.enums.ProjectStatus;
import com.payroll_app.project.enums.ProjectType;
import com.payroll_app.project.exception.InputValidationException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.NoEmployeesFoundException;
import com.payroll_app.project.model.Manager;
import com.payroll_app.project.model.Project;
import com.payroll_app.project.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ManagerService managerService;
	
	public Project addProject(int managerId, Project project) throws InputValidationException {
		 //fetch manager on the basis of managerId
		Manager manager =  managerService.getById(managerId);
		//attach manager to project
		project.setManager(manager);
		//save project
		project.setStatus(ProjectStatus.UPCOMING);
		return projectRepository.save(project); 
	}
	
	public Project getProjectById(int pid) throws InvalidIdException {
		Optional<Project> optional =   projectRepository.findById(pid);
		if(optional.isEmpty())
			throw new InvalidIdException("Project ID Invalid..");

		return optional.get();
	}
	
	public Page<Project> getCompletedProjects(Pageable pageable) throws NoEmployeesFoundException {
		Page<Project> projects = projectRepository.findCompletedProjects(pageable);
		if (projects.isEmpty()) {
			throw new NoEmployeesFoundException("No employees found.");
		}
		return projects;
	}
	
	public Page<Project> getActiveProjects(Pageable pageable) throws NoEmployeesFoundException {
		Page<Project> projects = projectRepository.findActiveProjects(pageable);
		if (projects.isEmpty()) {
			throw new NoEmployeesFoundException("No employees found.");
		}
		return projects;
	}
	
	public List<Project> getProjectByEmployeeId(int eid)  {
		
		return projectRepository.getProjectByEmployeeId(eid);
	}

	public void markAsCompleted(int pid) throws InvalidIdException {
		Optional<Project> optional = projectRepository.findById(pid);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Project Id");
		Project projectDB = optional.get();
		projectDB.setCompletedDate(LocalDate.now());
		projectDB.setStatus(ProjectStatus.COMPLETED);
		projectRepository.save(projectDB);
	}

	public List<ProjectType> getProjectType() {	
		return List.of(ProjectType.values()) ;
	}

	public int getActiveProjectCount() {
		return projectRepository.getCountByStatus(ProjectStatus.IN_PROGRESS);
	}

	public int getCompletedProjectCount() {
		return projectRepository.getCountByStatus(ProjectStatus.COMPLETED);
	}

	public int getUpcomingProjectCount() {
		return projectRepository.getCountByStatus(ProjectStatus.UPCOMING);
	}

	public int getOverdueProjectCount() {
		return projectRepository.getCountByStatus(ProjectStatus.PENDING);
	}

	public void updateProjectStatus(int id, String newStatus) throws InvalidIdException {
		Optional<Project> optional = projectRepository.findById(id);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Project Id");
		
		Project project = optional.get();
		project.setStatus(ProjectStatus.valueOf(newStatus));
		projectRepository.save(project);
		
	}

	
	
	public List<ProjectEmployeeStatDto> getEmployeeCountByProjectType() {
		List<Object[]> list =  projectRepository.getEmployeeCountByProjectType();
 		List<ProjectEmployeeStatDto> listDto = new ArrayList<>();
		for(Object[] obj : list) {
			String str = obj[0].toString();
			long numberOfEmployee = (Long)obj[1];
			ProjectEmployeeStatDto dto = new ProjectEmployeeStatDto(str, numberOfEmployee);
			listDto.add(dto);
		}
		return listDto;
	}
}
