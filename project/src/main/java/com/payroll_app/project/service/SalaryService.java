package com.payroll_app.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.dto.EmployeeDisplayDto;
import com.payroll_app.project.dto.EmployeeFilterDto;
import com.payroll_app.project.dto.ReviewPayrollDisplay;
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

	public void salaryStatusToPending(int id) {
		List<Salary> salaryList = salaryRepository.getSalaryByEmployeeId(id);
		Salary existingSalary = salaryList.get(salaryList.size() - 1);
		existingSalary.setStatus("PENDING");
		salaryRepository.save(existingSalary);
	}

	public List<ReviewPayrollDisplay> getSalaryByFilter(EmployeeFilterDto empFilter) {
		List<Employee> empList = employeeRepository.findAll();
	    String dept = empFilter.getDepartment();
	    String designation = empFilter.getDesignation();
	    String loc = empFilter.getCity();
	    List<ReviewPayrollDisplay> list = new ArrayList<>();
	    for (Employee e : empList) {
	        boolean matches = true;
	        if (dept != null && !dept.trim().isEmpty()) {
	            if (!dept.equalsIgnoreCase(e.getDepartment().toString())) {
	                matches = false;
	            }
	        }
	        if (designation != null && !designation.trim().isEmpty()) {
	            if (!designation.equalsIgnoreCase(e.getDesignation().toString())) {
	                matches = false;
	            }
	        }
	        if (loc != null && !loc.trim().isEmpty()) {
	            if (!loc.equalsIgnoreCase(e.getAddress().getCity().toString())) {
	                matches = false;
	            }
	        }
	        if (matches) {
	        	List<Salary> salaryList = salaryRepository.getSalaryByEmployeeId(e.getId());
				Salary s = salaryList.get(salaryList.size() - 1);
				Employee emp=s.getEmployee();
				ReviewPayrollDisplay dto=new ReviewPayrollDisplay();
				dto.setId(emp.getId());
				dto.setName(emp.getName());
				dto.setDepartment(emp.getDepartment());
				dto.setGrossPay(Math.round(s.getGrossPay()/12));
				double grossPay=(s.getAnnualCTC()-s.getBonus());
				double basic=(s.getAnnualCTC()-s.getBonus())-s.getDa()-s.getHra()-s.getMa()-s.getLta();
				double epf=0.12*(basic+s.getDa());
				double taxableIncome=grossPay-s.getHra()-epf-s.getMa();
				double tax=s.getTaxRate()*taxableIncome;
				long deduction=Math.round((s.getProffesionalTaxRate()*taxableIncome)+epf+tax);
				dto.setDeduction(deduction/12);
				double netSalary=grossPay-deduction;
				long netSalaryRounded = Math.round(netSalary);
				dto.setNetPay(netSalaryRounded/12);
				dto.setStatus(s.getStatus()); 
				list.add(dto);
	        }
	    }
	    return list;
	}

	public Salary getPaySlip(String name) {
		Employee e=employeeRepository.findByUsername(name);
		int id=e.getId();
		List<Salary> salaryList = salaryRepository.getSalaryByEmployeeId(id);
		Salary existingSalary = salaryList.get(salaryList.size() - 1);
		return existingSalary;
	}
}

	 

	
