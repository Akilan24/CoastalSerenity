package com.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.authservice.entity.Registration;
import com.authservice.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Registration user = userrepo.findByUsername(username).get();
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found with username " + username);
		}
		return UserDetailsImpl.getUser(user);
	}
}
