package com.payroll_app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payroll_app.project.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{

}
