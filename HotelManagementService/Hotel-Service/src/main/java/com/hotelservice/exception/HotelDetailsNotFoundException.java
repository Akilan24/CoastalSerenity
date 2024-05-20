package com.hotelservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelDetailsNotFoundException extends RuntimeException {

	public HotelDetailsNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public HotelDetailsNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public HotelDetailsNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
