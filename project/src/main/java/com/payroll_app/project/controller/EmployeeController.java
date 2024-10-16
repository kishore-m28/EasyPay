package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.EmployeeDisplayDto;
import com.payroll_app.project.dto.EmployeeFilterDto;
import com.payroll_app.project.dto.GenderStatDto;
import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.dto.SalaryProcessDto;
import com.payroll_app.project.enums.Department;
import com.payroll_app.project.enums.Designation;
import com.payroll_app.project.enums.City;
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.AccountDetails;
import com.payroll_app.project.model.Address;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.service.AccountDetailsService;
import com.payroll_app.project.service.AddressService;
import com.payroll_app.project.service.EmployeeService;


@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = {"http://localhost:4200"})
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AccountDetailsService accountDetailsService;
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/add/basic-info")
	public Employee addEmployee(@RequestBody Employee employee){
		return employeeService.addEmployee(employee);
	}
	
	/*@PostMapping("/onboard/{hrScoreSheetId}")
    public ResponseEntity<String> onboardEmployee(@PathVariable int hrScoreSheetId) {
        try {
            String result = employeeService.onboardEmployee(hrScoreSheetId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/
	
	@GetMapping("/salary")
	public ResponseEntity<?> getSalaryByEmployee(Principal principal) {
		String empUsername=principal.getName();
		try {
			List<Salary> list= employeeService.getSalaryByEmployee(empUsername);
			return ResponseEntity.ok(list);
		} catch (InvalidIdException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/one/{eid}")
	public ResponseEntity<?> getById(@PathVariable	int eid, MessageDto dto){
		try {
			Employee employee =  employeeService.getById(eid);
			return ResponseEntity.ok(employee);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
 			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PutMapping("/update/{eid}")
	public ResponseEntity<?> updateEmployee(@PathVariable int eid,@RequestBody Employee newEmployee, MessageDto dto) {
		try {
			Employee emp = employeeService.updateEmployee(eid,newEmployee);
			return ResponseEntity.ok(emp); 
		} catch (InvalidIdException e) {
			 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}
	}
	
	
	// foreign key constraint in EmployeeProject Table
	//We are updating the status to "INACTIVE" i.e SOFT DELETE
	@PutMapping("/delete/{eid}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int eid,MessageDto dto) {
		try {
			employeeService.deleteEmployee(eid);
			dto.setMsg("Employee Deleted..");
			return ResponseEntity.ok(dto);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		} 
	}
	
	@PostMapping("/add-account-details/{eid}")
	public ResponseEntity<?> addAccountDetails(@PathVariable int eid, @RequestBody AccountDetails accountDetails, MessageDto dto) {
		try {
			accountDetails =  accountDetailsService.addAccountDetails(eid, accountDetails);
			return ResponseEntity.ok(accountDetails);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
 			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PostMapping("/offboard")
	public void offboardEmployee(@RequestBody Employee employee) {
		
	}
	
	@PostMapping("/add-address/{eid}")
	public ResponseEntity<?> addAddress(@PathVariable int eid, @RequestBody Address address, MessageDto dto) {
		try {
			address =  addressService.addAddress(eid, address);
			return ResponseEntity.ok(address);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
 			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees(@RequestParam(defaultValue = "0", required = false) Integer page, 
			@RequestParam(defaultValue = "1000", required = false) Integer size, MessageDto dto) {
	    try {
	    	Pageable pageable =   PageRequest.of(page, size);
	    	
	        Page<Employee> employees = employeeService.getAllEmployees(pageable);
	        return ResponseEntity.ok(employees);
	    } catch (Exception e) { 
	        dto.setMsg(e.getMessage());
	        return ResponseEntity.badRequest().body(dto);
	    }
	}
	
	@GetMapping("/active-count")
    public ResponseEntity<Long> getActiveEmployeeCount() {
        long activeCount = employeeService.getActiveEmployeeCount();
        return ResponseEntity.ok(activeCount);
    }
	
	@GetMapping("/salary/below-average")
    public ResponseEntity<Long> getEmployeesBelowAverageSalary() {
        long count = employeeService.countEmployeesBelowAverageSalary();
        return ResponseEntity.ok(count);
    }  
	
	@GetMapping("/salary/above-average")
    public ResponseEntity<Long> getEmployeesAboveAverageSalary() 
	{
        long count = employeeService.countEmployeesAboveAverageSalary();
        return ResponseEntity.ok(count);
    }
	
	
	/*To fetch employee based on the filters applied*/
	@PostMapping("/display/filter")
	public List<EmployeeDisplayDto> getEmployeeByFilter(@RequestBody EmployeeFilterDto empFilter){
		//EmployeeFilterDto empFilter = new EmployeeFilterDto(department, designation, city);
		return employeeService.getEmployeeByFilter(empFilter);
	}
	
	@GetMapping("/designation")
	public List<Designation> getAllDesignation(){
		return List.of(Designation.values());
	}
	
	@GetMapping("/department")
	public List<Department> getAllDepartment(){
		return List.of(Department.values());
	}
	
	@GetMapping("/city")
	public List<City> getAllCity(){
		return List.of(City.values());
	}
	
	@GetMapping("/gender/stat")
	public ResponseEntity<?> getEmpGender() {
		GenderStatDto stat = employeeService.getEmpGender();
		return ResponseEntity.ok(stat);
	}
	

	

	/*@GetMapping("/salary/payroll")
    public ResponseEntity<?> getEmployeeAndSalary(Principal principal,MessageDto dto) {
        String loggedInUsername = principal.getName();
        try {
        	List<SalaryProcessDto> sdto = employeeService.getEmployeeAndSalary(loggedInUsername);
			return ResponseEntity.ok(sdto);
		} catch (InputInvalidException e) {
			 return ResponseEntity.badRequest().body(dto);
		}
<<<<<<< HEAD
	}
        
	
=======
		
    }*/


}


