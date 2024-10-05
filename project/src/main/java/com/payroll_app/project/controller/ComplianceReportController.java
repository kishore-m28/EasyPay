package com.payroll_app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.ComplianceNotFoundException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.service.ComplianceReportService;

@RestController
@RequestMapping("/compliance-report")
public class ComplianceReportController {

    @Autowired
    private ComplianceReportService complianceReportService;

    @PostMapping("/generate/{complianceId}")
    public ResponseEntity<?> updateComplianceReport(@PathVariable int complianceId, MessageDto dto ) {
        try {
            complianceReportService.updateComplianceReport(complianceId);
            return ResponseEntity.ok(null);
        } catch (InvalidIdException e) {
        	dto.setMsg(e.getMessage());
        	return ResponseEntity.badRequest().body(dto);
        }
    }
}

