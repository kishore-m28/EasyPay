package com.payroll_app.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.InvalidJobSeekerException;
import com.payroll_app.project.model.HR;
import com.payroll_app.project.model.HRInterview;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.model.JobSeeker;
import com.payroll_app.project.repository.HRInterviewRepository;
import com.payroll_app.project.repository.HRRepository;
import com.payroll_app.project.repository.JobApplicationRepository;
import com.payroll_app.project.repository.JobSeekerRepository;
import com.payroll_app.project.repository.TechnicalScoreSheetRepository;


@Service
public class HRInterviewService {

	@Autowired
	private HRInterviewRepository hrInterviewRepository;
	
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
	
	@Autowired
	private HRRepository hrRepository;
	
	@Autowired
	private TechnicalScoreSheetRepository technicalScoreSheetRepository;
	
	public HRInterview scheduleHrInterview(int aid, String username, HRInterview hrInterview) throws InvalidIdException, InvalidJobSeekerException {
		Optional<JobApplication> optional = jobApplicationRepository.findById(aid);
		if(optional.isEmpty())
			throw new InvalidIdException("JobSeeker ID invalid");
		
		HR hr = hrRepository.findByUsername(username);	
		JobApplication jobApplication = optional.get();
		
		boolean isCleared = technicalScoreSheetRepository.isCleared(jobApplication.getId());
		if(isCleared == true) {
			hrInterview.setJobApplication(jobApplication);
			hrInterview.setHr(hr);
			
			return hrInterviewRepository.save(hrInterview);		
		}
		else {
			throw new InvalidJobSeekerException("Jobseeker has not cleared technical round");
		}
	}

	public void validate(HRInterview hrInterview) throws InputInvalidException {
		if(hrInterview.getDate().toString()==null || hrInterview.getDate().toString()=="") {
			throw new InputInvalidException("Date cannot be null/blank");
		}
		if(hrInterview.getFromTime().toString()==null || hrInterview.getFromTime().toString()=="") {
			throw new InputInvalidException("Start time cannot be null/blank");
		}
		if(hrInterview.getToTime().toString()==null || hrInterview.getToTime().toString()=="") {
			throw new InputInvalidException("End time cannot be null/blank");
		}
		if(hrInterview.getInterviewLink()==null || hrInterview.getInterviewLink()=="") {
			throw new InputInvalidException("Interview Link cannot be null/blank");
		}
	}

	public List<HRInterview> getAll(String name) {
		return hrInterviewRepository.getAll(name);
	}

}
