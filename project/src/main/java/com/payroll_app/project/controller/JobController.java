package com.payroll_app.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.JobDto;
import com.payroll_app.project.dto.JobFilterDto;
import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.enums.Department;
import com.payroll_app.project.enums.JobType;
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Job;
import com.payroll_app.project.service.JobService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@PostMapping("/job/add")
	public ResponseEntity<?> addJob(@RequestBody Job job) {
		
			try {
				jobService.validate(job);
			} catch (InputInvalidException e) {
				return ResponseEntity.status(e.getStatusCode()).body(e.getMessage()); 			
			}
			
			return ResponseEntity.ok(jobService.addJob(job));
	}
	
	@GetMapping("/job/all")
	public List<Job> getAllJob(){
		return jobService.getAllJob();
	}
	
	@PostMapping("/job/display/filter")
	public List<Job> getByFilter(@RequestBody JobFilterDto jobFilterDto){
		return jobService.getByFilter(jobFilterDto);
	}
	
	@GetMapping("/job/all/jobType")
	public List<JobType> getAllJobType(){
		return List.of(JobType.values());
	}
	
	@GetMapping("/job/one/{jobId}")
	public ResponseEntity<?> getJobById(@PathVariable int jobId,MessageDto dto) {
		try {
			Job job=jobService.getJobById(jobId);
			 return ResponseEntity.ok(job);
		} catch (InvalidIdException e) {
			 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}	
	}
	
	@GetMapping("/job/display/specific/details")
	public List<JobDto> displayLimitedJobDetails( ) {
		return jobService.displayLimitedJobDetails( );
	}
	
	@GetMapping("/job/display/specific/details/{jobId}")
	public  ResponseEntity<?> displayLimitedJobDetailsById(@PathVariable int jobId,MessageDto dto) {
		try {
			JobDto jobDto= jobService.displayLimitedJobDetailsById(jobId);
			return ResponseEntity.ok(jobDto);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/job/search/all")
	public List<Job> searchJobAll(@RequestBody Job job) {
		return jobService.searchJobAll(job);	
	}
	
	@GetMapping("/job/search/location")
	public List<Job> searchJobByLocation(@PathVariable String location) {
		return jobService.searchJobByLocation(location);	
	}


}
