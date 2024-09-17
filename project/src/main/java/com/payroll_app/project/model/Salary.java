package com.payroll_app.project.model;

import java.time.LocalDate;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Salary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private double AnnualCTC;

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
	
	private LocalDate payPeriodStartDate;
	
	private LocalDate payPeriodEndDate;
	
	private LocalDate createdAt;
	
	private LocalDate updatedAt;
	
	private String status;
	
	@ManyToOne
	private Employee employee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAnnualCTC() {
		return AnnualCTC;
	}

	public void setAnnualCTC(double annualCTC) {
		AnnualCTC = annualCTC;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}

	public double getLta() {
		return lta;
	}

	public void setLta(double lta) {
		this.lta = lta;
	}

	public LocalDate getPayPeriodStartDate() {
		return payPeriodStartDate;
	}

	public void setPayPeriodStartDate(LocalDate payPeriodStartDate) {
		this.payPeriodStartDate = payPeriodStartDate;
	}

	public LocalDate getPayPeriodEndDate() {
		return payPeriodEndDate;
	}

	public void setPayPeriodEndDate(LocalDate payPeriodEndDate) {
		this.payPeriodEndDate = payPeriodEndDate;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	 
}
