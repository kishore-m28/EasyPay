package com.payroll_app.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	/* To compute the salary of the employee */
	public Salary computeSalaryForEmployee(int empId) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(empId);
		if (optional.isEmpty())
			throw new InvalidIdException("Invalid Employee id");
		Employee employee = optional.get();
		// Fetch the most recent salary record for the employee
		List<Salary> list = salaryRepository.getSalaryByEmployeeId(empId);
		Salary existingSalary = null;
		if (!list.isEmpty())
			existingSalary = list.get(list.size() - 1);
		Salary salaryToSave = null;
		if (existingSalary != null)
			salaryToSave = existingSalary;
		Salary computedSalary = salaryUtility.computeSalary(salaryToSave);
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

	/* To process the payroll of employee in batch */
	public List<Salary> processPayroll(List<Integer> eid) {
		return eid.parallelStream().map(e -> {
			Optional<Employee> optional = employeeRepository.findById(e);
			Employee employee = optional.get();
			List<Salary> list = salaryRepository.getSalaryByEmployeeId(e);
			Salary existingSalary = list.get(list.size() - 1);
			existingSalary.setStatus("PROCESSED");
			return salaryRepository.save(existingSalary);
		}).collect(Collectors.toList());
	}

}
