package com.authservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.authservice.proxyentity.flight.Flight;
import com.authservice.proxyentity.flight.FlightBookingDetails;
import com.authservice.proxyentity.flight.FlightProxyController;
import com.authservice.proxyentity.flight.FlightTravellerFlightSeats;

@RestController
@RequestMapping("/CS/Flight")
@CrossOrigin("http://localhost:5173")
public class FlightController {

	@Autowired
	private FlightProxyController flightProxy;

	@GetMapping("/getall")
	public ResponseEntity<List<Flight>> getAllFlights() {
		return flightProxy.getAllFlights();
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Optional<Flight>> getFlightById(@PathVariable long id) {
		return flightProxy.getFlightById(id);
	}

	@PostMapping("/save")
	public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
		return flightProxy.createFlight(flight);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Flight> updateFlight(@PathVariable long id, @RequestBody Flight flight) {
		return flightProxy.updateFlight(id, flight);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFlight(@PathVariable long id) {
		return flightProxy.deleteFlight(id);
	}

	@PutMapping("/resetstatus/{id}")
	public ResponseEntity<Flight> resetstatus(@PathVariable long id) {
		return flightProxy.resetstatus(id);
	}

	@GetMapping("/getallcitynames")
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		return flightProxy.getAllCityNames();
	}

	@GetMapping("/getallavailableflights/{from}/{to}/{departure}/{travellerClass}")
	public ResponseEntity<List<Flight>> getAllAvailableFlights(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure, @PathVariable String travellerClass) {
		return flightProxy.getAllAvailableFlights(from, to, departure, travellerClass);
	}

	@PostMapping("/bookflight/{id}/{username}")
	public ResponseEntity<FlightBookingDetails> bookFlight(@PathVariable long id,
			@RequestBody FlightTravellerFlightSeats ftfs, @PathVariable String username) {
		return flightProxy.bookFlight(id, ftfs, username);
	}

	@GetMapping("/getflightbookingdetailsbyid/{id}")
	public ResponseEntity<FlightBookingDetails> getFlightBookingDetailsById(@PathVariable long id) {
		return flightProxy.getFlightBookingDetailsById(id);
	}

	@GetMapping("/getflightbookingdetailsbyusername/{username}")
	public ResponseEntity<List<FlightBookingDetails>> getFlightBookingDetailsByUsername(@PathVariable String username) {
		return flightProxy.getFlightBookingDetailsByUsername(username);
	}
}
