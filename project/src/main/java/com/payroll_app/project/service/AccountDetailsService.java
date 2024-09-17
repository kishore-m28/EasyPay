package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.AccountDetails;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.repository.AccountDetailsRepo;
import com.payroll_app.project.repository.EmployeeRepository;

@Service
public class AccountDetailsService {
	
	@Autowired
	private AccountDetailsRepo accountDetailsRepo;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public AccountDetails addAccountDetails(int eid, AccountDetails accountDetails) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(eid);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		Employee employee =optional.get();
		
	    accountDetails.setEmployee(employee);
	    accountDetailsRepo.save(accountDetails);
	    
	    employee.setStatus("ACTIVE");
	    employeeRepository.save(employee);
	    
	    return accountDetails;
	    
	}

	
}
