package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.EmployeeProject;
import com.payroll_app.project.model.Project;
import com.payroll_app.project.repository.EmployeeProjectRepository;

@Service
public class EmployeeProjectService {

	@Autowired
	private EmployeeProjectRepository employeeProjectRepository;

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
}
