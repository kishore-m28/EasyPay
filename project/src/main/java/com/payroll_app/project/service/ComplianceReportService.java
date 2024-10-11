package com.payroll_app.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.ComplianceNotFoundException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Compliance;
import com.payroll_app.project.model.ComplianceReport;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.repository.ComplianceReportRepository;
import com.payroll_app.project.repository.ComplianceRepository;
import com.payroll_app.project.repository.EmployeeRepository;
import com.payroll_app.project.repository.SalaryRepository;

@Service
public class ComplianceReportService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private ComplianceReportRepository complianceReportRepository;

    @Autowired
    private ComplianceRepository complianceRepository;

    public void updateComplianceReport(int complianceId) throws InvalidIdException {
        Optional<Compliance> opt = complianceRepository.findById(complianceId);
        if(opt.isEmpty())
        		throw new InvalidIdException("Invalid Compliance Id Given");

        Compliance compliance = opt.get();
        
        double threshold = compliance.getThreshold(); 

        List<Integer> activeEmployeeIds = employeeRepository.findActiveEmployee();
        
        System.out.println(activeEmployeeIds.toString());

        List<Double> grossPayList = salaryRepository.grossPayOfEach(activeEmployeeIds);
        
        long complianceCount = grossPayList.stream()
                .filter(g -> g >= threshold)
                .count();

        long nonComplianceCount = activeEmployeeIds.size() - complianceCount; 

        double overallComplianceLevel = (double) complianceCount / activeEmployeeIds.size() * 100;

        ComplianceReport report = complianceReportRepository.findByComplianceId(complianceId)
            .orElse(new ComplianceReport());

        report.setComplianceCount((int) complianceCount);
        report.setNonComplianceCount((int) nonComplianceCount);
        report.setOverallComplianceLevel(overallComplianceLevel);
        report.setReportDate(LocalDate.now()); 

        report.setCompliance(compliance);
        
        complianceReportRepository.save(report);
        System.out.println("Compliance Report generated");
    }

	public double getComplianceReport(int complianceId) throws InvalidIdException {
		Optional<Compliance> opt = complianceRepository.findById(complianceId);
        if(opt.isEmpty())
        		throw new InvalidIdException("Invalid Compliance Id Given");

        Compliance compliance = opt.get();
        
        double threshold = compliance.getThreshold(); 

        List<Integer> activeEmployeeIds = employeeRepository.findActiveEmployee();
        
        System.out.println(activeEmployeeIds.toString());

        List<Double> grossPayList = salaryRepository.grossPayOfEach(activeEmployeeIds);
        
        long complianceCount = grossPayList.stream()
                .filter(g -> g >= threshold)
                .count();

        long nonComplianceCount = activeEmployeeIds.size() - complianceCount; 

        double overallComplianceLevel = (double) complianceCount / activeEmployeeIds.size() * 100;
        
        return overallComplianceLevel;
	}
}

