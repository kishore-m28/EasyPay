package com.payroll_app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.service.ScreenTestService;

@RestController
@RequestMapping("/hr")
public class ScreenTestController {

    @Autowired
    private ScreenTestService screenTestService;

    
    
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
}
