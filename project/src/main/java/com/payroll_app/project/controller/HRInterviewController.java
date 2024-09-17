package com.payroll_app.project.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.InvalidJobSeekerException;
import com.payroll_app.project.model.HRInterview;
import com.payroll_app.project.service.HRInterviewService;

@RestController
@RequestMapping("/hr-interview")
public class HRInterviewController {
	
	@Autowired
	private HRInterviewService hrInterviewService;
	
	@PostMapping("/schedule/{jid}")
	public ResponseEntity<?> scheduleHrInterview(@PathVariable int jid, Principal principal, @RequestBody HRInterview hrInterview, MessageDto dto){	     
		try {
			hrInterview = hrInterviewService.scheduleHrInterview(jid, principal.getName(), hrInterview);
			return ResponseEntity.ok(hrInterview);
		} catch (InvalidIdException | InvalidJobSeekerException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}	
	}
	

}
