package com.payroll_app.project.controller;

import java.security.Principal;

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
import com.payroll_app.project.exception.InputInvalidException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Work;
import com.payroll_app.project.service.WorkService;

@RestController
@RequestMapping("/work")
@CrossOrigin(origins = {"http://localhost:4200"})
public class WorkController {
	
	@Autowired
	private WorkService workService;
	
	@PostMapping("/assign/{eid}")
	public ResponseEntity<?> assignWork(@PathVariable int eid, @RequestBody Work work, Principal principal, MessageDto dto) {
		try {
			workService.validate(work);
			work = workService.assignWork(eid,work,principal.getName());
			return ResponseEntity.ok(work);
		} 
		catch (InputInvalidException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.status(e.getStatusCode()).body(dto);
		}
		catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		} 
		
	}
	
	
	@GetMapping("/get/{eid}")
	public Page<Work> getWork(@PathVariable int eid,
			@RequestParam(defaultValue ="0", required=false) Integer page,
			@RequestParam(defaultValue = "1000", required=false) Integer size){
		
		Pageable pageable = PageRequest.of(page, size);
		return workService.getWork(eid, pageable);
	}
	
	@GetMapping("/view")
	public Page<Work> viewWork(@RequestParam String username,
			@RequestParam(defaultValue ="0", required=false) Integer page,
			@RequestParam(defaultValue = "1000", required=false) Integer size){
		
		Pageable pageable = PageRequest.of(page, size);
		return workService.viewWork(username, pageable);
	}
	
	@GetMapping("get/id/{username}")
	public int getUserId(@RequestParam String username) {
		return workService.getUserId(username);
	}
	

}
