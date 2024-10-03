package com.payroll_app.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.dto.ProjectEmployeeStatDto;
import com.payroll_app.project.exception.InputValidationException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Project;
import com.payroll_app.project.service.ProjectService;


@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/add/{managerId}")
	public ResponseEntity<?> addProject(@PathVariable int managerId, @RequestBody Project project, MessageDto dto) {
		try {
			project =  projectService.addProject(managerId, project);
			return ResponseEntity.ok(project);
		} catch (InputValidationException e) {
			 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/{pid}")
	public ResponseEntity<?> getProjectById(@PathVariable int pid,MessageDto dto) {
		try {
			Project project = projectService.getProjectById(pid);
			return ResponseEntity.ok(project);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/employee/{eid}")
	public List<Project> getProjectByEmployeeId(@PathVariable int eid) {
		return projectService.getProjectByEmployeeId(eid);
	}
	
	
	@GetMapping("/employee/stat")
	public List<ProjectEmployeeStatDto>getEmployeeCountByProjectType() {
		return projectService.getEmployeeCountByProjectType();
	}
	
	
}
