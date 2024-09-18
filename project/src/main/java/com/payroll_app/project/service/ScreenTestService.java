package com.payroll_app.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Job;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.repository.JobApplicationRepository;
import com.payroll_app.project.repository.JobRepository;
import com.payroll_app.project.repository.ResumeRepository;

@Service
public class ScreenTestService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ResumeRepository resumeRepository;
    
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    
    
    
/*
    public List<JobMatch> compareJobAndResumeSkills() {
        List<Job> jobs = jobRepository.findAllJobs();
        List<Resume> resumes = resumeRepository.findAllResumes();
        
        List<JobMatch> matches = new ArrayList<>();
        
        for (Job job : jobs) {
            for (Resume resume : resumes) {
                if (isMatching(job, resume)) {
                    JobMatch match = new JobMatch(job.getId(), resume.getId(), "Match");
                    matches.add(match);
                    
                }
            }
        }
        
        return matches;
    }

    private boolean isMatching(Job job, Resume resume) {
        return job.getSkill1().equals(resume.getSkill1()) ||
               job.getSkill1().equals(resume.getSkill2()) ||
               job.getSkill1().equals(resume.getSkill3()) ||
               job.getSkill2().equals(resume.getSkill1()) ||
               job.getSkill2().equals(resume.getSkill2()) ||
               job.getSkill2().equals(resume.getSkill3()) ||
               job.getSkill3().equals(resume.getSkill1()) ||
               job.getSkill3().equals(resume.getSkill2()) ||
               job.getSkill3().equals(resume.getSkill3());
    }
*/
	
    public boolean compareApplication(int appId) throws InvalidIdException {
        Optional<JobApplication> optional = jobApplicationRepository.findById(appId);
        if (optional.isEmpty()) {
            throw new InvalidIdException("Invalid Input");
        }

        JobApplication jobApplication = optional.get();
/*
        Job job=new Job();
        job=jobRepository.findAll();
        int experience=job.getExperienceRequired();
        
        JobSeeker jobSeeker=new JobSeeker();
        jobSeeker=JobSeekerRepository.findAll();
        int experience=jobSeeker.getExperience();
        */
        int jobExperience = jobApplicationRepository.getExperience(appId);
        int jobseekerExperience = jobApplicationRepository.getExperienceJobseeker(appId);
        boolean isExperienceValid = jobseekerExperience >= jobExperience;
        
        

     return isExperienceValid;

}
}