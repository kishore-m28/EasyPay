package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.model.Issue;
import com.payroll_app.project.service.IssueService;

@RestController
@RequestMapping("/issue")
public class IssueController {
	
	@Autowired
	private IssueService issueService;
	
	@PutMapping("/track")
	public List<Issue> trackIssue(Principal principal){
		return issueService.trackIssue(principal.getName());
	}

}
