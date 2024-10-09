package com.payroll_app.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.dto.JobDto;
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Job;
import com.payroll_app.project.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;
	
	public Job addJob(Job job) {
		return jobRepository.save(job);
	}

	public List<Job> getAllJob() {
		return jobRepository.findAll();
	}

	public Job getJobById(int jobId) throws InvalidIdException {
		Optional<Job> optional=jobRepository.findById(jobId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Job Id");
		return optional.get();
		
	}

	//To display only particular details of the job
	public List<JobDto> displayLimitedJobDetails(){
		List<Job> list=jobRepository.findAll();
		List<JobDto> dtoList=new ArrayList<>();
		for(Job j:list) {
			JobDto dto = new JobDto();
			dto.setTile(j.getJobTitle());
			dto.setLocation(j.getLocation());
			dto.setJobType(j.getJobType());
			dto.setPostingDate(j.getStartDate());
			dtoList.add(dto);
		}	
		return dtoList;	
	}

	//To display only particular details of the job by Id
	public JobDto displayLimitedJobDetailsById(int jobId) throws InvalidIdException {
		Optional<Job> optional=jobRepository.findById(jobId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Job Id");
		Job j=optional.get();
		JobDto dto = new JobDto();
		dto.setTile(j.getJobTitle());
		dto.setLocation(j.getLocation());
		dto.setJobType(j.getJobType());
		dto.setPostingDate(j.getStartDate());
		return dto;
		
	}

	public List<Job> searchJobAll(Job job) {
		String jobTitle=job.getJobTitle();
		LocalDate startDate=job.getEndDate();
		String location=job.getLocation().toLowerCase();
		double ctc=job.getCtc();
		List<Job> list=jobRepository.searchJobAll(jobTitle,startDate,location,ctc);
		return list;
	}

	public List<Job> searchJobByLocation(String location) {
		String newLocation=location.toLowerCase();
		List<Job> list=jobRepository.searchJobByLocation(newLocation);
		return list;
	}

	public void validate(Job job) throws InputInvalidException {
		if(job.getJobTitle()==null || job.getJobTitle()=="") {
			throw new InputInvalidException("Job Title cannot be blank/null");
		}
		if(job.getJobType()==null || job.getJobType()=="") {
			throw new InputInvalidException("Job Type cannot be blank/null");
		}
		if(job.getDescription()==null || job.getDescription()=="") {
			throw new InputInvalidException("Job Description cannot be blank/null");
		}
		if(job.getRequirements()==null || job.getRequirements()=="") {
			throw new InputInvalidException("Requirements cannot be blank/null");
		}
		if(job.getStartDate().toString()==null || job.getStartDate().toString()=="") {
			throw new InputInvalidException("Start Date cannot be blank/null");
		}
		if(job.getEndDate().toString()==null || job.getEndDate().toString()=="") {
			throw new InputInvalidException("End Date cannot be blank/null");
		}
		if(job.getLocation()==null ||job.getLocation()=="") {
			throw new InputInvalidException("Location cannot be blank/null");
		}
		if(job.getSkill1()==null ||job.getSkill1()=="") {
			throw new InputInvalidException("Skill1 cannot be blank/null");
		}
		if(job.getSkill2()==null ||job.getSkill2()=="") {
			throw new InputInvalidException("Skill2 cannot be blank/null");
		}
		if(job.getSkill3()==null ||job.getSkill3()=="") {
			throw new InputInvalidException("Skill3 cannot be blank/null");
		}
		if(job.getExperienceRequired()<0) {
			throw new InputInvalidException("Experience required cannot be negative");
		}
		if(job.getCtc()<0) {
			throw new InputInvalidException("CTC cannot be negative");
		}
				
	}
}
