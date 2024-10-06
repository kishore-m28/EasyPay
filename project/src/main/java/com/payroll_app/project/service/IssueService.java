package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Issue;
import com.payroll_app.project.repository.IssueRepository;

@Service
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;

	public Page<Issue> getAll(String name, Pageable pageable) {
		return issueRepository.getAll(name, pageable);
	}

	public Issue getById(int iid) throws InvalidIdException {
		Optional<Issue> optional = issueRepository.findById(iid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Issue ID Invalid");
		}
		return optional.get();
	}

	public Issue replyToIssue(int iid, Issue issue) throws InvalidIdException {
		Optional<Issue> optional = issueRepository.findById(iid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Issue ID Invalid");
		}
		Issue newIssue = optional.get();
		newIssue.setReplyMessage(issue.getReplyMessage());
		return issueRepository.save(newIssue);		
	}

	public void validate(Issue issue) throws InputInvalidException {
		if(issue.getReplyMessage()==null || issue.getReplyMessage().equals("")) {
			throw new InputInvalidException("Reply message be null/blank");
		}
		
	}
	
	

}
