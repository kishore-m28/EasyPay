package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.InvalidJobSeekerException;
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

	public TechnicalInterview scheduleTechInterview(int jid, int mid, TechnicalInterview technicalInterview) throws InvalidIdException, InvalidJobSeekerException {	
		Optional<JobSeeker> optional = jobSeekerRepository.findById(jid);
		if(optional.isEmpty())
			throw new InvalidIdException("JobSeeker ID invalid");
		
		Optional<Manager> optional1 = managerRepository.findById(mid);
		if(optional1.isEmpty())
			throw new InvalidIdException("JobSeeker ID invalid");
		
		JobSeeker jobSeeker = optional.get();
		Manager manager = optional1.get();
		
		
		if(jobApplicationRepository.findStatus(jid).equalsIgnoreCase("cleared"))
		{
			technicalInterview.setJobSeeker(jobSeeker);
			technicalInterview.setManager(manager);		
			return technicalInterviewRepository.save(technicalInterview);	
		}
		else {
			throw new InvalidJobSeekerException("Jobseeker has not cleared technical round");
		}
		
	}

}
