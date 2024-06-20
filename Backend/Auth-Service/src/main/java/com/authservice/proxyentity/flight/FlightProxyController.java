package com.authservice.proxyentity.flight;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.constant.FlightConstant;

@FeignClient(name = FlightConstant.SERVICE, url = FlightConstant.URL)
public interface FlightProxyController {

	@GetMapping(FlightConstant.GET_ALL_FLIGHT)
	public ResponseEntity<List<Flight>> getAllFlight();

	@GetMapping(FlightConstant.GET_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<Optional<Flight>> getFlightByFlightId(@PathVariable long id);

	@PutMapping(FlightConstant.ADD_SEATS_BY_FLIGHT_ID)
	public ResponseEntity<Flight> addSeatsByFlightId(@PathVariable long id);

	@PostMapping(FlightConstant.SAVE_FLIGHT)
	public ResponseEntity<Flight> saveFlight(@RequestBody Flight flight);

	@PostMapping(FlightConstant.BOOK_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<FlightBookingDetails> bookFlight(@PathVariable long id,
			@RequestBody FlightTravellerFlightSeats ftfs, @PathVariable String username);

	@GetMapping(FlightConstant.GET_FLIGHT_BOOKING_DETAILS_BY_FLIGHT_BOOKING_ID)
	public ResponseEntity<FlightBookingDetails> getFlightBookingDetailsByFlightBookingId(@PathVariable long id);

	@GetMapping(FlightConstant.GET_FLIGHT_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<FlightBookingDetails>> getFlightBookingDetailsByUsername(@PathVariable String username);

	@PutMapping(FlightConstant.UPDATE_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<Flight> updateFlightByFlightId(@PathVariable long id, @RequestBody Flight flight);

	@DeleteMapping(FlightConstant.DELETE_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<String> deleteFlightByFlightId(@PathVariable long id);

	@PutMapping(FlightConstant.CANCEL_PAYMENT_BY_FLIGHT_BOOKING_ID)
	public ResponseEntity<FlightBookingDetails> cancelPaymentByFlightBookingId(@PathVariable long id);

	@GetMapping(FlightConstant.GET_ALL_CITY_NAMES)
	public ResponseEntity<List<List<String>>> getAllCityNames();

	@GetMapping(FlightConstant.PAYMENT_STATUS_CHANGE_BY_FLIGHT_BOOKING_ID)
	public ResponseEntity<FlightBookingDetails> paymentStatusChangeByFlightBookingId(@PathVariable long bookingid);

	@GetMapping(FlightConstant.GET_ALL_AVAILABLE_FLIGHT)
	public ResponseEntity<List<Flight>> getAllAvailableFlights(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure, @PathVariable String travellerClass);
}
