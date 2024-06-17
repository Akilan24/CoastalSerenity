package com.cabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RentalPackageDetailsNotFoundException extends RuntimeException {

	public RentalPackageDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RentalPackageDetailsNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RentalPackageDetailsNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
