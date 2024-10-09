package com.payroll_app.project.controller;

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
import com.payroll_app.project.model.TechnicalInterview;
import com.payroll_app.project.service.TechnicalInterviewService;

@RestController
@RequestMapping("/tech-interview")
public class TechnicalInterviewController {
	
	@Autowired
	private TechnicalInterviewService technicalInterviewService;	
	
	@PostMapping("/schedule/{aid}/{mid}")
	public ResponseEntity<?> scheduleTechInterview(@PathVariable int aid, @PathVariable int mid, @RequestBody TechnicalInterview technicalInterview, MessageDto dto){	     
		try {
			technicalInterview = technicalInterviewService.scheduleTechInterview(aid, mid, technicalInterview);
			return ResponseEntity.ok(technicalInterview);
		} catch (InvalidIdException | InvalidJobSeekerException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}	
	}
	

}
