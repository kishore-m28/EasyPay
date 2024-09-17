package com.payroll_app.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Salary;
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
	
	@PostMapping("/employee/salary/set/{eid}")
	public ResponseEntity<?> setSalary(@PathVariable int eid, @RequestBody Salary salary, MessageDto dto) {
		try {
			salary = salaryService.setSalary(eid, salary);
			return ResponseEntity.ok(salary);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PostMapping("/employee/salary/update/{eid}")
	public ResponseEntity<?> updateSalary(@PathVariable int eid, @RequestBody Salary salary, MessageDto dto) {
		try {
			salary = salaryService.updateSalary(eid, salary);
			return ResponseEntity.ok(salary);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	
	@GetMapping("/salary/avg")
	public double avgSalary() {
		return salaryService.avgSalary();
	}

}
