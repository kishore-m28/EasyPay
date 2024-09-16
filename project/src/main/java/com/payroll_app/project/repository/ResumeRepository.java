package com.payroll_app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll_app.project.model.Resume;


public interface ResumeRepository extends JpaRepository<Resume, Integer> {

}
