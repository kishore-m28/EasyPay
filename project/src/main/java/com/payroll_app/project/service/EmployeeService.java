package com.payroll_app.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payroll_app.project.dto.SalaryProcessDto;
import com.payroll_app.project.enums.Status;
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.NoEmployeesFoundException;
import com.payroll_app.project.model.Attendance;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Issue;
import com.payroll_app.project.model.LeaveRecord;
import com.payroll_app.project.model.Manager;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.model.User;
import com.payroll_app.project.repository.AttendanceRepository;
import com.payroll_app.project.repository.EmployeeRepository;
import com.payroll_app.project.repository.IssueRepository;
import com.payroll_app.project.repository.LeaveRepository;
import com.payroll_app.project.repository.ManagerRepository;
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
	
	
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private ManagerRepository managerRepository;
	

	
	
	
	
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

	
	public LeaveRecord addLeave(LeaveRecord leave, String loggedInUsername) throws InputInvalidException {
	    //Manager manager = managerRepository.findManagerByEmployeeUsername(loggedInUsername);
	    Employee employee = employeeRepository.getEmployee(loggedInUsername);
	    
	    leave.setApplyDate(LocalDate.now());
	    leave.setStatus(Status.PENDING);
	    //leave.setManager(manager);
	    leave.setEmployee(employee); // Associate the leave with the employee
	    
	    return leaveRepository.save(leave);
	}

	public Attendance addAttendance(Attendance attendance,String loggedInUsername) throws InputInvalidException {
	    
		//Manager manager = managerRepository.findManagerByEmployeeUsername(loggedInUsername);
	    Employee employee = employeeRepository.getEmployee(loggedInUsername);
	    attendance.setAttendanceDate(LocalDate.now());
	    //attendance.setManager(manager);
	    attendance.setEmployee(employee);
	    return attendanceRepository.save(attendance);
	}

	
	public Issue addIssue(Issue issue, String loggedInUsername) throws InputInvalidException {
		
		//Manager manager = managerRepository.findManagerByEmployeeUsername(loggedInUsername);
	    Employee employee = employeeRepository.getEmployee(loggedInUsername);
	   
	    
	    issue.setDate(LocalDate.now());
	    issue.setEmployee(employee);

	 

	    return issueRepository.save(issue);
	}


	public Employee addEmployee(Employee employee, int managerId) throws InputInvalidException {
		
		User user = employee.getUser();
		user.setRole("ROLE_EMPLOYEE");
		//encode the password 
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user = userRepository.save(user); //fully defined user with id 

		
		employee.setUser(user);
		
	        return employeeRepository.save(employee);

		
	}
	


	public Manager addManager(Manager manager) {

		User user = manager.getUser();
		user.setRole("ROLE_MANAGER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		
		manager.setUser(user);
		
        return managerRepository.save(manager);
	}
	
	

	public List<Salary> viewSalary(String loggedInUsername) {
		
		Employee employee = employeeRepository.getEmployee(loggedInUsername);
		return salaryRepository.findAll();
	}

	public Salary addSalary(Salary salary, String loggedInUsername) {
		
		Employee employee = employeeRepository.getEmployee(loggedInUsername);
		salary.setEmployee(employee);
		return salaryRepository.save(salary);
		
	}

	/*public List<SalaryProcessDto> getEmployeeAndSalary(String loggedInUsername) throws InputInvalidException {
	    List<Object[]> list = employeeRepository.getEmployeeAndSalaryByUsername(loggedInUsername); 
	    List<SalaryProcessDto> listDto = new ArrayList<>();

	    for (Object[] row : list) {
	        SalaryProcessDto dto = new SalaryProcessDto();

	        // Direct casting if the types are known and consistent
	        dto.setName((String) row[0]);
	        dto.setEmail((String) row[1]);
	        dto.setBasicPay(((Number) row[2]).doubleValue());
	        dto.setBonus(((Number) row[3]).doubleValue());
	        dto.setDa(((Number) row[4]).doubleValue());
	        dto.setHra(((Number) row[5]).doubleValue());
	        dto.setMa(((Number) row[6]).doubleValue());
	        dto.setMonth((String) row[7]);
	        dto.setNetPay(((Number) row[8]).doubleValue());
	        dto.setOverTimePay(((Number) row[9]).doubleValue());
	        dto.setTaxDeduction(((Number) row[10]).doubleValue());
	        dto.setYear((String) row[11]);

	        listDto.add(dto);
	    }
	    return listDto;
}  */


	
}

    
	