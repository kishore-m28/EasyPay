package com.payroll_app.project.dto;

import org.springframework.stereotype.Component;

@Component
public class TokenDto {
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
