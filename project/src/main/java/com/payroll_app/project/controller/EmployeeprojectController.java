package com.payroll_app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.EmployeeProject;
import com.payroll_app.project.service.EmployeeProjectService;

@RestController
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
}
