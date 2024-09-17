package com.payroll_app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class TechnicalScoreSheetController {
	
	@Autowired
	private TechnicalScoreSheetService technicalScoreSheetService;
	
	@PostMapping("/update/{jid}")
	public ResponseEntity<?> updateTechScoreSheet(@PathVariable int jid, @RequestBody TechnicalScoreSheet technicalScoreSheet, MessageDto dto) {
		try {
			technicalScoreSheet = technicalScoreSheetService.updateTechScoreSheet(jid, technicalScoreSheet);
			return ResponseEntity.ok(technicalScoreSheet);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	

}
