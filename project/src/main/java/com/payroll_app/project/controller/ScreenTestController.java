package com.payroll_app.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.repository.JobApplicationRepository;
import com.payroll_app.project.service.ScreenTestService;

@RestController
@RequestMapping("/hr")
public class ScreenTestController {

    @Autowired
    private ScreenTestService screenTestService;
    
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    
    

    
    
    @PostMapping("/screentest/experience/{appId}")
    public ResponseEntity<?> compareExperience(@PathVariable int appId,MessageDto dto) {
    	try {
           
        
            boolean isFound = screenTestService.compareApplication(appId);
           
            if (isFound) {
            	
                    
                return ResponseEntity.ok("Application matches");
                
            } 
                   
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Application does not match.");
            }
        } catch (InvalidIdException e) {
           
              return ResponseEntity.badRequest().body(dto);
        }
    }
    
    @PostMapping("/screentest/skills/{appId}")
    public ResponseEntity<?> compareSkills(@PathVariable int appId,MessageDto dto) {
        try {
            boolean isFound = screenTestService.compareSkills(appId);

            if (isFound) {
                return ResponseEntity.ok("Application matches");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Application does not match.");
            }
        } catch (InvalidIdException e) {
            return ResponseEntity.badRequest().body("Invalid application ID: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    }
   
   
