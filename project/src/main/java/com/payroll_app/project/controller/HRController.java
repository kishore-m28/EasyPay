package com.payroll_app.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.enums.Department;
import com.payroll_app.project.enums.Designation;
import com.payroll_app.project.enums.Gender;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class HRController {

	@GetMapping("/get/gender")
	public List<?> getGender(){
		return List.of(Gender.values());
	}
	
	@GetMapping("/get/dept")
	public List<?> getDepartments(){
		return List.of(Department.values());
	}
	
	@GetMapping("/get/designation")
	public List<?> getDesignations(){
		return List.of(Designation.values());
	}
	
	
	
}
