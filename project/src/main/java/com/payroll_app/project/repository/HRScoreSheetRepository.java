package com.payroll_app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.payroll_app.project.model.HRScoreSheet;


public interface HRScoreSheetRepository extends JpaRepository<HRScoreSheet, Integer>{

	/*@Query("SELECT ja.job.jobTitle " +
	           "FROM HRScoreSheet hss " +
	           "JOIN hss.jobSeeker js " +
	           "JOIN js.jobApplication ja " +
	           "WHERE hss.id = :hrScoreSheetId " +
	           "AND hss.isSelectedForOffer = true")
	    String findJobTitleByHRScoreSheetId(@Param("hrScoreSheetId") int hrScoreSheetId);*/
	
	@Query("SELECT ja.job.jobTitle " +
		       "FROM HRScoreSheet hss " +
		       "JOIN hss.jobSeeker js " +
		       "JOIN JobApplication ja ON ja.jobSeeker = js " +
		       "WHERE hss.id = :hrScoreSheetId " +
		       "AND hss.isSelectedForOffer = true")
		String findJobTitleByHRScoreSheetId(@Param("hrScoreSheetId") int hrScoreSheetId);

}
