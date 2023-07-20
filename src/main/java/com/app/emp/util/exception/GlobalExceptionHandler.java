package com.app.emp.util.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Map<String, String> opMap = new HashMap<>();
		ex.getBindingResult().getAllErrors()
		.forEach((err)->{
			String fieldName = ((FieldError)err).getField();
			String message = err.getDefaultMessage();
			opMap.put(fieldName, message);
		});
		
		return new ResponseEntity<>(opMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> hanldeResourceNotFoundException(ResourceNotFoundException exception,WebRequest request)
	{
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(new Date(),exception.getMessage(),request.getDescription(false))
				,HttpStatus.NOT_FOUND);
	}
	
	
}
