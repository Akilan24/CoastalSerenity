package com.authservice.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.authservice.entity.RefreshToken;
import com.authservice.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {

	@Value("${refreshTokenExpirationMs}")
	private int refreshTokenExpirationMs;
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	public RefreshToken createRefreshToken(String username) {

		RefreshToken refreshToken = RefreshToken.builder().username(username).refreshToken(UUID.randomUUID().toString())
				.expiryDate(Instant.now().plusMillis(refreshTokenExpirationMs)).build();
		return refreshTokenRepository.save(refreshToken);
	}

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByRefreshToken(token);
	}

	public Boolean verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new RuntimeException(
					token.getRefreshToken() + " Refresh token was expired. Please make a new signin request");
		}
		return true;
	}

}