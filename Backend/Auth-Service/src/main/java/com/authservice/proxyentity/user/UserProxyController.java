package com.authservice.proxyentity.user;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.constant.UserConstant;
import com.authservice.entity.Registration;
import com.authservice.entity.Traveller;

import jakarta.validation.Valid;

@FeignClient(name = UserConstant.SERVICE, url = UserConstant.URL)
public interface UserProxyController {

	@GetMapping(UserConstant.USER)
	public ResponseEntity<List<Registration>> listAllUser();

	@GetMapping(UserConstant.GET_ALL_TRAVELLERS)
	public ResponseEntity<List<Traveller>> getAllTravellersByUsername(@PathVariable String username);

	@PostMapping(UserConstant.ADD_TRAVELLER_BY_USERNAME)
	public ResponseEntity<String> addTravellerByUsername(@PathVariable String username, @RequestBody Traveller t);

	@PutMapping(UserConstant.UPDATE_TRAVELLER_BY_USERNAME)
	public ResponseEntity<String> updateTravellerByUsername(@PathVariable String username, @RequestBody Traveller t);

	@GetMapping(UserConstant.GET_TRAVELLER_BY_NAME)
	public ResponseEntity<Traveller> getTravellerByTravellerName(@PathVariable String username,
			@PathVariable String name);

	@DeleteMapping(UserConstant.DELETE_TRAVELLER_BY_NAME)
	public ResponseEntity<String> deleteTravellerByTravellerName(@PathVariable String username,
			@PathVariable String name);

	@PutMapping(UserConstant.UPDATE_USER_BY_USERNAME)
	public ResponseEntity<Registration> updateUserByUsername(@PathVariable String username,
			@RequestBody @Valid Registration user);

	@PutMapping(UserConstant.UPDATE_USER_PASSWORD_BY_USERNAME)
	public ResponseEntity<String> updatePasswordByUsername(@PathVariable String username,
			@PathVariable @Valid String password);

	@GetMapping(UserConstant.GET_USER_BY_USERNAME)
	public ResponseEntity<Registration> showUserByUserName(@PathVariable String username);

	@GetMapping(UserConstant.GET_USER_BY_EMAIL)
	public ResponseEntity<Registration> showUserByEmail(@PathVariable String email);

	@GetMapping(UserConstant.GET_USER_BY_MOBILE)
	public ResponseEntity<Registration> showUserByMobileNumber(@PathVariable String mobile);

	@DeleteMapping(UserConstant.DELETE_USER_BY_USERNAME)
	public ResponseEntity<String> deleteUserByUsername(@PathVariable String username);
}
