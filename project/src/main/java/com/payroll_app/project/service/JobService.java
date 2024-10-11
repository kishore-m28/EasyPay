package com.payroll_app.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.dto.JobDto;
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
			dto.setId(j.getId());
			dto.setJobTitle(j.getJobTitle());
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
		dto.setJobTitle(j.getJobTitle());
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
}
