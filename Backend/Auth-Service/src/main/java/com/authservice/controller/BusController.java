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

import com.authservice.constant.BusConstant;
import com.authservice.proxyentity.bus.Bus;
import com.authservice.proxyentity.bus.BusBookingDetails;
import com.authservice.proxyentity.bus.BusProxyController;
import com.authservice.proxyentity.bus.BusTravellerBusSeats;

@RestController
@RequestMapping(BusConstant.BUS)
@CrossOrigin(BusConstant.CROSS_ORIGIN)
public class BusController {

	@Autowired
	private BusProxyController busProxy;

	@GetMapping(BusConstant.GET_ALL_BUS)
	public ResponseEntity<List<Bus>> getAllBus() {
		return busProxy.getAllBus();
	}

	@GetMapping(BusConstant.GET_BUS_BY_BUS_ID)
	public ResponseEntity<Optional<Bus>> getBusByBusId(@PathVariable long busId) {
		return busProxy.getBusByBusId(busId);
	}

	@PutMapping(BusConstant.ADD_SEATS_BY_BUS_ID)
	public ResponseEntity<Bus> addSeatsByBusId(@PathVariable long busId) {
		return busProxy.addSeatsByBusId(busId);
	}

	@PostMapping(BusConstant.SAVE_BUS)
	public ResponseEntity<Bus> saveBus(@RequestBody Bus Bus) {
		return busProxy.saveBus(Bus);
	}

	@PostMapping(BusConstant.BOOK_BUS_BY_BUS_ID)
	public ResponseEntity<BusBookingDetails> bookBusByBusId(@PathVariable long id,
			@RequestBody BusTravellerBusSeats btbs, @PathVariable String username, @PathVariable String pickUpPoint,
			@PathVariable String dropPoint) {
		return busProxy.bookBusByBusId(id, btbs, username, pickUpPoint, dropPoint);
	}

	@GetMapping(BusConstant.GET_BUS_BOOKING_DETAILS_BY_BUS_BOOKING_ID)
	public ResponseEntity<BusBookingDetails> getBusBookingDetailsByBusBookingId(@PathVariable long id) {
		return busProxy.getBusBookingDetailsByBusBookingId(id);
	}

	@GetMapping(BusConstant.GET_BUS_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<BusBookingDetails>> getBusBookingDetailsByUsername(@PathVariable String username) {
		return busProxy.getBusBookingDetailsByUsername(username);
	}

	@PutMapping(BusConstant.UPDATE_BUS_BY_BUS_ID)
	public ResponseEntity<Bus> updateBusByBusId(@PathVariable long busId, @RequestBody Bus Bus) {
		return busProxy.updateBusByBusId(busId, Bus);
	}

	@DeleteMapping(BusConstant.DELETE_BUS_BY_BUS_ID)
	public ResponseEntity<String> deleteBusByBusId(@PathVariable long busId) {
		return busProxy.deleteBusByBusId(busId);
	}

	@PutMapping(BusConstant.CANCEL_PAYMENT_BY_BUS_BOOKING_ID)
	public ResponseEntity<BusBookingDetails> cancelPaymentByBusBookingId(@PathVariable long busId) {
		return busProxy.cancelPaymentByBusBookingId(busId);
	}

	@GetMapping(BusConstant.GET_ALL_CITY_NAMES)
	public ResponseEntity<List<List<String>>> getAllCityNames() {
		return busProxy.getAllCityNames();
	}

	@GetMapping(BusConstant.PAYMENT_STATUS_CHANGE_BY_BUS_BOOKING_ID)
	public ResponseEntity<BusBookingDetails> paymentStatusChangeByBusBookingId(@PathVariable long bookingid) {
		return busProxy.paymentStatusChangeByBusBookingId(bookingid);
	}

	@GetMapping(BusConstant.GET_ALL_AVAILABLE_BUS)
	public ResponseEntity<List<Bus>> getAllAvailableBuses(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure) {
		return busProxy.getAllAvailableBuses(from, to, departure);
	}
}
