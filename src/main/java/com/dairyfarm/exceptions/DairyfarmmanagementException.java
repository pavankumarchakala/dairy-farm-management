package com.dairyfarm.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Pavankumar - created date : Sep 23, 2021
 */
@Setter
@Getter
public class DairyfarmmanagementException extends RuntimeException {

	private static final long serialVersionUID = 3849807106163417698L;

	private HttpStatus httpStatus;

	private String message;

	public DairyfarmmanagementException() {
		super();
	}

	public DairyfarmmanagementException(Throwable t) {
		super(t);
	}

	public DairyfarmmanagementException(String message) {
		super(message);
		this.message = message;
	}

	public DairyfarmmanagementException(String message, HttpStatus status) {
		super(message);
		this.message = message;
		this.httpStatus = status;
	}

	public DairyfarmmanagementException(String message, Throwable t) {
		super(message, t);
		this.message = message;
	}

}
