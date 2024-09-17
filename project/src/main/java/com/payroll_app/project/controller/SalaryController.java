package com.payroll_app.project.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.service.SalaryService;

@RestController
@RequestMapping("/salary")
public class SalaryController {
	
	private SalaryService salaryService;
	
	@PostMapping("/compute/{eid}") /*currently working*/
	public void computeSalaryForEmployee(@PathVariable int empId){
		try {
			salaryService.computeSalaryForEmployee(empId);
		} catch (InvalidIdException e) {
			 
		}
		
	}

}
