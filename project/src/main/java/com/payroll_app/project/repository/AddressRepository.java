package com.payroll_app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll_app.project.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
