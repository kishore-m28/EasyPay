package com.payroll_app.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.TechnicalScoreSheet;
import com.payroll_app.project.service.TechnicalScoreSheetService;

@RestController
@RequestMapping("/tech-scoresheet")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TechnicalScoreSheetController {
	
	@Autowired
	private TechnicalScoreSheetService technicalScoreSheetService;
	
	private Logger logger = LoggerFactory.getLogger(TechnicalScoreSheetController.class);
	
	@PostMapping("/update/{aid}")
	public ResponseEntity<?> updateTechScoreSheet(@PathVariable int aid, @RequestBody TechnicalScoreSheet technicalScoreSheet, MessageDto dto) {
		try {
			technicalScoreSheet = technicalScoreSheetService.updateTechScoreSheet(aid, technicalScoreSheet);
			logger.info(" Tech Scoresheet updated for application with id: "+aid);
			return ResponseEntity.ok(technicalScoreSheet);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage()); 
			logger.error("Could not find application with id: "+aid);
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/status/all")
	public List<TechnicalScoreSheet> getTechStatus(){
		return technicalScoreSheetService.getTechStatus();
	}
	
	

}
