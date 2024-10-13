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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.dto.ProjectEmployeeStatDto;
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

	@GetMapping("/all")
	public ResponseEntity<?> getAllProject(@RequestParam(defaultValue = "0", required = false) Integer page,
			@RequestParam(defaultValue = "1000", required = false) Integer size, MessageDto dto) {
		try {
			Pageable pageable = PageRequest.of(page, size);

			Page<Project> projects = projectService.getAllProject(pageable);
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

}
