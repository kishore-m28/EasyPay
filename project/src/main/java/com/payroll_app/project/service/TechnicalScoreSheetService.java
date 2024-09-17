package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.enums.InterviewStatus;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.JobSeeker;
import com.payroll_app.project.model.TechnicalScoreSheet;
import com.payroll_app.project.repository.JobSeekerRepository;
import com.payroll_app.project.repository.TechnicalScoreSheetRepository;

@Service
public class TechnicalScoreSheetService {
	
	@Autowired
	private TechnicalScoreSheetRepository technicalScoreSheetRepository;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	
	public TechnicalScoreSheet updateTechScoreSheet(int jid, TechnicalScoreSheet technicalScoreSheet) throws InvalidIdException {
		Optional<JobSeeker> optional = jobSeekerRepository.findById(jid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("JobSeeker ID invalid");
		}
		JobSeeker jobSeeker = optional.get();
		technicalScoreSheet.setJobSeeker(jobSeeker);
		
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
