package com.payroll_app.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.enums.Status;
import com.payroll_app.project.model.Issue;
import com.payroll_app.project.repository.IssueRepository;

@Service
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;
	
	public List<Issue> trackIssue(String username) {
		List<Issue> list = issueRepository.getIssues(username);
		
		for(Issue i:list) {
			i.setStatus(Status.NOTED);
			i = issueRepository.save(i);
		}
		
		return list;
	}
	

}
