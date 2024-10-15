package com.payroll_app.project.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenderStatDto {
	
	private List<String> gender = Arrays.asList("FEMALE","MALE");
	
	private List<Integer> count;
	
	private List<Integer> percentage;

	public List<String> getGender() {
		return gender;
	}

	public void setGender(List<String> gender) {
		this.gender = gender;
	}

	public List<Integer> getCount() {
		return count;
	}

	public void setCount(List<Integer> count) {
		this.count = count;
	}

	public List<Integer> getPercentage() {
		return percentage;
	}

	public void setPercentage(List<Integer> percentage) {
		this.percentage = percentage;
	}
	
	
}
