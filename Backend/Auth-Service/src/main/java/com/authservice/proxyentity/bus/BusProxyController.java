package com.authservice.proxyentity.bus;

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

import com.authservice.constant.BusConstant;

@FeignClient(name = BusConstant.SERVICE, url = BusConstant.URL)
public interface BusProxyController {

	@GetMapping(BusConstant.GET_ALL_BUS)
	public ResponseEntity<List<Bus>> getAllBus();

	@GetMapping(BusConstant.GET_BUS_BY_BUS_ID)
	public ResponseEntity<Optional<Bus>> getBusByBusId(@PathVariable long busId);

	@PutMapping(BusConstant.ADD_SEATS_BY_BUS_ID)
	public ResponseEntity<Bus> addSeatsByBusId(@PathVariable long busId);

	@PostMapping(BusConstant.SAVE_BUS)
	public ResponseEntity<Bus> saveBus(@RequestBody Bus Bus);

	@PostMapping(BusConstant.BOOK_BUS_BY_BUS_ID)
	public ResponseEntity<BusBookingDetails> bookBusByBusId(@PathVariable long id,
			@RequestBody BusTravellerBusSeats btbs, @PathVariable String username, @PathVariable String pickUpPoint,
			@PathVariable String dropPoint);

	@GetMapping(BusConstant.GET_BUS_BOOKING_DETAILS_BY_BUS_BOOKING_ID)
	public ResponseEntity<BusBookingDetails> getBusBookingDetailsByBusBookingId(@PathVariable long id);

	@GetMapping(BusConstant.GET_BUS_BOOKING_DETAILS_BY_USERNAME)
	public ResponseEntity<List<BusBookingDetails>> getBusBookingDetailsByUsername(@PathVariable String username);

	@PutMapping(BusConstant.UPDATE_BUS_BY_BUS_ID)
	public ResponseEntity<Bus> updateBusByBusId(@PathVariable long busId, @RequestBody Bus Bus);

	@DeleteMapping(BusConstant.DELETE_BUS_BY_BUS_ID)
	public ResponseEntity<String> deleteBusByBusId(@PathVariable long busId);

	@PutMapping(BusConstant.CANCEL_PAYMENT_BY_BUS_BOOKING_ID)
	public ResponseEntity<BusBookingDetails> cancelPaymentByBusBookingId(@PathVariable long busId);

	@GetMapping(BusConstant.GET_ALL_CITY_NAMES)
	public ResponseEntity<List<List<String>>> getAllCityNames();

	@GetMapping(BusConstant.PAYMENT_STATUS_CHANGE_BY_BUS_BOOKING_ID)
	public ResponseEntity<BusBookingDetails> paymentStatusChangeByBusBookingId(@PathVariable long bookingid);

	@GetMapping(BusConstant.GET_ALL_AVAILABLE_BUS)
	public ResponseEntity<List<Bus>> getAllAvailableBuses(@PathVariable String from, @PathVariable String to,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure);
}
