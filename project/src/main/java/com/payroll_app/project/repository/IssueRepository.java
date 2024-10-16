package com.payroll_app.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer>{

	@Query("select i from Issue i where i.manager.user.username=?1")
    Page<Issue> getAll(String name, Pageable pageable);

	@Query("select i from Issue i where i.employee.id=?1")
	Issue getIssueByEmpId(int eId);

}
