package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.payroll_app.project.model.HRScoreSheet;
import com.payroll_app.project.model.Job;


public interface HRScoreSheetRepository extends JpaRepository<HRScoreSheet, Integer>{

	@Query("select j from HRScoreSheet h join h.jobApplication ja join ja.job j join ja.jobSeeker js where js.id=?1")
	List<Job> findJobOfferOfJobSeeker(int jobSeekerId);

	@Query("select h from HRScoreSheet h join h.jobApplication ja join ja.job j join ja.jobSeeker js where js.id=?1")
	HRScoreSheet hrRoundStatus(int jobSeekerId);

	/*@Query("SELECT ja.job.jobTitle " +
	           "FROM HRScoreSheet hss " +
	           "JOIN hss.jobSeeker js " +
	           "JOIN js.jobApplication ja " +
	           "WHERE hss.id = :hrScoreSheetId " +
	           "AND hss.isSelectedForOffer = true")
	    String findJobTitleByHRScoreSheetId(@Param("hrScoreSheetId") int hrScoreSheetId);*/
	
	/*@Query("SELECT ja.job.jobTitle " +
		       "FROM HRScoreSheet hss " +
		       "JOIN hss.jobSeeker js " +
		       "JOIN JobApplication ja ON ja.jobSeeker = js " +
		       "WHERE hss.id = :hrScoreSheetId " +
		       "AND hss.isSelectedForOffer = true")
		String findJobTitleByHRScoreSheetId(@Param("hrScoreSheetId") int hrScoreSheetId);*/

}
