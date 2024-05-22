package com.userservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userservice.entity.Registration;
import com.userservice.entity.Traveller;
import com.userservice.exception.UserDetailsNotFoundException;
import com.userservice.repo.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userrepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Registration updateUser(String username, Registration u) {
		if (userrepo.existsById(username)) {
			Optional<Registration> user = userrepo.findById(username);
			user.get().setAddress(u.getAddress());
			user.get().setEmail(u.getEmail());
			user.get().setMobile(u.getMobile());
			user.get().setName(u.getName());
			user.get().setGender(u.getGender());
			user.get().setMaritalStatus(u.getMaritalStatus());
			log.info("User details of username: " + username + " got updated");
			return userrepo.save(user.get());
		} else {
			log.info("User details of username: " + username + " are not found");
			throw new UserDetailsNotFoundException("User details are not found");
		}
	}

	@Override
	public String removeUser(String username) {

		if (userrepo.existsById(username)) {
			userrepo.deleteById(username);
			log.info("User details of username: " + username + " are deleted");
			return "User details are deleted";
		} else {
			log.info("User details of username: " + username + " are not found");
			throw new UserDetailsNotFoundException("User details are not found");
		}
	}

	@Override
	public List<Registration> ShowAllUser() {
		List<Registration> list = userrepo.findAll();
		if (list.isEmpty()) {
			log.info("User Details are not found");
			throw new UserDetailsNotFoundException("User details are not found");
		}
		log.info("User Details are found");
		return list;
	}

	@Override
	public Registration ShowUserByUserName(String username) {
		if (userrepo.findByUsername(username).isPresent()) {
			Registration u = userrepo.findByUsername(username).get();
			return u;
		} else
			throw new UserDetailsNotFoundException("User details are not found");

	}

	@Override
	public Registration ShowUserByEmail(String email) {
		if (userrepo.findByEmail(email).isPresent()) {
			Registration u = userrepo.findByEmail(email).get();
			return u;
		} else
			throw new UserDetailsNotFoundException("User details are not found");

	}

	@Override
	public Registration ShowUserByMobileNumber(String mobile) {
		if (userrepo.findByMobile(mobile).isPresent()) {
			Registration u = userrepo.findByMobile(mobile).get();
			return u;
		} else
			throw new UserDetailsNotFoundException("User details are not found");

	}

	@Override
	public String updateUserpasswordbyusername(String username, String password) {

		if (userrepo.findById(username).isPresent()) {
			Registration u = userrepo.findById(username).get();
			u.setPassword(passwordEncoder.encode(password));
			userrepo.save(u);
			return "User password updated";
		} else
			throw new UserDetailsNotFoundException("User details are not found");

	}

	@Override
	public String addtraveller(String username, Traveller t) {
		if (userrepo.existsById(username)) {
			Registration r = userrepo.findByUsername(username).get();
			List<Traveller> l = r.getTraveller();
			l.add(t);
			r.setTraveller(l);
			userrepo.save(r);
			log.info("Traveller details of username: " + username + " got added");
			return "Traveller added";
		} else {
			log.info("User details of username: " + username + " are not found");
			throw new UserDetailsNotFoundException("User details are not found");
		}
	}

	@Override
	public String updatetraveller(String username, Traveller t) {
		if (userrepo.existsById(username)) {
			Registration r = userrepo.findByUsername(username).get();
			List<Traveller> l = r.getTraveller().stream().filter(n -> !n.getName().equals(t.getName()))
					.collect(Collectors.toList());
			l.add(t);
			r.setTraveller(l);
			userrepo.save(r);
			log.info("Traveller details of username: " + username + " got updated");
			return "Traveller updated";
		} else {
			log.info("User details of username: " + username + " are not found");
			throw new UserDetailsNotFoundException("User details are not found");
		}
	}

	@Override
	public String deletetraveller(String username, String name) {
		if (userrepo.existsById(username)) {
			Registration r = userrepo.findByUsername(username).get();
			List<Traveller> l = r.getTraveller().stream().filter(n -> !n.getName().equals(name))
					.collect(Collectors.toList());
			System.out.println(l.toString());
			r.setTraveller(l);
			userrepo.save(r);
			log.info("Traveller details of username: " + username + " got deleted");
			return "Traveller deleted";
		} else {
			log.info("User details of username: " + username + " are not found");
			throw new UserDetailsNotFoundException("User details are not found");
		}
	}

	@Override
	public List<Traveller> getTravellerByUsername(String username) {
		if (userrepo.findByUsername(username).isPresent()) {
			List<Traveller> list = userrepo.findByUsername(username).get().getTraveller();
			if (!list.isEmpty())
				return list;
			else
				throw new UserDetailsNotFoundException("Travellers are not found");
		} else
			throw new UserDetailsNotFoundException("User details are not found");

	}

}
