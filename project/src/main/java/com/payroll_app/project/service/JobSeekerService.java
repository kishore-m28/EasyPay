package com.payroll_app.project.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payroll_app.project.dto.JobSeekerBasicDetailsDto;
import com.payroll_app.project.exception.InputValidationException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.HRInterview;
import com.payroll_app.project.model.HRScoreSheet;
import com.payroll_app.project.model.Job;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.model.JobSeeker;
import com.payroll_app.project.model.Resume;
import com.payroll_app.project.model.TechnicalInterview;
import com.payroll_app.project.model.TechnicalScoreSheet;
import com.payroll_app.project.model.User;
import com.payroll_app.project.repository.HRInterviewRepository;
import com.payroll_app.project.repository.HRScoreSheetRepository;
import com.payroll_app.project.repository.JobApplicationRepository;
import com.payroll_app.project.repository.JobRepository;
import com.payroll_app.project.repository.JobSeekerRepository;
import com.payroll_app.project.repository.ResumeRepository;
import com.payroll_app.project.repository.TechnicalInterviewRepository;
import com.payroll_app.project.repository.TechnicalScoreSheetRepository;
import com.payroll_app.project.repository.UserRepository;

@Service
public class JobSeekerService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ResumeRepository resumeRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;
	
	@Autowired
	private HRScoreSheetRepository hrScoreSheetRepository;
	
	@Autowired
	private TechnicalScoreSheetRepository technicalScoreSheetRepository;
	
	@Autowired
	private HRInterviewRepository hRInterviewRepository;
	
	@Autowired
	private TechnicalInterviewRepository technicalInterviewRepository;
	
	public JobSeeker addJobSeeker(JobSeeker jobSeeker,String username) {
		Resume resume=jobSeeker.getResume();
		resume=resumeRepository.save(resume);
		jobSeeker.setResume(resume);
	    Optional<User> optional=userRepository.findByUsername(username);
	    User user=optional.get();
		/*User user=jobSeeker.getUser();
		user.setRole("ROLE_JOBSEEKER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user=userRepository.save(user);**/
		jobSeeker.setUser(user);
		return jobSeekerRepository.save(jobSeeker);
	}
	
	public void validateJobSeeker(JobSeeker jobSeeker) throws InputValidationException {
		if(jobSeeker==null) 
			throw new InputValidationException("Object cannot be null");
		if(jobSeeker.getName()==null|| jobSeeker.getName().equals(""))
			throw new InputValidationException("JobSeeker name given is NULL or blank, InputValidationException Thrown");
		if(jobSeeker.getContact()==null|| jobSeeker.getContact().equals(""))
			throw new InputValidationException("JobSeeker contact given is NULL or blank, InputValidationException Thrown");
		if(jobSeeker.getDateOfBirth()==null)
			throw new InputValidationException("JobSeeker dob given is NULL or blank, InputValidationException Thrown");	
	}

	public JobApplication applyJob(int jobId, String jobSeekerUsername) throws InvalidIdException {
		Optional<Job> optional= jobRepository.findById(jobId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Job Id..");
		Job job=optional.get();
		
		JobSeeker jobSeeker=jobSeekerRepository.findByUsername(jobSeekerUsername);
		
		JobApplication jobApplication=new JobApplication();
		jobApplication.setApplyDate(LocalDate.now());
		jobApplication.setJobSeeker(jobSeeker);
		jobApplication.setJob(job);
		jobApplication.setStatus("applied");
		
		return jobApplicationRepository.save(jobApplication);
		
	}

	//To display basic details in my profile tab
	public JobSeekerBasicDetailsDto displayBasicDetails(String userName,JobSeekerBasicDetailsDto dto) {
		 JobSeeker j=jobSeekerRepository.findByUsername(userName);
		 dto.setName(j.getName());
		 dto.setDateOfBirth(j.getDateOfBirth());
		 dto.setGender(j.getGender());
		 dto.setContact(j.getContact());
		 dto.setCurrentEmployer(j.getCurrentEmployer());
		 dto.setEasyPayId(j.getId());
		 return dto;
	}

	//to display list of jobs applied by a particular jobSeeker
	public List<Job> listOfAppliedJobs(String userName) {
		JobSeeker j=jobSeekerRepository.findByUsername(userName);
		int jobSeekerId=j.getId();
		List<Job> job=jobApplicationRepository.listOfAppliedJobs(jobSeekerId);
		return job;	
	}

	public JobApplication getJobSeekerDetailsByAppId(int appId) throws InvalidIdException {
		Optional<JobApplication> optional = jobApplicationRepository.findById(appId);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Application ID invalid");
		}
		JobApplication jobApplication = optional.get();
		return jobApplication;	
		
	}

	public List<JobApplication> getAllApplication() {
		return jobApplicationRepository.findAll();		
	}

	public List<Job> getJobOffer(String name) {
		JobSeeker j=jobSeekerRepository.findByUsername(name);
		int jobSeekerId=j.getId();
		List<Job> job=hrScoreSheetRepository.findJobOfferOfJobSeeker(jobSeekerId);
		return null;
	}

	public HRScoreSheet hrRoundStatus(String name) {
		JobSeeker j=jobSeekerRepository.findByUsername(name);
		int jobSeekerId=j.getId();
		HRScoreSheet s=hrScoreSheetRepository.hrRoundStatus(jobSeekerId);
		return s;
	}

	public TechnicalScoreSheet technicalRoundStatus(String name) {
		JobSeeker j=jobSeekerRepository.findByUsername(name);
		int jobSeekerId=j.getId();
		TechnicalScoreSheet s=technicalScoreSheetRepository.technicalRoundStatus(jobSeekerId);
		return s;
	}

	public HRInterview getHrInterview(String name) {
		JobSeeker j=jobSeekerRepository.findByUsername(name);
		int jobSeekerId=j.getId();
		HRInterview hr=hRInterviewRepository.getHrInterview(jobSeekerId);
		return hr;
	}

	public TechnicalInterview getTechInterview(String name) {
		JobSeeker j=jobSeekerRepository.findByUsername(name);
		int jobSeekerId=j.getId();
		TechnicalInterview tr=technicalInterviewRepository.getTechInterview(jobSeekerId);
		return tr;
	}

	//To get status of technical Interview of jobSeeker
	/*public String getStatusOfTechnicalInterview(String userName) {
		JobSeeker j=jobSeekerRepository.findByUsername(userName);
		int jobSeekerId=j.getId();
		return jobSeekerRepository.getStatusOfTechnicalInterview(jobSeekerId);
	}

	//To get status of HR Interview of jobSeeker
	public String getStatusOfHrInterview(String userName) {
		JobSeeker j=jobSeekerRepository.findByUsername(userName);
		int jobSeekerId=j.getId();
		return jobSeekerRepository.getStatusOfHrInterview(jobSeekerId);
	}*/

		
}


