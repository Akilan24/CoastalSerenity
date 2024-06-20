package com.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.constant.UserConstant;
import com.authservice.entity.Registration;
import com.authservice.entity.Traveller;
import com.authservice.proxyentity.user.UserProxyController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(UserConstant.USER)
@CrossOrigin(UserConstant.CROSS_ORIGIN)
public class UserController {
	@Autowired
	UserProxyController userProxy;

	@GetMapping(UserConstant.USER)
	public ResponseEntity<List<Registration>> listAllUser() {
		log.info("listAllUser controller called");
		return userProxy.listAllUser();
	}

	@GetMapping(UserConstant.GET_ALL_TRAVELLERS)
	public ResponseEntity<List<Traveller>> getAllTravellersByUsername(@PathVariable String username) {
		log.info("getAllTravellersByUsername controller called");
		return userProxy.getAllTravellersByUsername(username);
	}

	@PostMapping(UserConstant.ADD_TRAVELLER_BY_USERNAME)
	public ResponseEntity<String> addTravellerByUsername(@PathVariable String username, @RequestBody Traveller t) {
		log.info("addTravellerByUsername controller called");
		return userProxy.addTravellerByUsername(username, t);
	}

	@PutMapping(UserConstant.UPDATE_TRAVELLER_BY_USERNAME)
	public ResponseEntity<String> updateTravellerByUsername(@PathVariable String username, @RequestBody Traveller t) {
		log.info("updateTravellerByUsername controller called");
		return userProxy.updateTravellerByUsername(username, t);
	}

	@GetMapping(UserConstant.GET_TRAVELLER_BY_NAME)
	public ResponseEntity<Traveller> getTravellerByTravellerName(@PathVariable String username,
			@PathVariable String name) {
		log.info("getTravellerByTravellerName controller called");
		return userProxy.getTravellerByTravellerName(username, name);
	}

	@DeleteMapping(UserConstant.DELETE_TRAVELLER_BY_NAME)
	public ResponseEntity<String> deleteTravellerByTravellerName(@PathVariable String username,
			@PathVariable String name) {
		log.info("deleteTravellerByTravellerName controller called");
		return userProxy.deleteTravellerByTravellerName(username, name);
	}

	@PutMapping(UserConstant.UPDATE_USER_BY_USERNAME)
	public ResponseEntity<Registration> updateUserByUsername(@PathVariable String username,
			@RequestBody @Valid Registration user) {
		log.info("updateUserByUsername controller called");
		return userProxy.updateUserByUsername(username, user);
	}

	@PutMapping(UserConstant.UPDATE_USER_PASSWORD_BY_USERNAME)
	public ResponseEntity<String> updatePasswordByUsername(@PathVariable String username,
			@PathVariable @Valid String password) {
		log.info("updatePasswordByUsername controller called");
		return userProxy.updatePasswordByUsername(username, password);
	}

	@GetMapping(UserConstant.GET_USER_BY_USERNAME)
	public ResponseEntity<Registration> showUserByUserName(@PathVariable String username) {
		log.info("showUserByUserName controller called");
		return userProxy.showUserByEmail(username);
	}

	@GetMapping(UserConstant.GET_USER_BY_EMAIL)
	public ResponseEntity<Registration> showUserByEmail(@PathVariable String email) {
		log.info("showUserByEmail controller called");
		return userProxy.showUserByEmail(email);
	}

	@GetMapping(UserConstant.GET_USER_BY_MOBILE)
	public ResponseEntity<Registration> showUserByMobileNumber(@PathVariable String mobile) {
		log.info("showUserByMobileNumber controller called");
		return userProxy.showUserByMobileNumber(mobile);
	}

	@DeleteMapping(UserConstant.DELETE_USER_BY_USERNAME)
	public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
		log.info("deleteUserByUsername controller called");
		return userProxy.deleteUserByUsername(username);
	}
}
