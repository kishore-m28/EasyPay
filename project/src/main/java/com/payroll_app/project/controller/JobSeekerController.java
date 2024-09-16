package com.payroll_app.project.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.model.JobSeeker;
import com.payroll_app.project.service.JobSeekerService;

@RestController
@RequestMapping("/jobSeeker")
public class JobSeekerController {
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@PostMapping("/add")
	public JobSeeker addJobSeeker(@RequestBody JobSeeker jobSeeker){
		return jobSeekerService.addJobSeeker(jobSeeker);	
	}
	
	@PostMapping("/job/apply/{jobId}")
	public ResponseEntity<?> applyJob(@PathVariable int jobId,Principal principal) {
		String jobSeekerUsername=principal.getName();
		try { 
			JobApplication jobApplication=jobSeekerService.applyJob(jobId,jobSeekerUsername);
			return ResponseEntity.ok(jobApplication);
		} catch (InvalidIdException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
