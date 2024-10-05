package com.payroll_app.project.exception;

public class InputInvalidException extends Exception  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	private int statusCode = 304;

	public InputInvalidException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
	
	

}
