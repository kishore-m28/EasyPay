package com.payroll_app.project.exception;

public class JobTitleException extends Exception{

	private String message;

	public JobTitleException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
