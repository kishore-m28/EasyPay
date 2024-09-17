package com.payroll_app.project.utility;

import org.springframework.stereotype.Component;

import com.payroll_app.project.model.Salary;

@Component
public class SalaryUtility {

	/*Not yet finished*/
	public void computeSalary(Salary s) {
		Salary sa=new Salary();
		double AnnualCTC=s.getAnnualCTC();
		double bonus=s.getBonus();
		
		double grossPay=s.getAnnualCTC()-s.getBonus();
		double basic=grossPay-s.getDa()-s.getHra()-s.getMa();
		double epf=0.12*(basic+s.getDa());
		double taxableIncome=grossPay-(s.getHra()-epf-s.getMa());
		double tax=s.getTaxRate()*taxableIncome;
		double deduction=s.getProffesionalTaxRate()-epf-tax;
		double netSalary=grossPay-deduction;
		
	}

}
