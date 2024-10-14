package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.HRInterview;

public interface HRInterviewRepository extends JpaRepository<HRInterview, Integer>{

	@Query("select hi from HRInterview hi where hi.hr.user.username=?1")
	List<HRInterview> getAll(String name);

}

