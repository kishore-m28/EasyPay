package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer>{

	@Query("select i from Issue i where i.manager.user.username=?1")
	List<Issue> getAll(String name);

}
