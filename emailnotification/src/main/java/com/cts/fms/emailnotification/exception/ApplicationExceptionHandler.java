package com.cts.fms.emailnotification.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<String> handleException(ApplicationException excp) {
		List<String> details = new ArrayList<>();
        details.add(excp.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
