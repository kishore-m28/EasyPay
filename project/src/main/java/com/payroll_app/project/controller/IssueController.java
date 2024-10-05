package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto; 
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Issue;
import com.payroll_app.project.service.EmployeeService;
import com.payroll_app.project.service.IssueService;

@RestController
@RequestMapping("/issue")
@CrossOrigin(origins = {"http://localhost:4200"})
public class IssueController {

	@Autowired
	private IssueService issueService;

	@Autowired
	private EmployeeService employeeService;

	// employee side
	@PostMapping("/record/add/{mid}")
	public ResponseEntity<?> addIssue(@RequestBody Issue issue, @PathVariable int mid, Principal principal,
			MessageDto dto) {
		String loggedInUsername = principal.getName();

		try {
			Issue issues = employeeService.addIssue(issue, loggedInUsername, mid);
			return ResponseEntity.ok(issues);
		} catch (InputInvalidException e) {
			return ResponseEntity.badRequest().body(dto);
		}
	}

	@PutMapping("/track/{iid}")
	public ResponseEntity<?> trackIssue(@PathVariable int iid, MessageDto dto) {
		try {
			issueService.trackIssue(iid);
			dto.setMsg("Issue Noted");
			return ResponseEntity.ok(dto);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/all")
	public List<Issue> getAll(Principal principal){
		return issueService.getAll(principal.getName());
	}

}
