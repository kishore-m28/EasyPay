package com.payroll_app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll_app.project.model.Work;

public interface WorkRepository extends JpaRepository<Work, Integer>{

}
