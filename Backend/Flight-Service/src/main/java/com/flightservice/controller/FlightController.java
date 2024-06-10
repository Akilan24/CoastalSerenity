package com.flightservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.flightservice.entity.Flight;
import com.flightservice.entity.FlightBookingDetails;
import com.flightservice.entity.FlightTravellerFlightSeats;
import com.flightservice.service.FlightService;

@RestController
@RequestMapping("/Flight")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping("/getall")
	public ResponseEntity<List<Flight>> getAllFlights() {
		return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Optional<Flight>> getFlightById(@PathVariable long id) {
		return new ResponseEntity<>(flightService.getFlightById(id), HttpStatus.OK);
	}

	@PutMapping("/addseats/{id}")
	public ResponseEntity<Flight> addSeats(@PathVariable long id) {
		return new ResponseEntity<>(flightService.addSeats(id), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
		return new ResponseEntity<>(flightService.saveFlight(flight), HttpStatus.CREATED);
	}

	@PostMapping("/bookflight/{id}/{username}")
	public ResponseEntity<FlightBookingDetails> bookFlight(@PathVariable long id,
			@RequestBody FlightTravellerFlightSeats ftfs, @PathVariable String username) {
		return new ResponseEntity<>(flightService.bookFlight(id, ftfs, username), HttpStatus.CREATED);
	}

	@GetMapping("/getflightbookingdetailsbyid/{id}")
	public ResponseEntity<FlightBookingDetails> getFlightBookingDetailsById(@PathVariable long id) {
		return new ResponseEntity<>(flightService.getFlightBookingDetailsById(id), HttpStatus.OK);
	}
	
	@GetMapping("/getflightbookingdetailsbyusername/{username}")
	public ResponseEntity<List<FlightBookingDetails>> getFlightBookingDetailsByUsername(@PathVariable String username) {
		return new ResponseEntity<>(flightService.getFlightBookingDetailsByUsername(username), HttpStatus.OK);
	}
		
	@PutMapping("/update/{id}")
	public ResponseEntity<Flight> updateFlight(@PathVariable long id, @RequestBody Flight flight) {
		return new ResponseEntity<>(flightService.updateFlight(id, flight), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFlight(@PathVariable long id) {
		return new ResponseEntity<>(flightService.deleteFlight(id), HttpStatus.OK);
	}

	@PutMapping("/resetstatus/{id}")
	public ResponseEntity<FlightBookingDetails> resetstatus(@PathVariable long id) {
		return new ResponseEntity<>(flightService.resetStatus(id), HttpStatus.OK);
	}

	@GetMapping("/getallcitynames")
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		return new ResponseEntity<>(flightService.getAllCityNames(), HttpStatus.OK);
	}

	@GetMapping("/paymentstatuschange/{bookingid}")
	public ResponseEntity<FlightBookingDetails> paymentstatuschange(@PathVariable long bookingid) {
		return new ResponseEntity<>(flightService.paymentstatuschange(bookingid), HttpStatus.OK);
	}

	@GetMapping("/getallavailableflights/{from}/{to}/{departure}/{travellerClass}")
	public ResponseEntity<List<Flight>> getAllAvailableFlights(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure, @PathVariable String travellerClass) {
		return new ResponseEntity<>(flightService.getAllAvailableFlights(from, to, departure, travellerClass),
				HttpStatus.OK);
	}

}
