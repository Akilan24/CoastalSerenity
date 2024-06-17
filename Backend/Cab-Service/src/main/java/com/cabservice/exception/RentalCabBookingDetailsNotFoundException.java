package com.cabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RentalCabBookingDetailsNotFoundException extends RuntimeException {

	public RentalCabBookingDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RentalCabBookingDetailsNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RentalCabBookingDetailsNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
