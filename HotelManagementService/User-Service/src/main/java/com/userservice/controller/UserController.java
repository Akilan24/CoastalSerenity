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

import com.userservice.entity.Registration;
import com.userservice.entity.Traveller;
import com.userservice.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/User")
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/getallusers")
	public ResponseEntity<List<Registration>> listUser() {
		log.info("/getallusers called");
		return new ResponseEntity<>(userService.ShowAllUser(), HttpStatus.OK);
	}

	@GetMapping("/getalltravellers/{username}")
	public ResponseEntity<List<Traveller>> getalltravellersbyusername(@PathVariable String username) {
		log.info("/getalltravellers/{username} called");
		return new ResponseEntity<>(userService.getTravellerByUsername(username), HttpStatus.OK);
	}

	@PostMapping("/addtraveller/{username}")
	public ResponseEntity<String> addtraveller(@PathVariable String username, @RequestBody  Traveller t) {
		log.info("/addtraveller/{username} called");
		return new ResponseEntity<>(userService.addtraveller(username, t), HttpStatus.OK);
	}

	@PutMapping("/updatetraveller/{username}")
	public ResponseEntity<String> updatetraveller(@PathVariable String username, @RequestBody  Traveller t) {
		log.info("/updatetraveller/{username} called");
		return new ResponseEntity<>(userService.updatetraveller(username, t), HttpStatus.OK);
	}

	@GetMapping("/gettraveller/{username}/{name}")
	public ResponseEntity<Traveller> gettraveller(@PathVariable String username, @PathVariable String name) {
		log.info("/gettraveller/{username}/{name} called");
		return new ResponseEntity<>(userService.gettraveller(username, name), HttpStatus.OK);
	}
	@DeleteMapping("/deletetraveller/{username}/{name}")
	public ResponseEntity<String> deletetraveller(@PathVariable String username, @PathVariable String name) {
		log.info("/deletetraveller/{username}/{name} called");
		return new ResponseEntity<>(userService.deletetraveller(username, name), HttpStatus.OK);
	}

	@PutMapping("/updateuser/{username}")
	public ResponseEntity<Registration> updateuser(@PathVariable String username,
			@RequestBody @Valid Registration user) {
		log.info("/updateuser/{username} called");
		return new ResponseEntity<>(userService.updateUser(username, user), HttpStatus.OK);
	}

	@PutMapping("/updatepassword/{username}/{password}")
	public ResponseEntity<String> updatepassword(@PathVariable String username, @PathVariable @Valid String password) {
		log.info("/updatepassword/{username}/{password} called");
		return new ResponseEntity<>(userService.updateUserpasswordbyusername(username, password), HttpStatus.OK);
	}

	@GetMapping("/getuserbyname/{username}")
	public ResponseEntity<Registration> showUserByUserName(@PathVariable String username) {
		log.info("/getuserbyname/{username} called");
		return new ResponseEntity<>(userService.ShowUserByUserName(username), HttpStatus.OK);
	}

	@GetMapping("/getuserbyemail/{email}")
	public ResponseEntity<Registration> showUserByEmail(@PathVariable String email) {
		log.info("/getuserbyemail/{email} called");
		return new ResponseEntity<>(userService.ShowUserByEmail(email), HttpStatus.OK);
	}

	@GetMapping("/getuserbymobile/{mobile}")
	public ResponseEntity<Registration> showUserByMobileNumber(@PathVariable String mobile) {
		log.info("/getuserbymobile/{mobile} called");
		return new ResponseEntity<>(userService.ShowUserByMobileNumber(mobile), HttpStatus.OK);
	}

	@DeleteMapping("/deleteuserbyid/{username}")
	public ResponseEntity<String> remove(@PathVariable String username) {
		log.info("/deleteuserbyid/{username} called");
		return new ResponseEntity<>(userService.removeUser(username), HttpStatus.OK);
	}
}
