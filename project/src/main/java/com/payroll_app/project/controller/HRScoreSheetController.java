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
import com.payroll_app.project.model.HRScoreSheet;
import com.payroll_app.project.service.HRScoreSheetService;


@RestController
@RequestMapping("/hr-scoresheet")
@CrossOrigin(origins = {"http://localhost:4200"})
public class HRScoreSheetController {
	
	@Autowired
	private HRScoreSheetService hrScoreSheetService;
	
	private Logger logger = LoggerFactory.getLogger(HRScoreSheetController.class);
	
	@PostMapping("/update/{aid}")
	public ResponseEntity<?> updateHrScoreSheet(@PathVariable int aid, @RequestBody HRScoreSheet hrScoreSheet, MessageDto dto) {
		try {
			hrScoreSheet = hrScoreSheetService.updateHrScoreSheet(aid, hrScoreSheet);
			logger.info("Hr Scoresheet updated for application with id: "+aid);
			return ResponseEntity.ok(hrScoreSheet);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			logger.error("Could not find application with id: "+aid);
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/status/all")
	public List<HRScoreSheet> getHrStatus(){
		return hrScoreSheetService.getHrStatus();
	} 

}
