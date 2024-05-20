package com.authservice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.ProxyEntity.BookingDetails;
import com.authservice.ProxyEntity.Room;
import com.authservice.proxyController.BookingDetailsProxyController;

@RestController
@RequestMapping("/HMA/BookingDetails")
public class BookingDetailsController {

	@Autowired
	BookingDetailsProxyController bookingDetailsProxy;

	@GetMapping("/getall")
	public ResponseEntity<List<BookingDetails>> listBookingDetails() {
		return bookingDetailsProxy.listBookingDetails();
	}

	@GetMapping("/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}")
	public ResponseEntity<List<Room>> checkRoomAvailability(@PathVariable String city, @PathVariable String roomtype,
			@PathVariable Date fromDate, @PathVariable Date ToDate) {
		return bookingDetailsProxy.checkRoomAvailability(city, roomtype, fromDate, ToDate);
	}

	@GetMapping("/getbyid/{bookingid}")
	public ResponseEntity<BookingDetails> getBookingDetails(@PathVariable Integer booking_id) {
		return bookingDetailsProxy.getBookingDetails(booking_id);
	}

	@PostMapping("/paymentstatuschangebybid/{bookingid}")
	public ResponseEntity<BookingDetails> paymentstatuschange(@PathVariable Integer booking_id) {
		return bookingDetailsProxy.paymentstatuschange(booking_id);
	}

	@PutMapping("/bookroom/{userid}")
	public ResponseEntity<BookingDetails> bookroom(@PathVariable String user_id, @RequestBody BookingDetails bd) {
		return bookingDetailsProxy.bookroom(user_id, bd);
	}

	@DeleteMapping("/deletebyid/{bookingid}")
	public ResponseEntity<String> remove(@PathVariable Integer booking_id) {
		return bookingDetailsProxy.remove(booking_id);
	}
}
