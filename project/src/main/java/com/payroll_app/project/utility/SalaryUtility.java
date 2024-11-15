package com.payroll_app.project.utility;


import com.payroll_app.project.model.Salary;
import java.time.LocalDate;
import org.springframework.stereotype.Component;
import com.payroll_app.project.model.Salary;
 
 
@Component
public class SalaryUtility {

	public Salary computeSalary(Salary s) {
		
		// Check if the provided Salary object is null
	    if (s == null) {
	        throw new IllegalArgumentException("Provided Salary object is null");
	    }
		
		Salary sa=new Salary();
		
		System.out.println(s.toString());
		
		double grossPay=(s.getAnnualCTC()-s.getBonus());
		double basic=grossPay-s.getDa()-s.getHra()-s.getMa()-s.getLta();
		double epf=0.12*(basic+s.getDa());
		double taxableIncome=grossPay-s.getHra()-epf-s.getMa();
		double tax=s.getTaxRate()*taxableIncome;
		double deduction=(s.getProffesionalTaxRate()*taxableIncome)+epf+tax;
		double netSalary=grossPay-deduction;
		long netSalaryRounded = Math.round(netSalary);
		
		sa.setAnnualCTC(s.getAnnualCTC());
		sa.setBonus(s.getBonus());
		sa.setBasic(basic);
		sa.setHra(s.getHra());
		sa.setMa(s.getMa());
		sa.setLta(s.getLta());
		sa.setDa(s.getDa());
		sa.setTaxRate(s.getTaxRate());
		sa.setTaxableIncome(taxableIncome);
		sa.setProffesionalTaxRate(s.getProffesionalTaxRate());
		sa.setGrossPay(grossPay);
		sa.setAnnualNetPay(netSalaryRounded);
		sa.setMonthlyNetPay(netSalaryRounded/12);
		sa.setCreatedAt(LocalDate.now());
		sa.setStatus("COMPUTED");
		sa.setEmployee(s.getEmployee());
		return sa;
		
	}
 

}
