package com.payroll_app.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.dto.RecruitDashBoardDto;
import com.payroll_app.project.repository.JobApplicationRepository;

@Service
public class RecruitDashBoardService {
	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	public List<RecruitDashBoardDto> recruitDashBoard() {
        List<Object[]> results = jobApplicationRepository.getJobApplicationStats();
        List<RecruitDashBoardDto> stats = new ArrayList<>();

        for (Object[] row : results) {
        	Integer jobCount = ((Long) row[0]).intValue(); 
            Integer applicationCount = ((Long) row[1]).intValue(); 
            Integer seekerCount = ((Long) row[2]).intValue();
            String jobTitle = (String) row[3];

            RecruitDashBoardDto dto = new RecruitDashBoardDto(jobCount, applicationCount, seekerCount, jobTitle);
            stats.add(dto);
        }

        return stats;

}

	
	
	
}
