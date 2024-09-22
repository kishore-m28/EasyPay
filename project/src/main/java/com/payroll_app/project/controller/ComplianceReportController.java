package com.payroll_app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.exception.ComplianceNotFoundException;
import com.payroll_app.project.service.ComplianceReportService;

@RestController
@RequestMapping("/compliance-report")
public class ComplianceReportController {

    @Autowired
    private ComplianceReportService complianceReportService;

    @PostMapping("/generate/{complianceId}")
    public ResponseEntity<String> updateComplianceReport(@PathVariable int complianceId) {
        try {
            complianceReportService.updateComplianceReport(complianceId);
            return new ResponseEntity<>("Compliance report updated successfully!", HttpStatus.OK);
        } catch (ComplianceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

