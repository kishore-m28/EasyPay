package com.payroll_app.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.dto.EmployeeGenderCountDto;
import com.payroll_app.project.dto.ProjectEmployeeStatDto;
import com.payroll_app.project.exception.InputValidationException;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Manager;
import com.payroll_app.project.model.Project;
import com.payroll_app.project.repository.ManagerRepository;
import com.payroll_app.project.repository.ProjectRepository;



@Service
public class ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	public Manager getById(int managerId) throws InputValidationException {
		 Optional<Manager> optional =  managerRepository.findById(managerId);
		 if(optional.isEmpty())
			 throw new InputValidationException("Manager ID Invalid");

		 return optional.get();
	}
	
	public List<Project> getProjectByManagerUsername(String name) {
		return projectRepository.getProjectByManagerUsername(name);
	}

	public List<Employee> getEmployeeByManagerUsername(String name) {
		return managerRepository.getEmployeeByManagerUsername(name);
	}

	public int getCountOfEmployeeByManagerUsername(String name) {
		return managerRepository.getCountOfEmployeeByManagerUsername(name);
	}

	public List<EmployeeGenderCountDto> getEmployeesByGender(String name) {
		List<Object[]> list = managerRepository.getEmployeesByGender(name);
 		List<EmployeeGenderCountDto> listDto = new ArrayList<>();
		for(Object[] obj : list) {
			String gender = obj[0].toString();
			long count =  (long) obj[1];
			EmployeeGenderCountDto dto = new EmployeeGenderCountDto(gender, count);
			listDto.add(dto);
		}
		return listDto;
	}

	
}
