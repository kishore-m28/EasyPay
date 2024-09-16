package com.payroll_app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll_app.project.model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

}
