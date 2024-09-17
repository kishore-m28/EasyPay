package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.model.LeaveRecord;
import com.payroll_app.project.service.LeaveService;

@RestController
@RequestMapping("/leave")
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	@PutMapping("/request/approval")
	public List<LeaveRecord> leaveApproval(Principal principal){
		return leaveService.leaveApproval(principal.getName());
	}

}
