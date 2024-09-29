package com.payroll_app.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.ComplianceNotFoundException;
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

    public void updateComplianceReport(int complianceId) {
        // Fetch the compliance entity to get the threshold
        Compliance compliance = complianceRepository.findById(complianceId)
            .orElseThrow(() -> new ComplianceNotFoundException("Compliance with ID " + complianceId + " not found"));

        double threshold = compliance.getThreshold(); // Get the minimum wage threshold

        // Fetch only active employees
        List<Employee> activeEmployees = employeeRepository.findByStatus("ACTIVE"); 
        
        
     // Collect employee IDs
        List<Integer> employeeIds = activeEmployees.stream()
            .map(Employee::getId) // Assuming you have a method getId() to get the employee's ID
            .collect(Collectors.toList());

        // Fetch salaries for these employees
        List<Salary> salaries = salaryRepository.findByEmployeeIdIn(employeeIds);

        // Create a map to access salaries by employee ID
        Map<Integer, Double> salaryMap = salaries.stream()
                .collect(Collectors.toMap(
                    s -> s.getEmployee().getId(),   // Key extractor: employee ID
                    Salary::getMonthlyNetPay));
        
     // Calculate compliance counts
        long complianceCount = activeEmployees.stream()
            .filter(emp -> salaryMap.getOrDefault(emp.getId(), 0.0) >= threshold)
            .count();

        long nonComplianceCount = activeEmployees.size() - complianceCount; // Non-compliant employees

        // Calculate the overall compliance level as a percentage
        double overallComplianceLevel = (double) complianceCount / activeEmployees.size() * 100;

        // Fetch or create a new compliance report for the given complianceId
        ComplianceReport report = complianceReportRepository.findByComplianceId(complianceId)
            .orElse(new ComplianceReport());

        // Update the report fields
        report.setComplianceCount((int) complianceCount);
        report.setNonComplianceCount((int) nonComplianceCount);
        report.setOverallComplianceLevel(overallComplianceLevel);
        report.setReportDate(LocalDate.now()); // Set the current date

        report.setCompliance(compliance);
        // Save the updated report
        complianceReportRepository.save(report);
    }
}

