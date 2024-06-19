package com.busservice.controller;

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

import com.busservice.constant.BusConstant;
import com.busservice.entity.Bus;
import com.busservice.entity.BusBookingDetails;
import com.busservice.entity.BusTravellerBusSeats;
import com.busservice.service.BusService;

@RestController
@RequestMapping(BusConstant.BUS)
public class BusController {

	@Autowired
	private BusService BusService;

	@GetMapping(BusConstant.GET_ALL_BUS)
	public ResponseEntity<List<Bus>> getAllBus() {
		return new ResponseEntity<>(BusService.getAllBus(), HttpStatus.OK);
	}

	@GetMapping(BusConstant.GET_BUS_BY_BUS_ID)
	public ResponseEntity<Optional<Bus>> getBusByBusId(@PathVariable long busId) {
		return new ResponseEntity<>(BusService.getBusByBusId(busId), HttpStatus.OK);
	}

	@PutMapping(BusConstant.ADD_SEATS_BY_BUS_ID)
	public ResponseEntity<Bus> addSeatsByBusId(@PathVariable long busId) {
		return new ResponseEntity<>(BusService.addSeatsByBusId(busId), HttpStatus.CREATED);
	}

	@PostMapping(BusConstant.SAVE_BUS)
	public ResponseEntity<Bus> saveBus(@RequestBody Bus Bus) {
		return new ResponseEntity<>(BusService.saveBus(Bus), HttpStatus.CREATED);
	}

	@PostMapping(BusConstant.BOOK_BUS_BY_BUS_ID)
	public ResponseEntity<BusBookingDetails> bookBusByBusId(@PathVariable long id, @RequestBody BusTravellerBusSeats btbs,
			@PathVariable String username, @PathVariable String pickUpPoint, @PathVariable String dropPoint) {
		return new ResponseEntity<>(BusService.bookBusByBusId(id, btbs, username, pickUpPoint, dropPoint), HttpStatus.CREATED);
	}

	@GetMapping(BusConstant.GET_BUS_BOOKING_DETAILS_BY_BUS_BOOKING_ID)
	public ResponseEntity<BusBookingDetails> getBusBookingDetailsByBusBookingId(@PathVariable long id) {
		return new ResponseEntity<>(BusService.getBusBookingDetailsByBusBookingId(id), HttpStatus.OK);
	}

	@GetMapping(BusConstant.GET_BUS_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<BusBookingDetails>> getBusBookingDetailsByUsername(@PathVariable String username) {
		return new ResponseEntity<>(BusService.getBusBookingDetailsByUsername(username), HttpStatus.OK);
	}

	@PutMapping(BusConstant.UPDATE_BUS_BY_BUS_ID)
	public ResponseEntity<Bus> updateBusByBusId(@PathVariable long busId, @RequestBody Bus Bus) {
		return new ResponseEntity<>(BusService.updateBusByBusId(busId, Bus), HttpStatus.OK);
	}

	@DeleteMapping(BusConstant.DELETE_BUS_BY_BUS_ID)
	public ResponseEntity<String> deleteBusByBusId(@PathVariable long busId) {
		return new ResponseEntity<>(BusService.deleteBusByBusId(busId), HttpStatus.OK);

	}

	@PutMapping(BusConstant.CANCEL_PAYMENT_BY_BUS_BOOKING_ID)
	public ResponseEntity<BusBookingDetails> cancelPaymentByBusBookingId(@PathVariable long busId) {
		return new ResponseEntity<>(BusService.cancelPaymentByBusBookingId(busId), HttpStatus.OK);
	}

	@GetMapping(BusConstant.GET_ALL_CITY_NAMES)
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		return new ResponseEntity<>(BusService.getAllCityNames(), HttpStatus.OK);
	}

	@GetMapping(BusConstant.PAYMENT_STATUS_CHANGE_BY_BUS_BOOKING_ID)
	public ResponseEntity<BusBookingDetails> paymentStatusChangeByBusBookingId(@PathVariable long bookingid) {
		return new ResponseEntity<>(BusService.paymentStatusChangeByBusBookingId(bookingid), HttpStatus.OK);
	}

	@GetMapping(BusConstant.GET_ALL_AVAILABLE_BUS)
	public ResponseEntity<List<Bus>> getAllAvailableBuses(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure) {
		return new ResponseEntity<>(BusService.getAllAvailableBuses(from, to, departure), HttpStatus.OK);
	}
}
