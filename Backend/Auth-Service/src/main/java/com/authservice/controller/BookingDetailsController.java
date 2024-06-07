package com.authservice.controller;

import java.util.Date;
import java.util.List;

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

import com.authservice.ProxyEntity.BookingDetails;
import com.authservice.ProxyEntity.HotelGuest;
import com.authservice.ProxyEntity.HotelRooms;
import com.authservice.proxyController.BookingDetailsProxyController;

@RestController
@RequestMapping("/HMA/BookingDetails")
@CrossOrigin("http://localhost:5173")
public class BookingDetailsController {

	@Autowired
	BookingDetailsProxyController bookingDetailsProxy;

	@GetMapping("/getall")
	public ResponseEntity<List<BookingDetails>> listBookingDetails() {
		return bookingDetailsProxy.listBookingDetails();
	}

	@GetMapping("/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}")
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city, @PathVariable String roomtype,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate) {
		return bookingDetailsProxy.checkRoomAvailability(city, roomtype, fromDate, ToDate);
	}

	@GetMapping("/getbyid/{bookingid}")
	public ResponseEntity<BookingDetails> getBookingDetails(@PathVariable long bookingid) {
		return bookingDetailsProxy.getBookingDetails(bookingid);
	}
	
	@GetMapping("/getbyusername/{username}")
	public ResponseEntity<List<BookingDetails>> getBookingDetailsbyusername(@PathVariable String username) {
		return bookingDetailsProxy.getBookingDetailsbyusername(username);
	}

	@PutMapping("/paymentstatuschangebybid/{bookingid}")
	public ResponseEntity<BookingDetails> paymentstatuschange(@PathVariable long bookingid) {
		return bookingDetailsProxy.paymentstatuschange(bookingid);
	}

	@PostMapping("/bookroom/{username}")
	public ResponseEntity<BookingDetails> bookroom(@PathVariable String username, @RequestBody BookingDetails bd) {
		return bookingDetailsProxy.bookroom(username, bd);
	}

	@DeleteMapping("/deletebyid/{bookingid}")
	public ResponseEntity<String> remove(@PathVariable long bookingid) {
		return bookingDetailsProxy.remove(bookingid);
	}
	
	@PostMapping("/addguests/{bookingid}")
	public ResponseEntity<BookingDetails> addGuest(@PathVariable long bookingid,@RequestBody List<HotelGuest> guest) {
		return bookingDetailsProxy.addGuest(bookingid,guest);
	}
}
