package com.bookingdetailsservice.controller;

import java.util.Date;
import java.util.List;

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

import com.bookingdetailsservice.entity.BookingDetails;
import com.bookingdetailsservice.entity.HotelRooms;
import com.bookingdetailsservice.service.BookingDetailsService;

@RestController
@RequestMapping("/BookingDetails")
public class BookingDetailsController {

	@Autowired
	BookingDetailsService service;

	@GetMapping("/getall")
	public ResponseEntity<List<BookingDetails>> listBookingDetails() {
		return new ResponseEntity<>(service.showAllBookingDetails(), HttpStatus.OK);
	}

	@GetMapping("/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}")
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city, @PathVariable String roomtype,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate) {
		return new ResponseEntity<>(service.AvailableRoom(roomtype, city, fromDate, ToDate), HttpStatus.OK);
	}

	@GetMapping("/getbyid/{bookingid}")
	public ResponseEntity<BookingDetails> getBookingDetails(@PathVariable long booking_id) {
		return new ResponseEntity<>(service.showBookingDetailsbyId(booking_id), HttpStatus.OK);
	}

	@PutMapping("/paymentstatuschangebybid/{bookingid}")
	public ResponseEntity<BookingDetails> paymentstatuschange(@PathVariable long booking_id) {
		return new ResponseEntity<>(service.paymentstatuschange(booking_id), HttpStatus.OK);
	}

	@PostMapping("/bookroom/{name}")
	public ResponseEntity<BookingDetails> bookroom(@PathVariable String name, @RequestBody BookingDetails bd) {
		return new ResponseEntity<>(service.BookRoom(name, bd), HttpStatus.OK);
	}

	@DeleteMapping("/deletebyid/{bookingid}")
	public ResponseEntity<String> remove(@PathVariable long booking_id) {
		return new ResponseEntity<>(service.removeBookingDetails(booking_id), HttpStatus.OK);
	}
}
