package com.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.entity.Registration;
import com.authservice.proxyentity.user.Traveller;
import com.authservice.proxyentity.user.UserProxyController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/CS/User")
@CrossOrigin("http://localhost:5173")
public class UserController {
	@Autowired
	UserProxyController userProxy;

	@PreAuthorize("hasAnyAuthority('admin','user')")
	@GetMapping("/getallusers")
	public ResponseEntity<List<Registration>> listUser() {
		return userProxy.listUser();
	}

	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/getalltravellers/{username}")
	public ResponseEntity<List<Traveller>> getalltravellersbyusername(@PathVariable String username) {
		return userProxy.getalltravellersbyusername(username);
	}

	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/addtraveller/{username}")
	public ResponseEntity<String> addtraveller(@PathVariable String username, @RequestBody @Valid Traveller t) {
		return userProxy.addtraveller(username, t);
	}

	@PreAuthorize("hasAuthority('user')")
	@PutMapping("/updatetraveller/{username}")
	public ResponseEntity<String> updatetraveller(@PathVariable String username, @RequestBody @Valid Traveller t) {
		return userProxy.updatetraveller(username, t);
	}

	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/gettraveller/{username}/{name}")
	public ResponseEntity<Traveller> gettraveller(@PathVariable String username, @PathVariable String name) {
		return userProxy.gettraveller(username, name);
	}

	@PreAuthorize("hasAuthority('user')")
	@DeleteMapping("/deletetraveller/{username}/{name}")
	public ResponseEntity<String> deletetraveller(@PathVariable String username, @PathVariable String name) {
		return userProxy.deletetraveller(username, name);
	}

	@PreAuthorize("hasAuthority('user')")
	@PutMapping("/updateuser/{username}")
	public ResponseEntity<Registration> updateuser(@PathVariable String username,
			@RequestBody @Valid Registration user) {
		return userProxy.updateuser(username, user);
	}

	@PreAuthorize("hasAuthority('user')")
	@PutMapping("/updatepassword/{username}/{password}")
	public ResponseEntity<String> updatepassword(@PathVariable String username, @PathVariable @Valid String password) {
		return userProxy.updatepassword(username, password);
	}

	@PreAuthorize("hasAnyAuthority('admin','user')")
	@GetMapping("/getuserbyname/{username}")
	public ResponseEntity<Registration> showUserByUserName(@PathVariable String username) {
		return userProxy.showUserByUserName(username);
	}

	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/getuserbyemail/{email}")
	public ResponseEntity<Registration> showUserByEmail(@PathVariable String email) {
		return userProxy.showUserByEmail(email);
	}

	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/getuserbymobile/{mobile}")
	public ResponseEntity<Registration> showUserByMobileNumber(@PathVariable String mobile) {
		return userProxy.showUserByMobileNumber(mobile);
	}

	@PreAuthorize("hasAuthority('user')")
	@DeleteMapping("/deleteuserbyid/{username}")
	public ResponseEntity<String> remove(@PathVariable String username) {
		return userProxy.remove(username);
	}

}
