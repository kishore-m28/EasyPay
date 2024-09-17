package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer>{

	@Query("select i from Issue i JOIN i.manager m JOIN m.user u WHERE u.username=?1 and i.status='PENDING'")
	List<Issue> getIssues(String username);

}
