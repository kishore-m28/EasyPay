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

import com.payroll_app.project.dto.JobSeekerBasicDetailsDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Job;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.model.JobSeeker;
import com.payroll_app.project.service.JobSeekerService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class JobSeekerController {
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@PostMapping("/jobSeeker/add")
	public JobSeeker addJobSeeker(@RequestBody JobSeeker jobSeeker,Principal principal){
		return jobSeekerService.addJobSeeker(jobSeeker,principal.getName());	
	}
	
	@PostMapping("/jobSeeker/job/apply/{jobId}")
	public ResponseEntity<?> applyJob(@PathVariable int jobId,Principal principal) {
		String jobSeekerUsername=principal.getName();
		try { 
			JobApplication jobApplication=jobSeekerService.applyJob(jobId,jobSeekerUsername);
			return ResponseEntity.ok(jobApplication);
		} catch (InvalidIdException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/jobSeeker/basic/details")
	public JobSeekerBasicDetailsDto displayBasicDetails(Principal principle,JobSeekerBasicDetailsDto dto) {
		String userName=principle.getName();
		return jobSeekerService.displayBasicDetails(userName,dto);	
	}
	
	@GetMapping("/jobSeeker/display/applied/jobs")
	public List<Job> listOfAppliedJobs(Principal principle) {
		String userName=principle.getName();
		List<Job> job=jobSeekerService.listOfAppliedJobs(userName);
		return job;
	}
	
	/*@GetMapping("/jobSeeker/status/TechnicalInterview")
	public String getStatusOfTechnicalInterview(Principal principle) {
		String userName=principle.getName();
		return jobSeekerService.getStatusOfTechnicalInterview(userName);
	}
	
	@GetMapping("/jobSeeker/status/HrInterview")
	public String getStatusOfHrInterview(Principal principle) {
		String userName=principle.getName();
		return jobSeekerService.getStatusOfHrInterview(userName);
	}*/

}
