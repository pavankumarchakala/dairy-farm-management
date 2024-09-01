package com.dairyfarm.interceptors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dairyfarm.dto.ErrorResponse;
import com.dairyfarm.exceptions.DairyfarmmanagementException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Pavankumar - created date : Sep 23, 2021
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionInterceptor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DairyfarmmanagementException.class)
	public ResponseEntity<ErrorResponse> dairyfarmmanagementExceptionHandler(DairyfarmmanagementException e,
			WebRequest webRequest) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(ErrorResponse.builder().message(e.getMessage()).status(e.getHttpStatus()).build(),
				e.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception e, WebRequest webRequest) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(
				ErrorResponse.builder().message(e.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> constraintViolationExceptionHandler() {
		return null;
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
	public ResponseEntity<ErrorResponse> sizeLimitExceededExceptionHandler(Exception e, WebRequest webRequest) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().message(e.getMessage()).status(HttpStatus.PAYLOAD_TOO_LARGE).build(),
				HttpStatus.PAYLOAD_TOO_LARGE);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> methodArgumentTypeMismatchException(Exception e, WebRequest webRequest) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.builder().message(e.getMessage()).status(HttpStatus.BAD_REQUEST).build(),
				HttpStatus.BAD_REQUEST);
	}

}
