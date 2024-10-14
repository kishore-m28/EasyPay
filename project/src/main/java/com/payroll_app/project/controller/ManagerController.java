package com.payroll_app.project.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.EmployeeGenderCountDto;
import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.EmployeeProject;
import com.payroll_app.project.model.Project;
import com.payroll_app.project.service.EmployeeProjectService;
import com.payroll_app.project.service.EmployeeService;
import com.payroll_app.project.service.LeaveService;
import com.payroll_app.project.service.ManagerService;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeProjectService employeeProjectService;
	
	@Autowired
	private LeaveService leaveService;
	

	@GetMapping("/project")
	public List<Project> getProjectByManagerUsername(Principal principal) 
	{

		return managerService.getProjectByManagerUsername(principal.getName());

	}

	@GetMapping("/employee")
	public List<Employee> getEmployeeByManagerUsername(Principal principal) {

		return managerService.getEmployeeByManagerUsername(principal.getName());

	}

	@GetMapping("/employee/count")
	public ResponseEntity<Integer> getCountOfEmployeeByManagerUsername(Principal principal) {
		int count = managerService.getCountOfEmployeeByManagerUsername(principal.getName());
		return ResponseEntity.ok(count);
	}
	
	@GetMapping("/employee/{eid}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int eid, MessageDto dto){
		try {
			Employee employee = employeeService.getEmployeeById(eid);
			return ResponseEntity.ok(employee);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/employee/project/{eid}")
	public ResponseEntity<?> getEmployeeProjectByEmployeeId(@PathVariable int eid, MessageDto dto) {
		try {
			EmployeeProject employeeProject = employeeProjectService.getEmployeeProjectByEmployeeId(eid);
			return ResponseEntity.ok(employeeProject);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/leave/requests")
	public ResponseEntity<Integer> getCountOfLeaveRequests(Principal principal){
		Integer leaveCount = leaveService.getCountOfLeaveRequests(principal.getName(), LocalDate.now());
		int count = leaveCount != null ? leaveCount : 0;
		return ResponseEntity.ok(count);
	}
	
	

	/*@PostMapping("/add")
	public Manager createManager(@RequestBody Manager manager) {
		return employeeService.addManager(manager);

	}*/


}
