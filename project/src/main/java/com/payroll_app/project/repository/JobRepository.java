package com.payroll_app.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payroll_app.project.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{

	@Query("select j from Job j where jobTitle=?1 and startDate=?2 and location=?3 and ctc=?4")
	List<Job> searchJobAll(String jobTitle, LocalDate startDate, String location,double ctc);

	@Query("select j from Job j where location=?1")
	List<Job> searchJobByLocation(String newLocation);

}
