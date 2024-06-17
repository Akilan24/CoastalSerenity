package com.cabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RentalCabDetailsNotFoundException extends RuntimeException {

	public RentalCabDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RentalCabDetailsNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RentalCabDetailsNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
