package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.enums.InterviewStatus;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.HRScoreSheet;
import com.payroll_app.project.model.JobSeeker;
import com.payroll_app.project.repository.HRScoreSheetRepository;
import com.payroll_app.project.repository.JobSeekerRepository;

@Service
public class HRScoreSheetService {
	
	@Autowired
	private HRScoreSheetRepository hrScoreSheetRepository;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;

	public HRScoreSheet updateHrScoreSheet(int jid, HRScoreSheet hrScoreSheet) throws InvalidIdException {
		Optional<JobSeeker> optional = jobSeekerRepository.findById(jid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("JobSeeker ID invalid");
		}
		JobSeeker jobSeeker = optional.get();
		hrScoreSheet.setJobSeeker(jobSeeker);
		
		if(hrScoreSheet.getCommunicationScore()>4 && hrScoreSheet.getInterpersonalScore()>3 && hrScoreSheet.getAttitudeScore()>3 && hrScoreSheet.getAdaptabilityScore()>3) {
			hrScoreSheet.setStatus(InterviewStatus.CLEARED);
			hrScoreSheet.setSelectedForOffer(true);
		}
		else {
			hrScoreSheet.setStatus(InterviewStatus.REJECTED);
			hrScoreSheet.setSelectedForOffer(false);
		}
		
		return hrScoreSheetRepository.save(hrScoreSheet);
	}

}
