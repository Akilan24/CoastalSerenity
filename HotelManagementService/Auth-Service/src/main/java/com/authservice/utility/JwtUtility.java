package com.authservice.utility;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authservice.entity.Registration;
import com.authservice.repository.UserRepository;
import com.authservice.service.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtility {

	@Value("${jwtSecret}")
	private String jwtSecret;

	@Value("${jwtExpirationMs}")
	private int jwtExpirationMs;
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String saveUser(Registration credential) {
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		repository.save(credential);
		return "user added to the system";
	}

	public String generateToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
		Date now = new Date();
		return Jwts.builder().setSubject(username).claim("roles", roles).setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS256, jwtSecret)
				.compact();

	}

	public boolean validatieToken(String token) {
		parse(token);
		return true;
	}

	private Jws<Claims> parse(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
	}

	public String getUsername(String token) {
		return parse(token).getBody().getSubject();
	}

}
