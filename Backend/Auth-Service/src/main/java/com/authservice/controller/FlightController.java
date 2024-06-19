package com.authservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

import com.authservice.constant.FlightConstant;
import com.authservice.proxyentity.flight.Flight;
import com.authservice.proxyentity.flight.FlightBookingDetails;
import com.authservice.proxyentity.flight.FlightProxyController;
import com.authservice.proxyentity.flight.FlightTravellerFlightSeats;

@RestController
@RequestMapping(FlightConstant.FLIGHT)
@CrossOrigin(FlightConstant.CROSS_ORIGIN)
public class FlightController {

	@Autowired
	private FlightProxyController flightProxy;

	@GetMapping(FlightConstant.GET_ALL_FLIGHT)
	public ResponseEntity<List<Flight>> getAllFlight() {
		return flightProxy.getAllFlight();
	}

	@GetMapping(FlightConstant.GET_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<Optional<Flight>> getFlightByFlightId(@PathVariable long id) {
		return flightProxy.getFlightByFlightId(id);
	}

	@PutMapping(FlightConstant.ADD_SEATS_BY_FLIGHT_ID)
	public ResponseEntity<Flight> addSeatsByFlightId(@PathVariable long id) {
		return flightProxy.addSeatsByFlightId(id);
	}

	@PostMapping(FlightConstant.SAVE_FLIGHT)
	public ResponseEntity<Flight> saveFlight(@RequestBody Flight flight) {
		return flightProxy.saveFlight(flight);
	}

	@PostMapping(FlightConstant.BOOK_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<FlightBookingDetails> bookFlight(@PathVariable long id,
			@RequestBody FlightTravellerFlightSeats ftfs, @PathVariable String username) {
		return flightProxy.bookFlight(id, ftfs, username);
	}

	@GetMapping(FlightConstant.GET_FLIGHT_BOOKING_DETAILS_BY_FLIGHT_BOOKING_ID)
	public ResponseEntity<FlightBookingDetails> getFlightBookingDetailsByFlightBookingId(@PathVariable long id) {
		return flightProxy.getFlightBookingDetailsByFlightBookingId(id);
	}

	@GetMapping(FlightConstant.GET_FLIGHT_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<FlightBookingDetails>> getFlightBookingDetailsByUsername(@PathVariable String username) {
		return flightProxy.getFlightBookingDetailsByUsername(username);
	}

	@PutMapping(FlightConstant.UPDATE_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<Flight> updateFlightByFlightId(@PathVariable long id, @RequestBody Flight flight) {
		return flightProxy.updateFlightByFlightId(id, flight);
	}

	@DeleteMapping(FlightConstant.DELETE_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<String> deleteFlightByFlightId(@PathVariable long id) {
		return flightProxy.deleteFlightByFlightId(id);
	}

	@PutMapping(FlightConstant.CANCEL_PAYMENT_BY_FLIGHT_BOOKING_ID)
	public ResponseEntity<FlightBookingDetails> cancelPaymentByFlightBookingId(@PathVariable long id) {
		return flightProxy.cancelPaymentByFlightBookingId(id);
	}

	@GetMapping(FlightConstant.GET_ALL_CITY_NAMES)
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		return flightProxy.getAllCityNames();
	}

	@GetMapping(FlightConstant.PAYMENT_STATUS_CHANGE_BY_FLIGHT_BOOKING_ID)
	public ResponseEntity<FlightBookingDetails> paymentStatusChangeByFlightBookingId(@PathVariable long bookingid) {
		return flightProxy.paymentStatusChangeByFlightBookingId(bookingid);
	}

	@GetMapping(FlightConstant.GET_ALL_AVAILABLE_FLIGHT)
	public ResponseEntity<List<Flight>> getAllAvailableFlights(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure, @PathVariable String travellerClass) {
		return flightProxy.getAllAvailableFlights(from, to, departure, travellerClass);
	}

}
