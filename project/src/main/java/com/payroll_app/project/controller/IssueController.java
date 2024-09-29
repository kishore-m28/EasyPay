package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.model.Issue;
import com.payroll_app.project.service.EmployeeService;
import com.payroll_app.project.service.IssueService;

@RestController
@RequestMapping("/issue")
public class IssueController {
	
	@Autowired
	private IssueService issueService;
	
	@Autowired
    private EmployeeService employeeService;

	
	@PutMapping("/track")
	public List<Issue> trackIssue(Principal principal){
		return issueService.trackIssue(principal.getName());
	}
	
	//employee side
	 @PostMapping("/record/add/{mid}")
	    public ResponseEntity<?> addIssue(@RequestBody Issue issue,@PathVariable int mid, Principal principal,MessageDto dto) {
	        String loggedInUsername = principal.getName();

	        try {
	            Issue issues = employeeService.addIssue(issue, loggedInUsername,mid);
	            return ResponseEntity.ok(issues);
	        } catch (InputInvalidException e) {
	            return ResponseEntity.badRequest().body(dto);
	        }
	    }

}
