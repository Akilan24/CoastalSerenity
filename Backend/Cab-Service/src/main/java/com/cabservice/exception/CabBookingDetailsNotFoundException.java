package com.cabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CabBookingDetailsNotFoundException extends RuntimeException {

	public CabBookingDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CabBookingDetailsNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CabBookingDetailsNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
