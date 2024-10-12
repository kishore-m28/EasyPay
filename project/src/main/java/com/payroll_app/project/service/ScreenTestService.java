package com.payroll_app.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.dto.JobSeekerInterviewDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.repository.JobApplicationRepository;

@Service
public class ScreenTestService {

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	public boolean compareSkillsandExperience(int appId) throws InvalidIdException {
		Optional<JobApplication> optional = jobApplicationRepository.findById(appId);
		if (optional.isEmpty()) {
			throw new InvalidIdException("Invalid Application ID");
		}
		

		JobApplication jobApplication = optional.get();

		int jobExperience = jobApplicationRepository.getExperience(appId);
		int jobseekerExperience = jobApplicationRepository.getExperienceJobseeker(appId);
		if (jobseekerExperience >= jobExperience) {
			
			// Retrieve job and resume skills
			List<Object[]> jobResults = jobApplicationRepository.findJobSkills(appId);
			List<Object[]> resumeResults = jobApplicationRepository.findResumeSkills(appId);

			// Check if results are empty
			if (resumeResults.isEmpty()) {
				return false; // No skills to compare
			}

			// Extract skills from results
			String jobSkill1 = (String) jobResults.get(0)[0];
			String jobSkill2 = (String) jobResults.get(0)[1];
			String jobSkill3 = (String) jobResults.get(0)[2];

			String resumeSkill1 = (String) resumeResults.get(0)[0];
			String resumeSkill2 = (String) resumeResults.get(0)[1];
			String resumeSkill3 = (String) resumeResults.get(0)[2];

			// Use a flag to count matching skills
			int matchCount = 0;

			// Compare job skills against resume skills
			if (jobSkill1.equalsIgnoreCase(resumeSkill1) || jobSkill1.equalsIgnoreCase(resumeSkill2) || jobSkill1.equalsIgnoreCase(resumeSkill3)) {
				matchCount++;
			}
			if (jobSkill2.equalsIgnoreCase(resumeSkill1) || jobSkill2.equalsIgnoreCase(resumeSkill2) || jobSkill2.equalsIgnoreCase(resumeSkill3)) {
				matchCount++;
			}
			if (jobSkill3.equalsIgnoreCase(resumeSkill1) || jobSkill3.equalsIgnoreCase(resumeSkill2) || jobSkill3.equalsIgnoreCase(resumeSkill3)) {
				matchCount++;
			}
			
			// Return true if all skills match
			return matchCount == 3;
					
		}

		return false;

	}

	public JobApplication updateApplication(int appId, String string) {
		JobApplication jobApplication = jobApplicationRepository.findById(appId).get();
		jobApplication.setStatus(string);
		return jobApplicationRepository.save(jobApplication);
				
	}

	
}
