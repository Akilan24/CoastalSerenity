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
import com.userservice.repository.TravellerRepository;
import com.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userrepo;

	@Autowired
	TravellerRepository travellerrepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Registration updateUserByUserName(String username, Registration u) {
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
	public String removeUserByUserName(String username) {

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
	public String updateUserPasswordByUserName(String username, String password) {
		if (userrepo.findById(username).isPresent()) {
			Registration u = userrepo.findById(username).get();
			u.setPassword(passwordEncoder.encode(password));
			userrepo.save(u);
			return "User password updated";
		} else
			throw new UserDetailsNotFoundException("User details are not found");

	}

	@Override
	public String addTravellerByUserName(String username, Traveller traveller) {
		Optional<Registration> res = userrepo.findByUsername(username);
		if (!res.isEmpty()) {
			res.get().getTraveller().add(traveller);
			userrepo.save(res.get());
			log.info("Traveller details of username: " + username + " got added");
			return "Traveller added";
		} else {
			log.info("User details of username: " + username + " are not found");
			throw new UserDetailsNotFoundException("User details are not found");
		}
	}

	@Override
	public String updateTravellerByUserName(String username, Traveller traveller) {
		Optional<Registration> res = userrepo.findByUsername(username);
		if (!res.isEmpty()) {
			List<Traveller> travellers = res.get().getTraveller();
			for (Traveller travel : travellers) {
				if (travel.getName().equalsIgnoreCase(traveller.getName())) {
					Traveller t = travellerrepo.findById(travel.getTravellerId()).get();
					t.setAddress(traveller.getAddress());
					t.setAge(traveller.getAge());
					t.setMobile(traveller.getMobile());
					t.setGender(traveller.getGender());
					travellerrepo.save(t);
				}
			}
			res.get().setTraveller(travellers);
			log.info("Traveller details of username: " + username + " got updated");
			return "Traveller updated";
		} else {
			log.info("User details of username: " + username + " are not found");
			throw new UserDetailsNotFoundException("User details are not found");
		}
	}

	@Override
	public String deleteTravellerByTravellerName(String username, String name) {
		if (userrepo.existsById(username)) {
			Registration r = userrepo.findByUsername(username).get();
			List<Traveller> l = r.getTraveller().stream().filter(t -> !t.getName().equalsIgnoreCase(t.getName()))
					.collect(Collectors.toList());
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
	public List<Traveller> getAllTravellersByUsername(String username) {
		if (userrepo.findByUsername(username).isPresent()) {
			List<Traveller> list = userrepo.findByUsername(username).get().getTraveller();
			if (!list.isEmpty()) {
				log.info("Traveller details of username: " + username + " are found");
				return list;
			} else {
				log.info("Traveller details of username: " + username + " are not found");
				return list;
			}
		} else
			throw new UserDetailsNotFoundException("User details are not found");

	}

	@Override
	public Traveller getTravellerByTravellerName(String username, String name) {
		if (userrepo.existsById(username)) {
			Registration r = userrepo.findByUsername(username).get();
			log.info("Traveller details of username: " + username + " are found");
			return r.getTraveller().stream().filter(t -> t.getName().equalsIgnoreCase(name))
					.collect(Collectors.toList()).get(0);
		} else {
			log.info("User details of username: " + username + " are not found");
			throw new UserDetailsNotFoundException("User details are not found");
		}
	}

}
