package com.payroll_app.project.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.model.Attendance;
import com.payroll_app.project.service.AttendanceService;
import com.payroll_app.project.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AttendanceController {

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private AttendanceService attendanceService;


    @PostMapping("/attendance/add/{mid}")
    public ResponseEntity<?> addAttendance(@RequestBody Attendance attendance,@PathVariable int mid, Principal principal,MessageDto dto) {
    	 String loggedInUsername = principal.getName();

        try {
            Attendance attendances = employeeService.addAttendance(attendance,loggedInUsername,mid);
            return ResponseEntity.ok(attendances);
        } catch (InputInvalidException e) {
            return ResponseEntity.badRequest().body(dto);
        }
    }
    
    @GetMapping("/present")
    public ResponseEntity<Integer> getPresentCount(Principal principal) {
    	Integer count = attendanceService.getPresentCount(principal.getName(), LocalDate.now());
    	int presentCount = count == null ? 0 : count;
    	return ResponseEntity.ok(presentCount);
    }
    
    @GetMapping("/absent")
    public ResponseEntity<Integer> getAbsentCount(Principal principal) {
    	Integer count = attendanceService.getAbsentCount(principal.getName(), LocalDate.now());
    	int absentCount = count == null ? 0 : count;
    	return ResponseEntity.ok(absentCount);
    }
    
    

   /* @PostMapping("/salary/insert")
    public void insertSalary(@RequestBody Salary salary, Principal principal)
    
    {
    	String loggedInUsername = principal.getName();
    	employeeService.addSalary(salary,loggedInUsername);
    }
    		
	@GetMapping("/salary/view")
	public List<Salary> viewSalary(Principal principal) 
	{
		
        String loggedInUsername = principal.getName();
		return employeeService.viewSalary(loggedInUsername);
	
	
	}*/

}

