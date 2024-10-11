package com.payroll_app.project.controller;

import java.security.Principal;
import java.util.List;

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
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.InvalidJobSeekerException;
import com.payroll_app.project.model.HRInterview;
import com.payroll_app.project.service.HRInterviewService;

@RestController
@RequestMapping("/hr-interview")
@CrossOrigin(origins = {"http://localhost:4200"})
public class HRInterviewController {
	
	@Autowired
	private HRInterviewService hrInterviewService;
	
	@PostMapping("/schedule/{aid}")
	public ResponseEntity<?> scheduleHrInterview(@PathVariable int aid, Principal principal, @RequestBody HRInterview hrInterview, MessageDto dto){	     
		try {
			hrInterviewService.validate(hrInterview);
			hrInterview = hrInterviewService.scheduleHrInterview(aid, principal.getName(), hrInterview);
			return ResponseEntity.ok(hrInterview);
		} catch (InvalidIdException | InvalidJobSeekerException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		} catch (InputInvalidException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.status(e.getStatusCode()).body(dto);
		}	
	}
	
	@GetMapping("/all")
	public List<HRInterview> getAll(Principal principal){
		return hrInterviewService.getAll(principal.getName());
	}
	

}
