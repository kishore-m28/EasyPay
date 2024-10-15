package com.payroll_app.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.AssignEmployeeDto;
import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.dto.ProjectEmployeeStatDto;
import com.payroll_app.project.enums.ProjectType;
import com.payroll_app.project.exception.InputValidationException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Project;
import com.payroll_app.project.service.ProjectService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping("/add/{managerId}")
	public ResponseEntity<?> addProject(@PathVariable int managerId, @RequestBody Project project, MessageDto dto) {
		try {
			project = projectService.addProject(managerId, project);
			return ResponseEntity.ok(project);
		} catch (InputValidationException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}

	@GetMapping("/{pid}")
	public ResponseEntity<?> getProjectById(@PathVariable int pid, MessageDto dto) {
		try {
			Project project = projectService.getProjectById(pid);
			return ResponseEntity.ok(project);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/all/completed")
	public ResponseEntity<?> getCompletedProjects(@RequestParam(defaultValue = "0", required = false) Integer page,
			@RequestParam(defaultValue = "1000", required = false) Integer size, MessageDto dto) {
		try {
			Pageable pageable = PageRequest.of(page, size);

			Page<Project> projects = projectService.getCompletedProjects(pageable);
			return ResponseEntity.ok(projects);
		} catch (Exception e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/all/in-progress")
	public ResponseEntity<?> getActiveProjects(@RequestParam(defaultValue = "0", required = false) Integer page,
			@RequestParam(defaultValue = "1000", required = false) Integer size, MessageDto dto) {
		try {
			Pageable pageable = PageRequest.of(page, size);

			Page<Project> projects = projectService.getActiveProjects(pageable);
			return ResponseEntity.ok(projects);
		} catch (Exception e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}

	@GetMapping("/employee/{eid}")
	public List<Project> getProjectByEmployeeId(@PathVariable int eid) {
		return projectService.getProjectByEmployeeId(eid);
	}

	@PostMapping("set/status/completed/{pid}")
	public ResponseEntity<?> markAsCompleted(@PathVariable int pid,MessageDto dto) {
		try {
			projectService.markAsCompleted(pid);
			return ResponseEntity.ok("Updated status successfully");
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}

	@GetMapping("/employee/stat")
	public List<ProjectEmployeeStatDto> getEmployeeCountByProjectType() {
		return projectService.getEmployeeCountByProjectType();
	}
	
	@GetMapping("/types")
	public List<ProjectType> getProjectType(){
		return projectService.getProjectType();
	}
	
	@GetMapping("/active-count")
	public int getActiveProjectCount() {
		return projectService.getActiveProjectCount();
	}
	
	@GetMapping("/completed-count")
	public int getCompletedProjectCount() {
		return projectService.getCompletedProjectCount();
	}
	
	@GetMapping("/upcoming-count")
	public int getUpcomingProjectCount() {
		return projectService.getUpcomingProjectCount();
	}
	
	@GetMapping("/overdue-count")
	public int getOverdueProjectCount() {
		return projectService.getOverdueProjectCount();
	}
	
	@PutMapping("/update-status/{id}")
    public ResponseEntity<?> updateProjectStatus(@PathVariable int id, @RequestParam String status,MessageDto dto) {
        try {
			projectService.updateProjectStatus(id, status);
			dto.setMsg("Project status updated successfully");
			return ResponseEntity.ok(dto);
		} catch (InvalidIdException e) {
			dto.setMsg("Failed to update Project Status");
			return ResponseEntity.badRequest().body(dto);
		}
        
    }

}
