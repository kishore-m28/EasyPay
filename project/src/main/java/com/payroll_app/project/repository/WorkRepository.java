package com.payroll_app.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll_app.project.model.Work;

public interface WorkRepository extends JpaRepository<Work, Integer>{

	@Query("select w from Work w where w.employee.id=?1")
	Page<Work> getWork(int eid, Pageable pageable);
	
	@Query("select w from Work w where w.employee.user.username=?1")
	Page<Work> viewWork(String username, Pageable pageable);

	@Query("select w from Work w where w.employee.user.username=?1 ") // not used
	int findByUsername(String username);

}
