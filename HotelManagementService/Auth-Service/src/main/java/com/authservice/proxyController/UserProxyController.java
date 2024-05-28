package com.authservice.proxyController;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.entity.Registration;
import com.authservice.entity.Traveller;

import jakarta.validation.Valid;

@FeignClient(name = "USER-SERVICE", url = "http://localhost:8081/User")
public interface UserProxyController {

	@GetMapping("/getallusers")
	public ResponseEntity<List<Registration>> listUser();

	@GetMapping("/getalltravellers/{username}")
	public ResponseEntity<List<Traveller>> getalltravellersbyusername(@PathVariable String username);

	@GetMapping("/addtraveller/{username}")
	public ResponseEntity<String> addtraveller(@PathVariable String username, @RequestBody @Valid Traveller t);

	@PutMapping("/updatetraveller/{username}")
	public ResponseEntity<String> updatetraveller(@PathVariable String username, @RequestBody @Valid Traveller t);

	@DeleteMapping("/deletetraveller/{username}/{name}")
	public ResponseEntity<String> deletetraveller(@PathVariable String username, @PathVariable String name);

	@PutMapping("/updateuser/{username}")
	public ResponseEntity<Registration> updateuser(@PathVariable String username,
			@RequestBody @Valid Registration user);

	@PutMapping("/updatepassword/{username}/{password}")
	public ResponseEntity<String> updatepassword(@PathVariable String username, @PathVariable @Valid String password);

	@GetMapping("/getuserbyname/{username}")
	public ResponseEntity<Registration> showUserByUserName(@PathVariable String username);

	@GetMapping("/getuserbyemail/{email}")
	public ResponseEntity<Registration> showUserByEmail(@PathVariable String email);

	@GetMapping("/getuserbymobile/{mobile}")
	public ResponseEntity<Registration> showUserByMobileNumber(@PathVariable String mobile);

	@GetMapping("/gettraveller/{username}/{name}")
	public ResponseEntity<Traveller> gettraveller(@PathVariable String username, @PathVariable String name);
	
	@DeleteMapping("/deleteuserbyid/{username}")
	public ResponseEntity<String> remove(@PathVariable String username);

}
