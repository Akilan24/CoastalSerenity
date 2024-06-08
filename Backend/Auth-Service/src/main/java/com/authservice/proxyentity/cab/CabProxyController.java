package com.authservice.proxyentity.cab;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.proxyentity.cab.Cab;

@FeignClient(name = "CAB-SERVICE", url = "http://localhost:8089/Cab")
public interface CabProxyController {

	@GetMapping("/getall")
	public ResponseEntity<List<Cab>> getAllCabs();

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Optional<Cab>> getCabById(@PathVariable long id);

	@PostMapping("/save")
	public ResponseEntity<Cab> createCab(@RequestBody Cab Cab);

	@PutMapping("/update/{id}")
	public ResponseEntity<Cab> updateCab(@PathVariable long id, @RequestBody Cab Cab);

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCab(@PathVariable long id);

	@PutMapping("/resetstatus/{id}")
	public ResponseEntity<Cab> resetstatus(@PathVariable long id);
}
