package com.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthenticationFailedException extends RuntimeException {

	public AuthenticationFailedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AuthenticationFailedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AuthenticationFailedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
