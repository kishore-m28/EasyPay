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
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.JobSeekerBasicDetailsDto;
import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.HRInterview;
import com.payroll_app.project.model.HRScoreSheet;
import com.payroll_app.project.model.Job;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.model.JobSeeker;
import com.payroll_app.project.model.TechnicalInterview;
import com.payroll_app.project.model.TechnicalScoreSheet;
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
	
	@PostMapping("/apply/job/{jobId}")
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
	
	@GetMapping("/jobseeker/application/all")
	public List<JobApplication> getAllApplication(){
		return jobSeekerService.getAllApplication();
	}
	
	@GetMapping("/jobseeker/details/{appId}")
	public ResponseEntity<?> getJobSeekerDetailsByAppId(@PathVariable int appId, MessageDto dto) {
		 try {
			return ResponseEntity.ok(jobSeekerService.getJobSeekerDetailsByAppId(appId));
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/offer/details/jobseeker")
	public List<Job> getJobOffer(Principal principal){
		return jobSeekerService.getJobOffer(principal.getName());
	}
	
	@GetMapping("/jobseeker/hrRound/status")
	public HRScoreSheet hrRoundStatus(Principal principal) {
		return jobSeekerService.hrRoundStatus(principal.getName());
	}
	
	@GetMapping("/jobseeker/technicalRound/status")
	public TechnicalScoreSheet technicalRoundStatus(Principal principal) {
		return jobSeekerService.technicalRoundStatus(principal.getName());
	}
	
	@GetMapping("/jobseeker/getHrInterview/link")
	public HRInterview getHrInterview(Principal principal){
		return jobSeekerService.getHrInterview(principal.getName());
	}
	
	@GetMapping("/jobseeker/getTechInterview/link")
	public TechnicalInterview getTechInterview(Principal principal){
		return jobSeekerService.getTechInterview(principal.getName());
	}

}
