package com.payroll_app.project.utility;


import com.payroll_app.project.model.Salary;
import java.time.LocalDate;
import org.springframework.stereotype.Component;
import com.payroll_app.project.model.Salary;
 
 
@Component
public class SalaryUtility {

	/*Not yet finished*/
	public Salary computeSalary(Salary s) {
		
		// Check if the provided Salary object is null
	    if (s == null) {
	        throw new IllegalArgumentException("Provided Salary object is null");
	    }
		
		Salary sa=new Salary();
		
		double grossPay=(s.getAnnualCTC()-s.getBonus())*(1/12);
		double basic=grossPay-s.getDa()-s.getHra()-s.getMa();
		double epf=0.12*(basic+s.getDa());
		double taxableIncome=grossPay-(s.getHra()-epf-s.getMa());
		double tax=s.getTaxRate()*taxableIncome;
		double deduction=s.getProffesionalTaxRate()-epf-tax;
		double netSalary=grossPay-deduction;
		
		
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
		sa.setAnnualNetPay(netSalary);
		sa.setMonthlyNetPay(netSalary/12);
		sa.setCreatedAt(LocalDate.now());
		return sa;
		
	}

}
