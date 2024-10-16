package com.payroll_app.project.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	private Logger logger = LoggerFactory.getLogger(IssueController.class);
	
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
	
	@PostMapping("/reply/{iid}")
	public ResponseEntity<?> replyToIssue(@PathVariable int iid, @RequestBody Issue issue, MessageDto dto) {
		try {
			issueService.validate(issue);
			issue = issueService.replyToIssue(iid,issue);
			return ResponseEntity.ok(issue);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		} catch (InputInvalidException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.status(e.getStatusCode()).body(dto);
		}
	}
	
	@GetMapping("/all")
	public Page<Issue> getAll(Principal principal,
			@RequestParam(defaultValue ="0", required=false) Integer page,
			@RequestParam(defaultValue = "1000", required=false) Integer size){
		
		Pageable pageable = PageRequest.of(page, size);
		logger.info("Fetching all issues");
		return issueService.getAll(principal.getName(), pageable);
	}
	
	@GetMapping("/{iid}")
	public ResponseEntity<?> getById(@PathVariable int iid, MessageDto dto) {
		try {
			Issue issue = issueService.getById(iid);
			logger.info("Displaying issue with id:"+iid);
			return ResponseEntity.ok(issue);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			logger.error("Could not find issue with id:"+iid);
			return ResponseEntity.badRequest().body(dto);			
		}
	}

}
