package com.payroll_app.project.exception;

public class NoEmployeesFoundException extends Exception {
    
    private String message;

	public NoEmployeesFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

