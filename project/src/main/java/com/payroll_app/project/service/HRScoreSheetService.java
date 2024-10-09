package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.enums.InterviewStatus;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.exception.JobTitleException;
import com.payroll_app.project.model.HRScoreSheet;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.repository.HRScoreSheetRepository;
import com.payroll_app.project.repository.JobApplicationRepository;

@Service
public class HRScoreSheetService {
	
	@Autowired
	private HRScoreSheetRepository hrScoreSheetRepository;
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	public HRScoreSheet updateHrScoreSheet(int aid, HRScoreSheet hrScoreSheet) throws InvalidIdException {
		Optional<JobApplication> optional = jobApplicationRepository.findById(aid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("JobSeeker ID invalid");
		}
		JobApplication jobApplication = optional.get();
		hrScoreSheet.setJobApplication(jobApplication);
		
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

	/*public String getJobTitleForOnboarding(int hrScoreSheetId) throws JobTitleException{
        String jobTitle = hrScoreSheetRepository.findJobTitleByHRScoreSheetId(hrScoreSheetId);

        if (jobTitle == null) {
            throw new JobTitleException("No job title found for the provided HR Score Sheet ID or Job Seeker not selected for an offer");
        }

        return jobTitle;
    }*/
	
}
