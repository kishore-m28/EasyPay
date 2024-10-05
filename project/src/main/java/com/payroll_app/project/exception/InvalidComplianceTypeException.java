package com.payroll_app.project.exception;

public class InvalidComplianceTypeException extends Exception{

	private String message;

	public InvalidComplianceTypeException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
