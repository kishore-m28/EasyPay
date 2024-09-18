package com.payroll_app.project.dto;

public class SalaryProcessDto {
	
	private int id;
	
	private String name;
	
	private String contact;
	
	private double bonus;
	
	private double basic;
	
	private double hra;
	
	private double ma;
	
	private double lta;
	
	private double da;
	
	private double taxRate;
	
	private double taxableIncome;
	
	private double proffesionalTaxRate;
	
	private double grossPay;
	
	private double annualNetPay;
	
	private double monthlyNetPay;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}

	public double getHra() {
		return hra;
	}

	public void setHra(double hra) {
		this.hra = hra;
	}

	public double getMa() {
		return ma;
	}

	public void setMa(double ma) {
		this.ma = ma;
	}

	public double getLta() {
		return lta;
	}

	public void setLta(double lta) {
		this.lta = lta;
	}

	public double getDa() {
		return da;
	}

	public void setDa(double da) {
		this.da = da;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public double getTaxableIncome() {
		return taxableIncome;
	}

	public void setTaxableIncome(double taxableIncome) {
		this.taxableIncome = taxableIncome;
	}

	public double getProffesionalTaxRate() {
		return proffesionalTaxRate;
	}

	public void setProffesionalTaxRate(double proffesionalTaxRate) {
		this.proffesionalTaxRate = proffesionalTaxRate;
	}

	public double getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}

	public double getAnnualNetPay() {
		return annualNetPay;
	}

	public void setAnnualNetPay(double annualNetPay) {
		this.annualNetPay = annualNetPay;
	}

	public double getMonthlyNetPay() {
		return monthlyNetPay;
	}

	public void setMonthlyNetPay(double monthlyNetPay) {
		this.monthlyNetPay = monthlyNetPay;
	}

	
}
