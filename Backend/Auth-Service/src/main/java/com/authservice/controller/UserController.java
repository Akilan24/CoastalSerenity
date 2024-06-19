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

@RestController
@RequestMapping(UserConstant.USER)
@CrossOrigin(UserConstant.CROSS_ORIGIN)
public class UserController {
	@Autowired
	UserProxyController userProxy;

	@GetMapping(UserConstant.USER)
	public ResponseEntity<List<Registration>> listAllUser() {
		return userProxy.listAllUser();
	}

	@GetMapping(UserConstant.GET_ALL_TRAVELLERS)
	public ResponseEntity<List<Traveller>> getAllTravellersByUsername(@PathVariable String username) {
		return userProxy.getAllTravellersByUsername(username);
	}

	@PostMapping(UserConstant.ADD_TRAVELLER_BY_USERNAME)
	public ResponseEntity<String> addTravellerByUsername(@PathVariable String username, @RequestBody Traveller t) {
		return userProxy.addTravellerByUsername(username, t);
	}

	@PutMapping(UserConstant.UPDATE_TRAVELLER_BY_USERNAME)
	public ResponseEntity<String> updateTravellerByUsername(@PathVariable String username, @RequestBody Traveller t) {
		return userProxy.updateTravellerByUsername(username, t);
	}

	@GetMapping(UserConstant.GET_TRAVELLER_BY_NAME)
	public ResponseEntity<Traveller> getTravellerByTravellerName(@PathVariable String username,
			@PathVariable String name) {
		return userProxy.getTravellerByTravellerName(username, name);
	}

	@DeleteMapping(UserConstant.DELETE_TRAVELLER_BY_NAME)
	public ResponseEntity<String> deleteTravellerByTravellerName(@PathVariable String username,
			@PathVariable String name) {
		return userProxy.deleteTravellerByTravellerName(username, name);
	}

	@PutMapping(UserConstant.UPDATE_USER_BY_USERNAME)
	public ResponseEntity<Registration> updateUserByUsername(@PathVariable String username,
			@RequestBody @Valid Registration user) {
		return userProxy.updateUserByUsername(username, user);
	}

	@PutMapping(UserConstant.UPDATE_USER_PASSWORD_BY_USERNAME)
	public ResponseEntity<String> updatePasswordByUsername(@PathVariable String username,
			@PathVariable @Valid String password) {
		return userProxy.updatePasswordByUsername(username, password);
	}

	@GetMapping(UserConstant.GET_USER_BY_USERNAME)
	public ResponseEntity<Registration> showUserByUserName(@PathVariable String username) {
		return userProxy.showUserByEmail(username);
	}

	@GetMapping(UserConstant.GET_USER_BY_EMAIL)
	public ResponseEntity<Registration> showUserByEmail(@PathVariable String email) {
		return userProxy.showUserByEmail(email);
	}

	@GetMapping(UserConstant.GET_USER_BY_MOBILE)
	public ResponseEntity<Registration> showUserByMobileNumber(@PathVariable String mobile) {
		return userProxy.showUserByMobileNumber(mobile);
	}

	@DeleteMapping(UserConstant.DELETE_USER_BY_USERNAME)
	public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
		return userProxy.deleteUserByUsername(username);
	}
}
