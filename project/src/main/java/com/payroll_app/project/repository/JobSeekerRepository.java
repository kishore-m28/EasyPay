package com.payroll_app.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.JobSeeker;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {

	@Query("select j from JobSeeker j JOIN j.user u where u.username=?1")
	JobSeeker findByUsername(String jobSeekerUsername);

	@Query("select t.status from TechnicalScoreSheet t JOIN t.jobSeeker j where j.id=?1")
	String getStatusOfTechnicalInterview(int jobSeekerId);

	@Query("select h.status from HRScoreSheet h JOIN h.jobSeeker j where j.id=?1")
	String getStatusOfHrInterview(int jobSeekerId);
	
	

}
