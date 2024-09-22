package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.LeaveRecord;
import com.payroll_app.project.service.EmployeeService;
import com.payroll_app.project.service.LeaveService;

@RestController
@RequestMapping("/leave")
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired 
	private EmployeeService employeeService;
	
	@PutMapping("/request/approval")
	public List<LeaveRecord> leaveApproval(Principal principal){
		return leaveService.leaveApproval(principal.getName());
	}
	
	
	@PostMapping("/record/add/{mid}")
	public ResponseEntity<?> addLeave(@RequestBody LeaveRecord leave,@PathVariable int mid, Principal principal,MessageDto dto) {
	    String loggedInUsername = principal.getName();
	    
	    try {
	        LeaveRecord savedLeave = employeeService.addLeave(leave,loggedInUsername,mid);
	        return ResponseEntity.ok(savedLeave);
	    } catch (InputInvalidException e) {
	        return ResponseEntity.badRequest().body(dto);
	    }
	}
	
	// API to reject leave by manager by leave id and status
	@PutMapping("/{lid}/{status}")
	public ResponseEntity<?> updateStatus(@PathVariable int lid, @PathVariable String status,MessageDto dto) {
		try {
			LeaveRecord leaveRecord = leaveService.updateStatus(lid, status);
			return ResponseEntity.ok(leaveRecord);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);			
		}
		
	}

}
