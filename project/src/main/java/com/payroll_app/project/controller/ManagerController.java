package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Manager;
import com.payroll_app.project.model.Project;
import com.payroll_app.project.service.EmployeeService;
import com.payroll_app.project.service.ManagerService;


@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	 @Autowired
	    private EmployeeService employeeService;
	
	@GetMapping("/project")
	public List<Project> getProjectByManagerUsername(Principal principal) {
		
		return managerService.getProjectByManagerUsername(principal.getName());
		
	}
	/*
	@GetMapping("/employee")
	public List<Employee> getEmployeeByManagerUsername(Principal principal) {
		
		return managerService.getEmployeeByManagerUsername(principal.getName());
		
	}
	
	@GetMapping("/employee/count")
	public ResponseEntity<?> getCountOfEmployeeByManagerUsername(Principal principal) {
		
        return ResponseEntity.ok("Number of Employees: "+managerService.getCountOfEmployeeByManagerUsername(principal.getName()));
		
	}
	*/
	    @PostMapping("/add")
	    public Manager createManager(@RequestBody Manager manager) {
	        return employeeService.addManager(manager);
	    
	
	
	    }

}
