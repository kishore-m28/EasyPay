package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.AccountDetails;
import com.payroll_app.project.model.Address;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.service.AccountDetailsService;
import com.payroll_app.project.service.AddressService;
import com.payroll_app.project.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AccountDetailsService accountDetailsService;
	
	@Autowired
	private AddressService addressService;
	
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
	
	@GetMapping("/one/{eid}")
	public ResponseEntity<?> getById(@PathVariable	int eid, MessageDto dto){
		try {
			Employee employee =  employeeService.getById(eid);
			return ResponseEntity.ok(employee);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
 			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PostMapping("/add-account-details/{eid}")
	public ResponseEntity<?> addAccountDetails(@PathVariable int eid, @RequestBody AccountDetails accountDetails, MessageDto dto) {
		try {
			accountDetails =  accountDetailsService.addAccountDetails(eid, accountDetails);
			return ResponseEntity.ok(accountDetails);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
 			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PostMapping("/offboard")
	public void offboardEmployee(@RequestBody Employee employee) {
		
	}
	
	@PostMapping("/add-address/{eid}")
	public ResponseEntity<?> addAccountDetails(@PathVariable int eid, @RequestBody Address address, MessageDto dto) {
		try {
			address =  addressService.addAddress(eid, address);
			return ResponseEntity.ok(address);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
 			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees(MessageDto dto) {
	    try {
	        List<Employee> employees = employeeService.getAllEmployees();
	        return ResponseEntity.ok(employees);
	    } catch (Exception e) { 
	        dto.setMsg(e.getMessage());
	        return ResponseEntity.badRequest().body(dto);
	    }
	}

	@GetMapping("/salary/below-average")
    public ResponseEntity<Long> getEmployeesBelowAverageSalary() {
        long count = employeeService.countEmployeesBelowAverageSalary();
        return ResponseEntity.ok(count);
    }
	
	@GetMapping("/salary/above-average")
    public ResponseEntity<Long> getEmployeesAboveAverageSalary() {
        long count = employeeService.countEmployeesAboveAverageSalary();
        return ResponseEntity.ok(count);
    }

}
