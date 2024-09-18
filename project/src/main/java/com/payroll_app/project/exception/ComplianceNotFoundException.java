package com.payroll_app.project.exception;

public class ComplianceNotFoundException extends RuntimeException {
    
    String message;
	
    public ComplianceNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

