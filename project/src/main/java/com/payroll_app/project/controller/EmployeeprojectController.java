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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.AssignEmployeeDto;
import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.EmployeeProject;
import com.payroll_app.project.service.EmployeeProjectService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class EmployeeprojectController {

	@Autowired
	private EmployeeProjectService employeeProjectService;

	@PostMapping("/employee/project/add/{eid}/{pid}")
	public ResponseEntity<?> assignProjectToEmployee(@PathVariable int eid, 
										@PathVariable int pid, 
										@RequestBody EmployeeProject employeeProject,
										MessageDto dto) {
		try {
			employeeProject = employeeProjectService.assignProjectToEmployee(eid,pid,employeeProject);
			return ResponseEntity.ok(employeeProject); 
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto); 
		}
	}
	
	@PostMapping("/project/assign-employees")
	public ResponseEntity<?> assignEmployees(@RequestBody AssignEmployeeDto assignEmployeeDto,MessageDto dto){
		try {
			return ResponseEntity.ok(employeeProjectService.assignEmployees(assignEmployeeDto));
		} catch (InvalidIdException e) {
			dto.setMsg("Failed to assign");
			return ResponseEntity.badRequest().body(dto);
		}	
	}
	
	@GetMapping("project/employees")
	public List<Employee> getEmployeeByManagerUsername(@RequestParam String username) {

		return employeeProjectService.getEmployeeByManagerUsername(username);

	}
	
	@GetMapping("/employees-by-title")
    public ResponseEntity<?> getEmployeesByProjectTitle(@RequestParam String title) {
        List<Employee> employees = employeeProjectService.getEmployeesByProjectTitle(title);
        return ResponseEntity.ok(employees);
    }
}
