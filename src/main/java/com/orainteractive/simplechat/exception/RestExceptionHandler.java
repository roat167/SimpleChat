package com.orainteractive.simplechat.exception;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.orainteractive.simplechat.constant.SimpleChatConstant;
import com.orainteractive.simplechat.po.CustomErrorResponse;
import com.orainteractive.simplechat.po.Response;

@ControllerAdvice
public class RestExceptionHandler {
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(UserException.class)
	public ResponseEntity<CustomErrorResponse> exceptionCustomerHandler(Exception ex) {
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setMessage(SimpleChatConstant.VALIDATION_FAILED);
		errors.getErrors().put("UserException ", ex.getMessage());
		return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ChatException.class)
	public ResponseEntity<CustomErrorResponse> exceptionChatHandler(Exception ex) {
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setMessage(SimpleChatConstant.VALIDATION_FAILED);
		errors.getErrors().put("ChatException ", ex.getMessage());
		return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomErrorResponse> exceptionHandler(Exception ex) {
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setMessage(SimpleChatConstant.VALIDATION_FAILED);
		errors.getErrors().put("Malformed syntax, server can not uderstand the request ", ex.getMessage());
		return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomErrorResponse> processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		CustomErrorResponse errors = processFieldErrors(fieldErrors);
		errors.setMessage(SimpleChatConstant.VALIDATION_FAILED);
		return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateUsernameException.class)
	public ResponseEntity<CustomErrorResponse> duplicateUsernameExceptionHandler(DuplicateUsernameException ex) {
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setMessage(SimpleChatConstant.VALIDATION_FAILED);
		errors.getErrors().put("Username", ex.getMessage());
		return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.BAD_REQUEST);
	}

	private CustomErrorResponse processFieldErrors(List<FieldError> fieldErrors) {
		CustomErrorResponse errors = new CustomErrorResponse();

		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			errors.getErrors().put(fieldError.getField(), localizedErrorMessage);
		}

		return errors;
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

		// If the message was not found, return the most accurate field error
		// code instead.
		// You can remove this check if you prefer to get the default error
		// message.
		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}

		return localizedErrorMessage;
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Response> authExceptionHandler(AuthenticationException ex) {
		Response errors = new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		
		return new ResponseEntity<Response>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PermissionDeniedException.class)
	public ResponseEntity<Response> exceptionChatHandler(PermissionDeniedException ex) {
		Response errors = new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		
		return new ResponseEntity<Response>(errors, HttpStatus.BAD_REQUEST);
	}

}
