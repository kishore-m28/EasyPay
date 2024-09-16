package com.payroll_app.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.JobSeeker;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {

	@Query("select j from JobSeeker j JOIN j.user u where u.username=?1")
	JobSeeker findByUsername(String jobSeekerUsername);
	
	

}
