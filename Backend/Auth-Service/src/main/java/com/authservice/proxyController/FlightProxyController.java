package com.authservice.proxyController;

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

import com.authservice.ProxyEntity.Flight;

@FeignClient(name = "FLIGHT-SERVICE", url = "http://localhost:8086/Flight")
public interface FlightProxyController {

	@GetMapping("/getall")
	public ResponseEntity<List<Flight>> getAllFlights();

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Optional<Flight>> getFlightById(@PathVariable long id);

	@PostMapping("/save")
	public ResponseEntity<Flight> createFlight(@RequestBody Flight flight);

	@PutMapping("/update/{id}")
	public ResponseEntity<Flight> updateFlight(@PathVariable long id, @RequestBody Flight flight);

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFlight(@PathVariable long id);

	@PutMapping("/resetstatus/{id}")
	public ResponseEntity<Flight> resetstatus(@PathVariable long id);

	@GetMapping("/getallcitynames")
	public ResponseEntity<List<List<String>>> getAllCityNames();

}
