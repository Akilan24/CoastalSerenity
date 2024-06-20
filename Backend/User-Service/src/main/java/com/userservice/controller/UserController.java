package com.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.constant.UserConstant;
import com.userservice.entity.Registration;
import com.userservice.entity.Traveller;
import com.userservice.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(UserConstant.USER)
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(UserConstant.USER)
	public ResponseEntity<List<Registration>> listAllUser() {
		log.info("listAllUser controller called");
		return new ResponseEntity<>(userService.ShowAllUser(), HttpStatus.OK);
	}

	@GetMapping(UserConstant.GET_ALL_TRAVELLERS)
	public ResponseEntity<List<Traveller>> getAllTravellersByUsername(@PathVariable String username) {
		log.info("getAllTravellersByUsername controller called");
		return new ResponseEntity<>(userService.getAllTravellersByUsername(username), HttpStatus.OK);
	}

	@PostMapping(UserConstant.ADD_TRAVELLER_BY_USERNAME)
	public ResponseEntity<String> addTravellerByUsername(@PathVariable String username, @RequestBody Traveller t) {
		log.info("addTravellerByUsername controller called");
		return new ResponseEntity<>(userService.addTravellerByUserName(username, t), HttpStatus.OK);
	}

	@PutMapping(UserConstant.UPDATE_TRAVELLER_BY_USERNAME)
	public ResponseEntity<String> updateTravellerByUsername(@PathVariable String username, @RequestBody Traveller t) {
		log.info("updateTravellerByUsername controller called");
		return new ResponseEntity<>(userService.updateTravellerByUserName(username, t), HttpStatus.OK);
	}

	@GetMapping(UserConstant.GET_TRAVELLER_BY_NAME)
	public ResponseEntity<Traveller> getTravellerByTravellerName(@PathVariable String username,
			@PathVariable String name) {
		log.info("getTravellerByTravellerName controller called");
		return new ResponseEntity<>(userService.getTravellerByTravellerName(username, name), HttpStatus.OK);
	}

	@DeleteMapping(UserConstant.DELETE_TRAVELLER_BY_NAME)
	public ResponseEntity<String> deleteTravellerByTravellerName(@PathVariable String username,
			@PathVariable String name) {
		log.info("deleteTravellerByTravellerName controller called");
		return new ResponseEntity<>(userService.deleteTravellerByTravellerName(username, name), HttpStatus.OK);
	}

	@PutMapping(UserConstant.UPDATE_USER_BY_USERNAME)
	public ResponseEntity<Registration> updateUserByUsername(@PathVariable String username,
			@RequestBody @Valid Registration user) {
		log.info("updateUserByUsername controller called");
		return new ResponseEntity<>(userService.updateUserByUserName(username, user), HttpStatus.OK);
	}

	@PutMapping(UserConstant.UPDATE_USER_PASSWORD_BY_USERNAME)
	public ResponseEntity<String> updatePasswordByUsername(@PathVariable String username,
			@PathVariable @Valid String password) {
		log.info("updatePasswordByUsername controller called");
		return new ResponseEntity<>(userService.updateUserPasswordByUserName(username, password), HttpStatus.OK);
	}

	@GetMapping(UserConstant.GET_USER_BY_USERNAME)
	public ResponseEntity<Registration> showUserByUserName(@PathVariable String username) {
		log.info("showUserByUserName controller called");
		return new ResponseEntity<>(userService.ShowUserByUserName(username), HttpStatus.OK);
	}

	@GetMapping(UserConstant.GET_USER_BY_EMAIL)
	public ResponseEntity<Registration> showUserByEmail(@PathVariable String email) {
		log.info("showUserByEmail controller called");
		return new ResponseEntity<>(userService.ShowUserByEmail(email), HttpStatus.OK);
	}

	@GetMapping(UserConstant.GET_USER_BY_MOBILE)
	public ResponseEntity<Registration> showUserByMobileNumber(@PathVariable String mobile) {
		log.info("showUserByMobileNumber controller called");
		return new ResponseEntity<>(userService.ShowUserByMobileNumber(mobile), HttpStatus.OK);
	}

	@DeleteMapping(UserConstant.DELETE_USER_BY_USERNAME)
	public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
		log.info("deleteUserByUsername controller called");
		return new ResponseEntity<>(userService.removeUserByUserName(username), HttpStatus.OK);
	}
}
