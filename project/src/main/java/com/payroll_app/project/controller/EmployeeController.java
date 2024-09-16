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

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee){
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping("/salary")
	public ResponseEntity<?> getSalaryByEmployee(Principal principal) {
		String empUsername=principal.getName();
		try {
			List<Salary> list= employeeService.getSalaryByEmployee(empUsername);
			return ResponseEntity.ok(list);
		} catch (InvalidIdException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
