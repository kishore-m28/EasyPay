package com.payroll_app.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.NoEmployeesFoundException;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.model.User;
import com.payroll_app.project.repository.EmployeeRepository;
import com.payroll_app.project.repository.SalaryRepository;
import com.payroll_app.project.repository.UserRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SalaryRepository salaryRepository;
	
	public Employee addEmployee(Employee employee) {
		User user = employee.getUser();
		user.setRole("ROLE_EMPLOYEE");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		employee.setUser(user);
	   return employeeRepository.save(employee);
	}

	public List<Salary> getSalaryByEmployee(String empUsername) throws InvalidIdException {
		
		/*Creating employee object to fetch employee belonging to that username*/
		Employee employee=employeeRepository.findByUsername(empUsername);
		
		/*get the id of employee*/
		int id=employee.getId();
		
		/*Get the list of salary detail of that particular employee using the id*/
		List<Salary> list=salaryRepository.getSalaryByEmployeeId(id); 
		return list;	
		 
	} 

	public Employee getById(int eid) throws InvalidIdException{
		Optional<Employee> optional = employeeRepository.findById(eid);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		return optional.get();
	}

	public List<Employee> getAllEmployees() throws NoEmployeesFoundException  {
		List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new NoEmployeesFoundException("No employees found.");
        }
        return employees;
	}

	public long countEmployeesBelowAverageSalary() {
        double averageSalary = salaryRepository.findAverageSalary();
        return employeeRepository.countEmployeesWithSalaryLessThan(averageSalary);
    }

    public long countEmployeesAboveAverageSalary() {
        double averageSalary = salaryRepository.findAverageSalary();
        return employeeRepository.countEmployeesWithSalaryGreaterThan(averageSalary);
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