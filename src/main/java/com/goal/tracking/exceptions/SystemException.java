package com.goal.tracking.exceptions;

import org.springframework.http.HttpStatus;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = -4665703087968521081L;

	private HttpStatus httpStatus;
	
	public SystemException() {
		super();
	}
	
	public SystemException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
}
