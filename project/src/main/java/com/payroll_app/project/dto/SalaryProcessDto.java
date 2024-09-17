package com.payroll_app.project.dto;

public class SalaryProcessDto {
	
	private String name;
	
	private String email;
	
	private double basicPay;
	
	private double overTimePay;
	
	private double bonus;
	
	private double hra;
	
	private double ma;
	
	private double da;
	
	private double taxDeduction;
	
	private double netPay;
	
	private String month;
	
	private String year;
	
	

	public SalaryProcessDto(String name, String email, double basicPay, double overTimePay, double bonus, double hra,
			double ma, double da, double taxDeduction, double netPay, String month, String year) {
		super();
		this.name = name;
		this.email = email;
		this.basicPay = basicPay;
		this.overTimePay = overTimePay;
		this.bonus = bonus;
		this.hra = hra;
		this.ma = ma;
		this.da = da;
		this.taxDeduction = taxDeduction;
		this.netPay = netPay;
		this.month = month;
		this.year = year;
	}

	public SalaryProcessDto() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
	}

	public double getOverTimePay() {
		return overTimePay;
	}

	public void setOverTimePay(double overTimePay) {
		this.overTimePay = overTimePay;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
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

	public double getDa() {
		return da;
	}

	public void setDa(double da) {
		this.da = da;
	}

	public double getTaxDeduction() {
		return taxDeduction;
	}

	public void setTaxDeduction(double taxDeduction) {
		this.taxDeduction = taxDeduction;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	
}
