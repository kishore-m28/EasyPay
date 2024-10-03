package com.payroll_app.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.enums.Status;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Issue;
import com.payroll_app.project.repository.IssueRepository;

@Service
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;

	public void trackIssue(int iid) throws InvalidIdException {
		Optional<Issue> optional = issueRepository.findById(iid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Issue ID invalid");
		}
		Issue issue = optional.get();
		issue.setStatus(Status.NOTED);
		issueRepository.save(issue);	
	}

	public List<Issue> getAll(String name) {
		return issueRepository.getAll(name);
	}
	
	

}
