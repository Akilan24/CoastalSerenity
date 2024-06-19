package com.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.constant.AuthConstant;
import com.authservice.entity.Registration;
import com.authservice.request.AuthRequest;
import com.authservice.request.RefreshTokenRequest;
import com.authservice.response.JwtResponse;
import com.authservice.service.UserService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping(AuthConstant.AUTH)
@CrossOrigin(AuthConstant.CROSS_ORIGIN)
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping(AuthConstant.SAVE_USER)
	public ResponseEntity<String> saveUser(@RequestBody Registration user) {
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
	}

	@PostMapping(AuthConstant.FORGOT_PASSWORD)
	public ResponseEntity<Boolean> forgotpassword(@PathVariable String toEmail) throws MessagingException {
		return new ResponseEntity<>(userService.forgotpassword(toEmail), HttpStatus.OK);
	}

	@PostMapping(AuthConstant.LOGIN)
	public ResponseEntity<JwtResponse> login(@RequestBody AuthRequest authRequest) {
		return new ResponseEntity<>(userService.login(authRequest), HttpStatus.OK);

	}

	@PostMapping(AuthConstant.LOGOUT)
	public ResponseEntity<String> logout(@RequestBody AuthRequest authRequest, @PathVariable String refreshToken) {
		return new ResponseEntity<>(userService.logout(authRequest, refreshToken), HttpStatus.OK);
	}

	@PostMapping(AuthConstant.REFRESH_TOKEN)
	public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		return new ResponseEntity<>(userService.refreshToken(refreshTokenRequest), HttpStatus.OK);
	}

}
