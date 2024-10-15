package com.payroll_app.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.payroll_app.project.dto.AssignEmployeeDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.EmployeeProject;
import com.payroll_app.project.model.Project;
import com.payroll_app.project.repository.EmployeeProjectRepository;
import com.payroll_app.project.repository.EmployeeRepository;
import com.payroll_app.project.repository.ProjectRepository;

@Service
public class EmployeeProjectService {

	@Autowired
	private EmployeeProjectRepository employeeProjectRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ProjectService projectService;

	public EmployeeProject assignProjectToEmployee(int eid, int pid, EmployeeProject employeeProject) 
			throws InvalidIdException {
		Employee employee = employeeService.getById(eid);

		Project project = projectService.getProjectById(pid);

		employeeProject.setEmployee(employee);
		employeeProject.setProject(project);

		return employeeProjectRepository.save(employeeProject);
	}

	public EmployeeProject getEmployeeProjectByEmployeeId(int eid) throws InvalidIdException {
		Optional<EmployeeProject> optional = employeeProjectRepository.findEmployeeProject(eid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Invalid Employee ID");
		}
		return optional.get();		
	}

	public List<EmployeeProject> assignEmployees(AssignEmployeeDto assignEmployeeDto) throws InvalidIdException {
		List<EmployeeProject> employeeProjectList = new ArrayList<>();
		
		//fetch project object by pid
		Optional<Project> opt = projectRepository.findById(assignEmployeeDto.getProjectId());
		if(opt.isEmpty())
			throw new InvalidIdException("Invalid Employee Id");
		Project project = opt.get();
		
		for(int employeeId : assignEmployeeDto.getEmployeeIds()) {
			EmployeeProject employeeProject = new EmployeeProject();
			//fetch employee object by eid
			Optional<Employee> optional = employeeRepository.findById(employeeId);
			if(optional.isEmpty())
				throw new InvalidIdException("Invalid Employee Id");
			Employee employee = optional.get();
			
			employeeProject.setEmployee(employee);
			
			employeeProject.setProject(project);
			employeeProject.setAssignedDate(LocalDate.now());
			
			employeeProjectList.add(employeeProject);
		}
		employeeProjectRepository.saveAll(employeeProjectList);
		return employeeProjectList;
	}

	public List<Employee> getEmployeeByManagerUsername(String mUsername) {
		return employeeProjectRepository.getEmployeeByManagerUsername(mUsername);
	}

	public List<Employee> getEmployeesByProjectTitle(String title) {
		return employeeProjectRepository.findEmployeesByProjectTitle(title);
	}
}
