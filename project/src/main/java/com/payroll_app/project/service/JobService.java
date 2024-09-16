package com.payroll_app.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
