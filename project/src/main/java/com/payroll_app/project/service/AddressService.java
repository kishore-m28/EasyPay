package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Address;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.repository.AddressRepository;
import com.payroll_app.project.repository.EmployeeRepository;

@Service
public class AddressService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	public Address addAddress(int eid, Address address) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(eid);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		Employee employee =optional.get();
		
	    employee.setAddress(address);
	    addressRepository.save(address);
	    employeeRepository.save(employee);
	    
	    return address;
	}
	
	
}
