package com.payroll_app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.TechnicalScoreSheet;

public interface TechnicalScoreSheetRepository extends JpaRepository<TechnicalScoreSheet, Integer>{

	@Query("select ts.isSelectedForHR from TechnicalScoreSheet ts where ts.jobApplication.id=?1")
	boolean isCleared(int id);

	@Query("select t from TechnicalScoreSheet t join t.jobApplication ja join ja.job j join ja.jobSeeker js where js.id=?1")
	TechnicalScoreSheet technicalRoundStatus(int jobSeekerId);

}
