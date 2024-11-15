package com.payroll_app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidComplianceTypeException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Compliance;
import com.payroll_app.project.service.ComplianceService;

@RestController
@RequestMapping("/compliance")
public class ComplianceController {

	@Autowired
    private ComplianceService complianceService;
	
	@PostMapping("/add")
	public Compliance addCompliance(@RequestBody Compliance compliance){
		return complianceService.addCompliance(compliance);
	}

    @GetMapping("/minimum-wage/{employeeId}")
    public ResponseEntity<?> checkMinimumWageCompliance(@PathVariable int employeeId, MessageDto dto) {
			String complianceStatus;
			try {
				complianceStatus = complianceService.checkMinimumWageCompliance(employeeId);
				return ResponseEntity.ok(complianceStatus);
			} catch (InvalidComplianceTypeException e) {
				dto.setMsg(e.getMessage());
				return ResponseEntity.badRequest().body(dto);
			} catch (InvalidIdException e) {
				dto.setMsg(e.getMessage());
				return ResponseEntity.badRequest().body(dto);
			}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    @GetMapping("/bonus")
    public ResponseEntity<?> checkBonusCompliance(@RequestParam double bonusThreshold) {
        try {
            return ResponseEntity.ok(complianceService.checkBonusCompliance(bonusThreshold));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
	*/
	
}
