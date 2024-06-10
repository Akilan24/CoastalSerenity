package com.flightservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flightservice.externalclass.Registration;

@FeignClient(name = "user-service", url = "http://localhost:8081/User")
public interface UserProxy {

	@GetMapping("/getuserbyname/{username}")
	public ResponseEntity<Registration> showUserByUserName(@PathVariable String username);
}
