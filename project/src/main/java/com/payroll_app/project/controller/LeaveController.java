package com.payroll_app.project.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.LeaveRecord;
import com.payroll_app.project.service.EmployeeService;
import com.payroll_app.project.service.LeaveService;

@RestController
@RequestMapping("/leave")
@CrossOrigin(origins = {"http://localhost:4200"})
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired 
	private EmployeeService employeeService;
	
	
	//employee side
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
	
	@GetMapping("/all")
	public Page<LeaveRecord> getAll(Principal principal,
			@RequestParam(defaultValue ="0", required=false) Integer page,
			@RequestParam(defaultValue = "1000", required=false) Integer size){
		
		Pageable pageable = PageRequest.of(page, size);
		return leaveService.getAll(principal.getName(), pageable);
	}
	
	// API to approve leave by leave id and status
	@PutMapping("approval/{lid}/{status}")
	public ResponseEntity<?> approveLeave(@PathVariable int lid, @PathVariable String status, MessageDto dto){
		try {
			leaveService.approveLeave(lid,status);
			dto.setMsg("Leave request approved");
			return ResponseEntity.ok(dto);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	// API to reject leave by leave id and status
	@PutMapping("/{lid}/{status}")
	public ResponseEntity<?> rejectLeave(@PathVariable int lid, @PathVariable String status,MessageDto dto) {
		try {
			leaveService.rejectLeave(lid, status);
			dto.setMsg("Leave request rejected");
			return ResponseEntity.ok(dto);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);			
		}
		
	}

}
