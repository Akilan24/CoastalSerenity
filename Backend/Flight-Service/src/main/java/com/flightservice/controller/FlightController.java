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

import com.flightservice.constant.FlightConstant;
import com.flightservice.entity.Flight;
import com.flightservice.entity.FlightBookingDetails;
import com.flightservice.entity.FlightTravellerFlightSeats;
import com.flightservice.service.FlightService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(FlightConstant.FLIGHT)
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping(FlightConstant.GET_ALL_FLIGHT)
	public ResponseEntity<List<Flight>> getAllFlight() {
		log.info("getAllFlight controller called");
		return new ResponseEntity<>(flightService.getAllFlight(), HttpStatus.OK);
	}

	@GetMapping(FlightConstant.GET_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<Optional<Flight>> getFlightByFlightId(@PathVariable long id) {
		log.info("getFlightByFlightId controller called");
		return new ResponseEntity<>(flightService.getFlightByFlightId(id), HttpStatus.OK);
	}

	@PutMapping(FlightConstant.ADD_SEATS_BY_FLIGHT_ID)
	public ResponseEntity<Flight> addSeatsByFlightId(@PathVariable long id) {
		log.info("addSeatsByFlightId controller called");
		return new ResponseEntity<>(flightService.addSeatsByFlightId(id), HttpStatus.OK);
	}

	@PostMapping(FlightConstant.SAVE_FLIGHT)
	public ResponseEntity<Flight> saveFlight(@RequestBody Flight flight) {
		log.info("createFlight controller called");
		return new ResponseEntity<>(flightService.saveFlight(flight), HttpStatus.CREATED);
	}

	@PostMapping(FlightConstant.BOOK_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<FlightBookingDetails> bookFlight(@PathVariable long id,
			@RequestBody FlightTravellerFlightSeats ftfs, @PathVariable String username) {
		log.info("bookFlight controller called");
		return new ResponseEntity<>(flightService.bookFlight(id, ftfs, username), HttpStatus.CREATED);
	}

	@GetMapping(FlightConstant.GET_FLIGHT_BOOKING_DETAILS_BY_FLIGHT_BOOKING_ID)
	public ResponseEntity<FlightBookingDetails> getFlightBookingDetailsByFlightBookingId(@PathVariable long id) {
		log.info("getFlightBookingDetailsByFlightBookingId controller called");
		return new ResponseEntity<>(flightService.getFlightBookingDetailsByFlightBookingId(id), HttpStatus.OK);
	}

	@GetMapping(FlightConstant.GET_FLIGHT_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<FlightBookingDetails>> getFlightBookingDetailsByUsername(@PathVariable String username) {
		log.info("getFlightBookingDetailsByUsername controller called");
		return new ResponseEntity<>(flightService.getFlightBookingDetailsByUsername(username), HttpStatus.OK);
	}

	@PutMapping(FlightConstant.UPDATE_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<Flight> updateFlightByFlightId(@PathVariable long id, @RequestBody Flight flight) {
		log.info("updateFlight controller called");
		return new ResponseEntity<>(flightService.updateFlightByFlightId(id, flight), HttpStatus.OK);
	}

	@DeleteMapping(FlightConstant.DELETE_FLIGHT_BY_FLIGHT_ID)
	public ResponseEntity<String> deleteFlightByFlightId(@PathVariable long id) {
		log.info("deleteFlight controller called");
		return new ResponseEntity<>(flightService.deleteFlightByFlightId(id), HttpStatus.OK);
	}

	@PutMapping(FlightConstant.CANCEL_PAYMENT_BY_FLIGHT_BOOKING_ID)
	public ResponseEntity<FlightBookingDetails> cancelPaymentByFlightBookingId(@PathVariable long id) {
		log.info("cancelPaymentByTrainBookingId controller called");
		return new ResponseEntity<>(flightService.cancelPaymentByFlightBookingId(id), HttpStatus.OK);
	}

	@GetMapping(FlightConstant.GET_ALL_CITY_NAMES)
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		log.info("getAllCityNames controller called");
		return new ResponseEntity<>(flightService.getAllCityNames(), HttpStatus.OK);
	}

	@GetMapping(FlightConstant.PAYMENT_STATUS_CHANGE_BY_FLIGHT_BOOKING_ID)
	public ResponseEntity<FlightBookingDetails> paymentStatusChangeByFlightBookingId(@PathVariable long bookingid) {
		log.info("paymentStatusChangeByTrainBookingId controller called");
		return new ResponseEntity<>(flightService.paymentStatusChangeByFlightBookingId(bookingid), HttpStatus.OK);
	}

	@GetMapping(FlightConstant.GET_ALL_AVAILABLE_FLIGHT)
	public ResponseEntity<List<Flight>> getAllAvailableFlights(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure, @PathVariable String travellerClass) {
		log.info("getAllAvailableFlights controller called");
		return new ResponseEntity<>(flightService.getAllAvailableFlights(from, to, departure, travellerClass),
				HttpStatus.OK);
	}

}
