package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.TechnicalInterview;

public interface TechnicalInterviewRepository extends JpaRepository<TechnicalInterview, Integer>{

	@Query("select ti from TechnicalInterview ti where ti.manager.user.username=?1")
	List<TechnicalInterview> findAll(String name);

	@Query("select tr from TechnicalInterview tr join tr.jobApplication ja join ja.jobSeeker js where js.id=?1")
	TechnicalInterview getTechInterview(int jobSeekerId);

}
