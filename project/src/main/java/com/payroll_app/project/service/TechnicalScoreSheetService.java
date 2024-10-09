package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.enums.InterviewStatus;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.model.JobSeeker;
import com.payroll_app.project.model.TechnicalScoreSheet;
import com.payroll_app.project.repository.JobApplicationRepository;
import com.payroll_app.project.repository.JobSeekerRepository;
import com.payroll_app.project.repository.TechnicalScoreSheetRepository;

@Service
public class TechnicalScoreSheetService {
	
	@Autowired
	private TechnicalScoreSheetRepository technicalScoreSheetRepository;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;
	
	
	public TechnicalScoreSheet updateTechScoreSheet(int aid, TechnicalScoreSheet technicalScoreSheet) throws InvalidIdException {
		Optional<JobApplication> optional = jobApplicationRepository.findById(aid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Application ID invalid");
		}
		JobApplication jobApplication = optional.get();
		technicalScoreSheet.setJobApplication(jobApplication);
		
		if(technicalScoreSheet.getProblemSolvingScore()>3 && technicalScoreSheet.getCommunicationScore()>4 && technicalScoreSheet.getCodingSkillScore()>3 && technicalScoreSheet.getTechnicalKnowledgeScore()>3) {
			technicalScoreSheet.setStatus(InterviewStatus.CLEARED);
			technicalScoreSheet.setSelectedForHR(true);
		}
		else { 
			technicalScoreSheet.setStatus(InterviewStatus.REJECTED);
			technicalScoreSheet.setSelectedForHR(false);
		}
		
		return technicalScoreSheetRepository.save(technicalScoreSheet);
	}

}
