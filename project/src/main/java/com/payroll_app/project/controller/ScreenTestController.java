package com.payroll_app.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.payroll_app.project.dto.MessageDto;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.JobApplication;
import com.payroll_app.project.service.ScreenTestService;

@RestController
@RequestMapping("/hr")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ScreenTestController {

    @Autowired
    private ScreenTestService screenTestService;
    
    @GetMapping("/screentest/{appId}")
    public ResponseEntity<?> compareSkillsandExperience(@PathVariable int appId,MessageDto dto) {
    	try {  
            boolean isFound = screenTestService.compareSkillsandExperience(appId);  
            JobApplication jobApplication;
            if (isFound)  
            	jobApplication = screenTestService.updateApplication(appId, "CLEARED");         
            else
            	jobApplication = screenTestService.updateApplication(appId, "REJECTED");
            return ResponseEntity.ok(jobApplication);
        } catch (InvalidIdException e) {   
              return ResponseEntity.badRequest().body(dto);
        }
    }
    

}
   
   
