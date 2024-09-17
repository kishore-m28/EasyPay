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
import com.payroll_app.project.utility.SalaryUtility;

@Service
public class SalaryService {
	
	@Autowired
	private SalaryRepository salaryRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SalaryUtility salaryUtility;

	/*Not yet finished*/
	public void computeSalaryForEmployee(int empId) throws InvalidIdException {
		Optional<Employee> optional= employeeRepository.findById(empId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Employee id");
		List<Salary> list = salaryRepository.getSalaryByEmployeeId(empId);
		Salary salary=new Salary();
		if(!list.isEmpty()) 
			salary=list.get(list.size()-1);
		
		Salary computedSalary=salaryUtility.computeSalary(salary);
		//using employee id save it
		salaryRepository.save(computedSalary);
		
		
		
		
			
		
	}

}
