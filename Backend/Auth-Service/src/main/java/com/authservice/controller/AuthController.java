package com.authservice.controller;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.ProxyEntity.Hotel;
import com.authservice.entity.RefreshToken;
import com.authservice.entity.Registration;
import com.authservice.exception.AuthenticationFailedException;
import com.authservice.proxyController.HotelProxyController;
import com.authservice.repository.RefreshTokenRepository;
import com.authservice.repository.UserRepository;
import com.authservice.request.AuthRequest;
import com.authservice.request.RefreshTokenRequest;
import com.authservice.response.JwtResponse;
import com.authservice.service.RefreshTokenService;
import com.authservice.utility.JwtUtility;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping("/HMA/Auth")
@CrossOrigin("http://localhost:5173")
public class AuthController {
	@Value("${spring.mail.username}")
	String fromEmail;

	@Autowired
	PasswordEncoder pass;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtility jwtutil;

	@Autowired
	RefreshTokenService refreshToken;

	@Autowired
	RefreshTokenRepository refreshTokenRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	private JavaMailSender mailSender;

	@PostMapping("/register")
	public ResponseEntity<String> addNewUser(@RequestBody Registration user) {
		System.out.println(user.toString());
		return new ResponseEntity<>(jwtutil.saveUser(user), HttpStatus.OK);

	}

	@PostMapping("/forgotpassword/{toEmail}")
	public ResponseEntity<Boolean> forgotpassword(@PathVariable String toEmail) throws MessagingException {
		Optional<Registration> reg = userRepo.findByEmail(toEmail);
		if (reg != null) {
			String password = generatePassword(8, 12);
			reg.get().setPassword(password);
			userRepo.save(reg.get());
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			String emailBody = "<html><body><h3>Hi " + reg.get().getUsername() + ",</h3><p>Your reset password: <b>"
					+ password + "</b>. Use it to login and reset your password in the below link.</p>"
					+ "<a href=\'http://localhost:5173/resetpassword\'>Reset Password link</a></body></html>";

			helper.setText(emailBody, true);
			helper.setSubject("Coastal Serenity - Reset Password");
			helper.setFrom(fromEmail);
			helper.setTo(toEmail);

			mailSender.send(message);
			return new ResponseEntity<>(true, HttpStatus.OK);

		}
		return new ResponseEntity<>(false, HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody AuthRequest authRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			System.out.println(userRepo.findByUsername(authRequest.getUsername()));
			Optional<RefreshToken> r = refreshTokenRepo.findByUsername(authRequest.getUsername());
			List<RefreshToken> refreshTokenList=refreshTokenRepo.findAll().stream().filter(t->t.getUsername().equalsIgnoreCase(authRequest.getUsername())).collect(Collectors.toList());	
			for(RefreshToken rt:refreshTokenList) {
				refreshTokenRepo.deleteById(rt.getId());			}
			if (r.isPresent()) {
				refreshTokenRepo.deleteById(r.get().getId());
			}
			if (authentication.isAuthenticated()) {
				return ResponseEntity.ok(JwtResponse.builder().accessToken(jwtutil.generateToken(authentication))
						.refreshToken(refreshToken.createRefreshToken(authRequest.getUsername()).getRefreshToken())
						.build());
			} else {
				throw new AuthenticationFailedException("Wrong Credentials");
			}
		} catch (Exception e) {
			throw new AuthenticationFailedException("Invalid Credentials");
		}

	}

	@PostMapping("/logout/{refreshToken}")
	public ResponseEntity<String> logout(@RequestBody AuthRequest authRequest, @PathVariable String refreshToken) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			refreshTokenRepo.deleteById(refreshTokenRepo.findByRefreshToken(refreshToken).get().getId());
			return new ResponseEntity<>("Logged out Successfully", HttpStatus.OK);

		} else {
			throw new AuthenticationFailedException("Invalid Credentials");
		}

	}

	@PostMapping("/refreshToken")
	public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

		RefreshToken rt = refreshToken.findByToken(refreshTokenRequest.getRefreshToken()).get();
		Registration r = userRepo.findByUsername(rt.getUsername()).get();
		if (refreshToken.verifyExpiration(rt)) {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(r.getUsername(), r.getPassword()));
			if (!authentication.isAuthenticated()) {
				throw new AuthenticationFailedException("Refresh token is not in database!");
			}
			return new ResponseEntity<>(JwtResponse.builder().accessToken(jwtutil.generateToken(authentication))
					.refreshToken(refreshTokenRequest.getRefreshToken()).build(), HttpStatus.OK);
		} else {
			throw new AuthenticationFailedException("Refresh token is not in database!");
		}

	}

	public static String generatePassword(int minLength, int maxLength) {
		String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
		String CHAR_UPPER = CHAR_LOWER.toUpperCase();
		String NUMBER = "0123456789";
		String SPECIAL_CHAR = "@#$%^&+=!";

		String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + SPECIAL_CHAR;
		SecureRandom random = new SecureRandom();
		if (minLength < 8 || maxLength < minLength) {
			throw new IllegalArgumentException("Invalid password length range");
		}

		int length = minLength + random.nextInt(maxLength - minLength + 1);

		StringBuilder password = new StringBuilder(length);
		password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
		password.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
		password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
		password.append(SPECIAL_CHAR.charAt(random.nextInt(SPECIAL_CHAR.length())));

		for (int i = 4; i < length; i++) {
			password.append(PASSWORD_ALLOW_BASE.charAt(random.nextInt(PASSWORD_ALLOW_BASE.length())));
		}

		char[] passwordChars = password.toString().toCharArray();
		for (int i = 0; i < passwordChars.length; i++) {
			int randomIndex = random.nextInt(passwordChars.length);
			char temp = passwordChars[i];
			passwordChars[i] = passwordChars[randomIndex];
			passwordChars[randomIndex] = temp;
		}

		return new String(passwordChars);
	}
}
