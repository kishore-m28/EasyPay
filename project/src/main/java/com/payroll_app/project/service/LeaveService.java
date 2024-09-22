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
	

	public List<LeaveRecord> leaveApproval(String username) {
		List<LeaveRecord> requests = leaveRepository.getLeaveRequests(username);
		
		for(LeaveRecord l: requests) {
			if (l.getStartDate().isAfter(l.getEndDate()) || l.getApplyDate().isAfter(l.getStartDate())) {
	            l.setStatus(Status.REJECTED);
		    }
			else {
				l.setStatus(Status.APPROVED);
			}
			l = leaveRepository.save(l);
	    }
		return requests;
	}


	public LeaveRecord updateStatus(int lid, String status) throws InvalidIdException {
		Optional<LeaveRecord> optional = leaveRepository.findById(lid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Leave ID invalid");
		}
		LeaveRecord leaveRecord = optional.get();
		leaveRecord.setStatus(Status.valueOf(status));
		leaveRepository.save(leaveRecord);
		return leaveRecord;
	}


	

}
