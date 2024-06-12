package com.authservice.service;

import org.springframework.stereotype.Service;

import com.authservice.entity.Registration;
import com.authservice.request.AuthRequest;
import com.authservice.request.RefreshTokenRequest;
import com.authservice.response.JwtResponse;

import jakarta.mail.MessagingException;

@Service
public interface UserService {

	public String saveUser(Registration user);
	
	public Boolean forgotpassword(String toEmail) throws MessagingException;
	
	public JwtResponse login( AuthRequest authRequest); 
	
	public String logout(AuthRequest authRequest,String refreshToken);
	
	public JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
