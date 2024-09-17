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
import com.payroll_app.project.model.HRScoreSheet;
import com.payroll_app.project.service.HRScoreSheetService;


@RestController
@RequestMapping("/hr-scoresheet")
public class HRScoreSheetController {
	
	@Autowired
	private HRScoreSheetService hrScoreSheetService;
	
	@PostMapping("/update/{jid}")
	public ResponseEntity<?> updateHrScoreSheet(@PathVariable int jid, @RequestBody HRScoreSheet hrScoreSheet, MessageDto dto) {
		try {
			hrScoreSheet = hrScoreSheetService.updateHrScoreSheet(jid, hrScoreSheet);
			return ResponseEntity.ok(hrScoreSheet);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}

}
