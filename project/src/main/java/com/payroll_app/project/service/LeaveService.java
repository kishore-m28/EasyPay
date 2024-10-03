package com.payroll_app.project.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.enums.Status;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.LeaveRecord;
import com.payroll_app.project.repository.LeaveRepository;

@Service
public class LeaveService {

	@Autowired
	private LeaveRepository leaveRepository;

	public void approveLeave(int lid, String status) throws InvalidIdException {
		Optional<LeaveRecord> optional = leaveRepository.findById(lid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Leave ID invalid");
		}
		LeaveRecord leaveRecord = optional.get();
		leaveRecord.setStatus(Status.valueOf(status));
		leaveRepository.save(leaveRecord);
	}
	
	public void rejectLeave(int lid, String status) throws InvalidIdException {
		Optional<LeaveRecord> optional = leaveRepository.findById(lid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Leave ID invalid");
		}
		LeaveRecord leaveRecord = optional.get();
		leaveRecord.setStatus(Status.valueOf(status));
		leaveRepository.save(leaveRecord);
	}

	public List<LeaveRecord> getAll(String name) {
		return leaveRepository.getAll(name);
	}


	

}
