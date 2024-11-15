package com.payroll_app.project.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll_app.project.exception.InvalidComplianceTypeException;
import com.payroll_app.project.exception.InvalidIdException;
import com.payroll_app.project.model.Compliance;
import com.payroll_app.project.model.Employee;
import com.payroll_app.project.model.EmployeeCompliance;
import com.payroll_app.project.model.Salary;
import com.payroll_app.project.repository.ComplianceRepository;
import com.payroll_app.project.repository.EmployeeComplianceRepository;
import com.payroll_app.project.repository.EmployeeRepository;
import com.payroll_app.project.repository.SalaryRepository;

@Service
public class ComplianceService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private SalaryRepository salaryRepository;

	@Autowired
	private ComplianceRepository complianceRepository;

	@Autowired
	private EmployeeComplianceRepository employeeComplianceRepository;

	public Compliance addCompliance(Compliance compliance) {
		return complianceRepository.save(compliance);
	}

	public String checkMinimumWageCompliance(int employeeId) throws InvalidIdException, InvalidComplianceTypeException {
		Optional<Employee> optional = employeeRepository.findById(employeeId);
		if (optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		Employee employee = optional.get();
		
		Salary latestSalary = salaryRepository.findLatestSalary(employeeId);

		double grossPay = latestSalary.getGrossPay();
		Optional<Compliance> complianceOpt = complianceRepository.findByComplianceType("Minimum_Wage");

		if(complianceOpt.isEmpty())
			throw new InvalidComplianceTypeException("Invalid Compliance Type"); 
		Compliance compliance = complianceOpt.get();
		double minimumWage = compliance.getThreshold();

		if (grossPay >= minimumWage) {
			updateEmployeeCompliance(employee, compliance, true);
			return "Compliant";
		} else {
			updateEmployeeCompliance(employee, compliance, false);
			return "Non-Compliant";
		}
	}

	private void updateEmployeeCompliance(Employee employee, Compliance compliance, boolean status) {
		EmployeeCompliance employeeCompliance = new EmployeeCompliance();
		employeeCompliance.setEmployee(employee);
		employeeCompliance.setCompliance(compliance);
		employeeCompliance.setComplianceStatus(status ? "Compliant" : "Non-Compliant");
		employeeCompliance.setComplianceLevel(status ? "100.0" : "0.0");
		employeeCompliance.setDateChecked(LocalDate.now());

		employeeComplianceRepository.save(employeeCompliance);
	}

}
