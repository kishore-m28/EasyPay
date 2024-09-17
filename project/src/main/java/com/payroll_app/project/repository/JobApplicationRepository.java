package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
	@Query("SELECT j.experienceRequired FROM JobApplication ja JOIN ja.job j WHERE ja.id = ?1")
	int getExperience(int appId);

	 @Query("SELECT js.experience FROM JobApplication ja JOIN ja.jobSeeker js WHERE ja.id = ?1")
	 
	int getExperienceJobseeker(int appId);

	/* @Query("SELECT r.skill1, r.skill2, r.skill3, j.skill1, j.skill2, j.skill3 " +
	           "FROM JobApplication ja " +
	           "JOIN ja.jobSeeker js " +
	           "JOIN js.resume r " +
	           "JOIN ja.job j " +
	           "WHERE ja.id = ?1")
	List<Object[]> findSkillsByJobApplicationId(int appId);
	*/
	 
	 @Query("select ja.status from JobApplication ja where ja.jobSeeker.id=?1") 
	 String findStatus(int jid);
	 
}
