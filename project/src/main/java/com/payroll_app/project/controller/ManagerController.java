package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Project;
import com.payroll_app.project.service.ManagerService;


@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	@GetMapping("/project")
	public List<Project> getProjectByManagerUsername(Principal principal) {
		
		return managerService.getProjectByManagerUsername(principal.getName());
		
	}
	
	@GetMapping("/employee")
	public List<Employee> getEmployeeByManagerUsername(Principal principal) {
		
		return managerService.getEmployeeByManagerUsername(principal.getName());
		
	}
	
	@GetMapping("/employee/count")
	public ResponseEntity<?> getCountOfEmployeeByManagerUsername(Principal principal) {
		
        return ResponseEntity.ok("Number of Employees: "+managerService.getCountOfEmployeeByManagerUsername(principal.getName()));
		
	}
	
	
	

}
