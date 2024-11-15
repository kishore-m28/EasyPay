package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.EmployeeFilterDto;
import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.dto.ReviewPayrollDisplay;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.SalaryNotExists;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.service.SalaryService;

@RestController
@RequestMapping("/salary")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SalaryController {
	
	@Autowired
	private SalaryService salaryService;
	
	@PostMapping("/compute/{empId}")  
	public ResponseEntity<?> computeSalaryForEmployee(@PathVariable int empId,MessageDto dto){
		try {
			Salary sal=salaryService.computeSalaryForEmployee(empId);
			return ResponseEntity.ok(sal);
		} catch (InvalidIdException | SalaryNotExists e) {
			 return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PostMapping("/set/{eid}")
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
	
	@PostMapping("/status/pending/{id}")
	public void salaryStatusToPending(@PathVariable int id){
		salaryService.salaryStatusToPending(id);	
	}

	@PostMapping("/process/inBatch")
	public ResponseEntity<?> processPayroll(@RequestBody List<Integer> eid,MessageDto dto) {
			try {
				List<Salary> salary=salaryService.processPayroll(eid);
				return ResponseEntity.ok(salary);
			} catch (InvalidIdException e) {
				dto.setMsg(e.getMessage());
				return ResponseEntity.badRequest().body(dto);
			}
	}
	
	@PostMapping("/filter/display")
	public List<ReviewPayrollDisplay> reviewPayroll(@RequestBody EmployeeFilterDto empFilter) {
          return salaryService.getSalaryByFilter(empFilter);
	}
	
	@GetMapping("/payslip")
	public Salary getPaySlip(Principal principal) {
		return salaryService.getPaySlip(principal.getName());
	}
}
