package com.payroll_app.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.SalaryNotExists;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Job;
import com.payroll_app.project.model.JobSeeker;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.repository.EmployeeRepository;
import com.payroll_app.project.repository.JobApplicationRepository;
import com.payroll_app.project.repository.JobSeekerRepository;
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
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;


	/*To compute the salary of the employee*/
	public Salary computeSalaryForEmployee(int empId) throws InvalidIdException, SalaryNotExists {
		Optional<Employee> optional= employeeRepository.findById(empId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Employee id");
		Employee employee = optional.get();
		// Fetch the most recent salary record for the employee
	    List<Salary> list = salaryRepository.getSalaryByEmployeeId(empId);
	    //If the list is empty throw exception saying no salary record found
	    if (list.isEmpty()) 
	        throw new SalaryNotExists("Salary record not found");
	    Salary existingSalary =  list.get(list.size() - 1);
	    Salary computedSalary = salaryUtility.computeSalary(existingSalary);
	    computedSalary.setEmployee(employee);
	    return salaryRepository.save(computedSalary);
	}

	public Salary setSalary(int eid, Salary salary) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(eid);
		if (optional.isEmpty())
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
		if (optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");

		// Link salary to the employee
		Employee employee = optional.get();
		salary.setEmployee(employee);

		return salaryRepository.save(salary);
	}

	/*To process the payroll of employee in batch*/
	public List<Salary> processPayroll(List<Integer> empList) throws InvalidIdException{
		List<Salary> list=new ArrayList<>();
		for(Integer e:empList) {
			Optional<Employee> optional = employeeRepository.findById(e);
			if(optional.isEmpty())
				throw new InvalidIdException("Invalid Employee id");
			List<Salary> salaryList = salaryRepository.getSalaryByEmployeeId(e);
			Salary existingSalary = salaryList.get(salaryList.size() - 1);
			existingSalary.setStatus("PROCESSED");	
			Salary savedSalary = salaryRepository.save(existingSalary);
			list.add(savedSalary);	
		}
		return list;
	}
}

	 

	
