package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InputValidationException;
import com.payroll_app.project.exception.InvalidIdException;
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
		return projectRepository.save(project); 


	}
	
	public Project getProjectById(int pid) throws InvalidIdException {
		Optional<Project> optional =   projectRepository.findById(pid);
		if(optional.isEmpty())
			throw new InvalidIdException("Project ID Invalid..");

		return optional.get();
	}
	
	/*
	public List<Project> getProjectByEmployeeId(int eid)  {
		
		return projectRepository.getProjectByEmployeeId(eid);
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
	}*/

}
