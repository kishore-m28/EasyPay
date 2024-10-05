package com.payroll_app.project.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.enums.WorkStatus;
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Manager;
import com.payroll_app.project.model.Work;
import com.payroll_app.project.repository.EmployeeRepository;
import com.payroll_app.project.repository.ManagerRepository;
import com.payroll_app.project.repository.WorkRepository;

@Service
public class WorkService {
	
	@Autowired
	private WorkRepository workRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ManagerRepository managerRepository;

	public Work assignWork(int eid, Work work, String name) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(eid);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Invalid Employee ID");
		}
		Employee employee = optional.get();
		Manager manager = managerRepository.findByUsername(name);
		work.setEmployee(employee);
		work.setManager(manager);
		work.setAssignedDate(LocalDate.now());
		work.setStatus(WorkStatus.PENDING);
		return workRepository.save(work);		
	}

	public void validate(Work work) throws InputInvalidException {
		if(work.getDetails()==null || work.getDetails().equals("")) {
			throw new InputInvalidException("Work details cannot be null/blank");
		}
	}

}
