package com.payroll_app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll_app.project.model.AccountDetails;

public interface AccountDetailsRepo extends JpaRepository<AccountDetails, Integer>{

}
