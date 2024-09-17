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
		
		salaryUtility.computeSalary(salary);
		
	}

	public Salary setSalary(int eid, Salary salary) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(eid);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		
		// Link salary to the employee
        Employee employee = optional.get();
        salary.setEmployee(employee);
        
		return salaryRepository.save(salary);
	}

	public double avgSalary() {
		return salaryRepository.findAverageSalary();
	}

	public Salary updateSalary(int eid, Salary salary) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(eid);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		
		// Link salary to the employee
        Employee employee = optional.get();
        salary.setEmployee(employee);
        
		return salaryRepository.save(salary);
	}
	
}
