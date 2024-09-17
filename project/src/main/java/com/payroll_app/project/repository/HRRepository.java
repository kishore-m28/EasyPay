package com.payroll_app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.HR;

public interface HRRepository extends JpaRepository<HR, Integer> {

    @Query("select h from HR h JOIN h.user u where u.username=?1")	
	HR findByUsername(String username);

}
