package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.model.Attendance;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class AttendanceController {

    @Autowired
    private EmployeeService employeeService;
  //employee side
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
    
   
}

