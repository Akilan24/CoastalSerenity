package com.bookingdetailsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookingDetailsNotFoundException extends RuntimeException {

	public BookingDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BookingDetailsNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BookingDetailsNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
