package com.payroll_app.project.controller;

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
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Job;
import com.payroll_app.project.service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@PostMapping("/add")
	public Job addJob(@RequestBody Job job) {
		return jobService.addJob(job);
	}
	
	@GetMapping("/all")
	public List<Job> getAllJob(){
		return jobService.getAllJob();
	}
	
	@GetMapping("/one/{jobId}")
	public ResponseEntity<?> getJobById(@PathVariable int jobId,MessageDto dto) {
		try {
			Job job=jobService.getJobById(jobId);
			 return ResponseEntity.ok(job);
		} catch (InvalidIdException e) {
			 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}
		
	}

}
