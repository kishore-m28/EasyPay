package com.payroll_app.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Project;
import com.payroll_app.project.repository.ManagerRepository;
import com.payroll_app.project.repository.ProjectRepository;



@Service
public class ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	

	public List<Project> getProjectByManagerUsername(String name) {
		return projectRepository.getProjectByManagerUsername(name);
	}

	public List<Employee> getEmployeeByManagerUsername(String name) {
		return managerRepository.getEmployeeByManagerUsername(name);
	}

	public int getCountOfEmployeeByManagerUsername(String name) {
		return managerRepository.getCountOfEmployeeByManagerUsername(name);
	}
	

}
