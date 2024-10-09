package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.InvalidJobSeekerException;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.model.JobSeeker;
import com.payroll_app.project.model.Manager;
import com.payroll_app.project.model.TechnicalInterview;
import com.payroll_app.project.repository.JobApplicationRepository;
import com.payroll_app.project.repository.JobSeekerRepository;
import com.payroll_app.project.repository.ManagerRepository;
import com.payroll_app.project.repository.TechnicalInterviewRepository;


@Service
public class TechnicalInterviewService {
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private TechnicalInterviewRepository technicalInterviewRepository;
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	public TechnicalInterview scheduleTechInterview(int aid, int mid, TechnicalInterview technicalInterview) throws InvalidIdException, InvalidJobSeekerException {	
		Optional<JobApplication> optional = jobApplicationRepository.findById(aid);
		if(optional.isEmpty())
			throw new InvalidIdException("JobSeeker ID invalid");
		
		Optional<Manager> optional1 = managerRepository.findById(mid);
		if(optional1.isEmpty())
			throw new InvalidIdException("JobSeeker ID invalid");
		
		JobApplication jobApplication = optional.get();
		Manager manager = optional1.get();
			
		if(jobApplicationRepository.findStatus(aid).equalsIgnoreCase("cleared"))
		{
			technicalInterview.setJobApplication(jobApplication);
			technicalInterview.setManager(manager);		
			return technicalInterviewRepository.save(technicalInterview);	
		}
		else {
			throw new InvalidJobSeekerException("Screen Test is not cleared");
		}
		
	}

}
