package com.goal.tracking.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Error.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Error err, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), err.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(SystemException.class)
	public final ResponseEntity<ErrorDetails> handleAllSystemExceptions(SystemException sysEx, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), sysEx.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, sysEx.getHttpStatus());
	}
	
}
