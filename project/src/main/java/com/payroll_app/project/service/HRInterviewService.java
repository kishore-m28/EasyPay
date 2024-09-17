package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.InvalidJobSeekerException;
import com.payroll_app.project.model.HR;
import com.payroll_app.project.model.HRInterview;
import com.payroll_app.project.model.JobSeeker;
import com.payroll_app.project.repository.HRInterviewRepository;
import com.payroll_app.project.repository.HRRepository;
import com.payroll_app.project.repository.JobSeekerRepository;
import com.payroll_app.project.repository.TechnicalScoreSheetRepository;


@Service
public class HRInterviewService {

	@Autowired
	private HRInterviewRepository hrInterviewRepository;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	private HRRepository hrRepository;
	
	@Autowired
	private TechnicalScoreSheetRepository technicalScoreSheetRepository;
	
	public HRInterview scheduleHrInterview(int jid, String username, HRInterview hrInterview) throws InvalidIdException, InvalidJobSeekerException {
		Optional<JobSeeker> optional = jobSeekerRepository.findById(jid);
		if(optional.isEmpty())
			throw new InvalidIdException("JobSeeker ID invalid");
		
		HR hr = hrRepository.findByUsername(username);	
		JobSeeker jobSeeker = optional.get();
		
		boolean isCleared = technicalScoreSheetRepository.isCleared(jobSeeker.getId());
		if(isCleared == true) {
			hrInterview.setJobSeeker(jobSeeker);
			hrInterview.setHr(hr);
			
			return hrInterviewRepository.save(hrInterview);		
		}
		else {
			throw new InvalidJobSeekerException("Jobseeker has not cleared technical round");
		}
	}

}
