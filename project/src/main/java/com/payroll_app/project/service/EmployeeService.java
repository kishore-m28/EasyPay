package com.payroll_app.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payroll_app.project.dto.EmployeeDisplayDto;
import com.payroll_app.project.dto.EmployeeFilterDto;
import com.payroll_app.project.dto.GenderStatDto;
import com.payroll_app.project.dto.SalaryProcessDto;
import com.payroll_app.project.enums.Status;
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.NoEmployeesFoundException;
import com.payroll_app.project.model.Address;
import com.payroll_app.project.model.Attendance;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Issue;
import com.payroll_app.project.model.LeaveRecord;
import com.payroll_app.project.model.Manager;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.model.User;
import com.payroll_app.project.repository.AddressRepository;
import com.payroll_app.project.repository.AttendanceRepository;
import com.payroll_app.project.repository.EmployeeRepository;
import com.payroll_app.project.repository.HRScoreSheetRepository;
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

	@Autowired
	private HRScoreSheetRepository hrScoreSheetRepository;
	
	@Autowired
	private HRScoreSheetService hrScoreSheetService;
	
	@Autowired
	private AddressRepository addressRepository;

	public Employee addEmployee(Employee employee) {
//		User user = employee.getUser();
//		user.setRole("ROLE_EMPLOYEE");
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		user = userRepository.save(user);
//		employee.setUser(user);
//		Address address=employee.getAddress();
//		address=addressRepository.save(address);
		employee.setStatus("ACTIVE");
		return employeeRepository.save(employee);
	}

	public List<Salary> getSalaryByEmployee(String empUsername) throws InvalidIdException {

		/* Creating employee object to fetch employee belonging to that username */
		Employee employee = employeeRepository.findByUsername(empUsername);

		/* get the id of employee */
		int id = employee.getId();

		/* Get the list of salary detail of that particular employee using the id */
		List<Salary> list = salaryRepository.getSalaryByEmployeeId(id);
		return list;

	}

	public Employee getById(int eid) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(eid);
		if (optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		return optional.get();
	}

	public Employee updateEmployee(int eid, Employee newEmployee) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(eid);
		if (optional.isEmpty())
			throw new InvalidIdException("Invalid ID Given");

		Employee employeeDB = optional.get();
		
		employeeDB.setName(newEmployee.getName());
		employeeDB.setContact(newEmployee.getContact());
		employeeDB.setDepartment(newEmployee.getDepartment());
		employeeDB.setDesignation(newEmployee.getDesignation());
		employeeDB.setDateOfBirth(newEmployee.getDateOfBirth());
		employeeDB.setDateOfJoining(newEmployee.getDateOfJoining());

		return employeeRepository.save(employeeDB);
	}

	public void deleteEmployee(int eid) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(eid);
		if (optional.isEmpty()) {
			throw new InvalidIdException("Invalid ID Given");
		}
		Employee employee = optional.get();
		employee.setStatus("INACTIVE");
		employeeRepository.save(employee);
	}

	public Page<Employee> getAllEmployees(Pageable pageable) throws NoEmployeesFoundException {
		Page<Employee> employees = employeeRepository.findAll(pageable);
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

	public LeaveRecord addLeave(LeaveRecord leave, String loggedInUsername, int mid) throws InputInvalidException {
	 
	    Employee employee = employeeRepository.getEmployee(loggedInUsername);
	    Optional<Manager> optionalManager = managerRepository.findById(mid);
	    if (optionalManager.isEmpty()) {
	        throw new InputInvalidException("Enter the correct manager id");
	    }
	    Manager manager = optionalManager.get();
	    leave.setApplyDate(LocalDate.now());
	    leave.setStatus(Status.PENDING);
	    leave.setManager(manager);
	    leave.setEmployee(employee); 
	    
	    return leaveRepository.save(leave);
	}

	public Attendance addAttendance(Attendance attendance, String loggedInUsername, int mid)
			throws InputInvalidException {

		Employee employee = employeeRepository.getEmployee(loggedInUsername);

		Optional<Manager> optionalManager = managerRepository.findById(mid);
		if (optionalManager.isEmpty()) {
			throw new InputInvalidException("Enter the correct manager id");
		}

		Manager manager = optionalManager.get();

		attendance.setAttendanceDate(LocalDate.now());
		attendance.setManager(manager);
		attendance.setEmployee(employee);

		return attendanceRepository.save(attendance);
	}

	public Issue addIssue(Issue issue, String loggedInUsername, int mid) throws InputInvalidException {

		Employee employee = employeeRepository.getEmployee(loggedInUsername);
		Optional<Manager> optionalManager = managerRepository.findById(mid);
		if (optionalManager.isEmpty()) {
			throw new InputInvalidException("Enter the correct manager id");
		}

		Manager manager = optionalManager.get();

		issue.setDate(LocalDate.now());
		issue.setEmployee(employee);
		issue.setManager(manager);

		return issueRepository.save(issue);
	}



	public List<SalaryProcessDto> getEmployeeAndSalary(String loggedInUsername) throws InputInvalidException {
	    List<Object[]> list = employeeRepository.getEmployeeAndSalaryByUsername(loggedInUsername);
	    List<SalaryProcessDto> listDto = new ArrayList<>();

		for (Object[] row : list) {
			SalaryProcessDto dto = new SalaryProcessDto();
	      
	        dto.setId((Integer) row[0]); 
	        dto.setName((String) row[1]);
	        dto.setContact((String) row[2]);
	        dto.setBonus(((Double) row[3]));
	        dto.setBasic(((Number) row[4]).doubleValue());
	        dto.setHra(((Number) row[5]).doubleValue());
	        dto.setMa(((Number) row[6]).doubleValue());
	        dto.setLta(((Number) row[7]).doubleValue());
	        dto.setDa(((Number) row[8]).doubleValue());
	        dto.setTaxRate(((Number) row[9]).doubleValue());
	        dto.setTaxableIncome(((Number) row[10]).doubleValue());
	        dto.setProffesionalTaxRate(((Number) row[11]).doubleValue());
	        dto.setGrossPay(((Number) row[12]).doubleValue());
	        dto.setAnnualNetPay(((Number) row[13]).doubleValue());
	        dto.setMonthlyNetPay(((Number) row[14]).doubleValue());

	        listDto.add(dto);
	    }
	    return listDto;
}

	public long getActiveEmployeeCount() {
		return employeeRepository.countActiveEmployees();
	}

	/*public String onboardEmployee(int hrScoreSheetId) throws InputInvalidException, JobTitleException{
		Optional<HRScoreSheet> optional = hrScoreSheetRepository.findById(hrScoreSheetId);
		if(optional.isEmpty())
			throw new InputInvalidException("Invalid HR Scoresheet ID");

		HRScoreSheet hrScoreSheet =  optional.get();

		String jobTitle = hrScoreSheetService.getJobTitleForOnboarding(hrScoreSheetId);

	    JobRole jobRole = JobRole.valueOf(jobTitle);  

	    Employee newEmployee = new Employee();
	    newEmployee.setName(hrScoreSheet.getJobSeeker().getName());
	    newEmployee.setContact(hrScoreSheet.getJobSeeker().getContact());
	    newEmployee.setJobRole(jobRole);

		employeeRepository.save(newEmployee);

		return "Employee onboarded successfully: ";
	}*/

	public Employee getEmployeeById(int eid) throws InvalidIdException {
		Optional<Employee> optional =employeeRepository.findById(eid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Employee ID invalid");
		}
		return optional.get();
	}

	public List<EmployeeDisplayDto> getEmployeeByFilterV2(EmployeeFilterDto empFilter) {
		List<Employee> empList=employeeRepository.findAll();
		String dept=empFilter.getDepartment();
		String designation=empFilter.getDesignation();
		String loc=empFilter.getCity();
		List<EmployeeDisplayDto> list=new ArrayList<>();
		for(Employee e:empList) {
			EmployeeDisplayDto dto=new EmployeeDisplayDto();
			if(dept.equalsIgnoreCase(e.getDepartment().toString())||designation.equalsIgnoreCase(e.getDesignation().toString())||loc.equalsIgnoreCase(e.getAddress().getCity().toString())) {
				dto.setId(e.getId());
				dto.setName(e.getName());
				dto.setDepartment(e.getDepartment());
				dto.setDesignation(e.getDesignation());	
				list.add(dto);
			}
		}
		return list;
	}
	
	/*
	public List<EmployeeDisplayDto> getEmployeeByFilter(EmployeeFilterDto empFilter) {
	    List<Employee> empList = employeeRepository.findAll();
	    String dept = empFilter.getDepartment();
	    String designation = empFilter.getDesignation();
	    String loc = empFilter.getLocation();
	    List<EmployeeDisplayDto> list = new ArrayList<>();

	    for (Employee e : empList) {
	        boolean matches = true;

	        // Check if department is provided and matches
	        if (dept != null && !dept.isEmpty()) {
	            if (!dept.equalsIgnoreCase(e.getDepartment().toString())) {
	                matches = false;
	            }
	        }

	        // Check if designation is provided and matches
	        if (designation != null && !designation.isEmpty()) {
	            if (!designation.equalsIgnoreCase(e.getDesignation().toString())) {
	                matches = false;
	            }
	        }

	        // Check if location is provided and matches
	        if (loc != null && !loc.isEmpty()) {
	            if (!loc.equalsIgnoreCase(e.getAddress().getCity().toString())) {
	                matches = false;
	            }
	        }

	        // If all provided filters match, add to the list
	        if (matches) {
	            EmployeeDisplayDto dto = new EmployeeDisplayDto();
	            dto.setName(e.getName());
	            dto.setDepartment(e.getDepartment());
	            dto.setDesignation(e.getDesignation());
	            list.add(dto);
	        }
	    }

	    return list;
	}*/
	
	public List<EmployeeDisplayDto> getEmployeeByFilter(EmployeeFilterDto empFilter) {
	    List<Employee> empList = employeeRepository.findAll();
	    String dept = empFilter.getDepartment();
	    String designation = empFilter.getDesignation();
	    String loc = empFilter.getCity();
	    List<EmployeeDisplayDto> list = new ArrayList<>();

	    for (Employee e : empList) {
	        boolean matches = true;

	        // Check if department is provided and matches, if not provided ignore
	        if (dept != null && !dept.trim().isEmpty()) {
	            if (!dept.equalsIgnoreCase(e.getDepartment().toString())) {
	                matches = false;
	            }
	        }

	        // Check if designation is provided and matches, if not provided ignore
	        if (designation != null && !designation.trim().isEmpty()) {
	            if (!designation.equalsIgnoreCase(e.getDesignation().toString())) {
	                matches = false;
	            }
	        }

	        // Check if location is provided and matches, if not provided ignore
	        if (loc != null && !loc.trim().isEmpty()) {
	            if (!loc.equalsIgnoreCase(e.getAddress().getCity().toString())) {
	                matches = false;
	            }
	        }

	        // If all provided filters match, add to the list
	        if (matches) {
	            EmployeeDisplayDto dto = new EmployeeDisplayDto();
	            dto.setId(e.getId());
	            dto.setName(e.getName());
	            dto.setDepartment(e.getDepartment());
	            dto.setDesignation(e.getDesignation());
	            list.add(dto);
	        }
	    }

	    return list;
	}

	public  GenderStatDto getEmpGender() {
		int total = (int) employeeRepository.countActiveEmployees();
		int femaleCount = employeeRepository.countFemaleEmployees();
		int maleCount = total-femaleCount;
		
		GenderStatDto genderStatDto = new GenderStatDto();
		
		int femalePercent=(femaleCount/total)*100;
		
		int malePercent=100-femalePercent;
		
		List<Integer> c = Arrays.asList(femaleCount,maleCount);
		List<Integer> per=Arrays.asList(femalePercent,malePercent);
		
		genderStatDto.setCount(c);
		genderStatDto.setPercentage(per);
		
		return genderStatDto;
		
	}




	/*
	private JobApplication findRelevantJobApplicationForJobSeeker(JobSeeker jobSeeker) throws Exception {
        return jobSeeker.getJobApplications()
                .stream()
                .filter(jobApplication -> jobApplication.isSelectedForOffer())
                .findFirst()
                .orElseThrow(() -> new Exception("No valid Job Application found for the Job Seeker"));
    }*/
	
}
