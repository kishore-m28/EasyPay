package com.payroll_app.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.repository.EmployeeRepository;
import com.payroll_app.project.repository.SalaryRepository;

@Service
public class SalaryService {
	
	@Autowired
	private SalaryRepository salaryRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public void computeSalaryForEmployee(int empId) throws InvalidIdException {
		Optional<Employee> optional= employeeRepository.findById(empId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Employee id");
		/* Get the list of salary detail of that particular employee using the id */
		List<Salary> list = salaryRepository.getSalaryByEmployeeId(empId);
		for(Salary s:list) {
			double annualCTC=s.getAnnualCTC();
		}
		
	}

}
