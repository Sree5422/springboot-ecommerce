package com.ecommerce.user.exceptions;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
		String path=request.getDescription(false).replace("uri=", "");
		
		ErrorResponse errorResponse= new ErrorResponse( HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage(),path);
		System.out.println(errorResponse);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(errorResponse);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	
	public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request){
		String path= request.getDescription(false).replace("uri=", "");
		String errorMessage = ex.getBindingResult()
		.getFieldErrors()
		.stream()
		.map(error->error.getField()+" : "+error.getDefaultMessage())
		.collect(Collectors.joining(", "));
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),errorMessage,path);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	
	public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex, WebRequest request){
		String path=request.getDescription(false).replace("uri=", "");
		//System.out.println(path);
		ErrorResponse errorResponse= new ErrorResponse( HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage(),path);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(errorResponse);
	}
	@ExceptionHandler(AccountNotActiveException.class)
	
	public ResponseEntity<ErrorResponse> handleAccountNotActiveException(AccountNotActiveException ex, WebRequest request){
		String path=request.getDescription(false).replace("uri=", "");
		//System.out.println(path);
		ErrorResponse errorResponse= new ErrorResponse( HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), ex.getMessage(),path);
		System.out.println(errorResponse);

		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(errorResponse);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request){
		System.out.println(">>> HttpMessageNotReadableException handler called");
		String path = request.getDescription(false).replace("uri=", "");
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), path);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body(errorResponse);
	}
	
	@ExceptionHandler(InvalidUserException.class)
	
	public ResponseEntity<ErrorResponse> handleInvalidUserException(InvalidUserException ex,WebRequest request){
		String path = request.getDescription(false).replace("uri=", "");
	   ErrorResponse errorResponse= new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase(),ex.getMessage(),path);
	   return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			   									.body(errorResponse);
	}

}
