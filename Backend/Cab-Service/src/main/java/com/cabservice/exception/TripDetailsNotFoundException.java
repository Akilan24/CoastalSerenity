package com.cabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TripDetailsNotFoundException extends RuntimeException {

	public TripDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TripDetailsNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TripDetailsNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
