package com.payroll_app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Job;
import com.payroll_app.project.model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
	@Query("SELECT j.experienceRequired FROM JobApplication ja JOIN ja.job j WHERE ja.id = ?1")
	int getExperience(int appId);

	@Query("SELECT js.experience FROM JobApplication ja JOIN ja.jobSeeker js WHERE ja.id = ?1")
	int getExperienceJobseeker(int appId);

	

	@Query("select ja.status from JobApplication ja where ja.id=?1")
	String findStatus(int aid);

	@Query("select j from JobApplication ja JOIN ja.jobSeeker js JOIN ja.job j where js.id=?1 ")
	List<Job> listOfAppliedJobs(int jobSeekerId);

	@Query("select j from JobApplication ja JOIN ja.jobSeeker js JOIN ja.job j where js.id=?1 ")
	List<Object[]> listOfAppliedJobsV2(int jobSeekerId);

	@Query("SELECT COUNT(j), COUNT(ja), COUNT(js), j.jobTitle " + "FROM JobApplication ja " + "JOIN ja.job j "
			+ "JOIN ja.jobSeeker js " + "GROUP BY j.jobTitle")
	List<Object[]> getJobApplicationStats();

	@Query("SELECT r.skill1, r.skill2, r.skill3 " + "FROM JobApplication ja " + "JOIN ja.jobSeeker js "
			+ "JOIN js.resume r " + "WHERE ja.id = ?1")
	List<Object[]> findResumeSkills(int appId);

	@Query("SELECT j.skill1, j.skill2, j.skill3 " + "FROM JobApplication ja " + "JOIN ja.job j " + "WHERE ja.id = ?1")
	List<Object[]> findJobSkills(int appId);
	
}

/*
 * @Query("SELECT r.skill1, r.skill2, r.skill3, j.skill1, j.skill2, j.skill3 " +
 * "FROM JobApplication ja " + "JOIN ja.jobSeeker js " + "JOIN js.resume r " +
 * "JOIN ja.job j " + "WHERE ja.id = ?1") List<Object[]>
 * findSkillsByJobApplicationId(int appId);
 */
