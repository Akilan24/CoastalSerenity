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

import com.authservice.proxyentity.hotel.HotelBookingDetails;
import com.authservice.proxyentity.hotel.HotelBookingDetailsProxyController;
import com.authservice.proxyentity.hotel.HotelGuest;
import com.authservice.proxyentity.hotel.HotelRooms;

@RestController
@RequestMapping("/CS/Hotel/HotelBookingDetails")
@CrossOrigin("http://localhost:5173")
public class HotelBookingDetailsController {

	@Autowired
	HotelBookingDetailsProxyController hotelBookingDetailsProxyController;

	@GetMapping("/getall")
	public ResponseEntity<List<HotelBookingDetails>> listBookingDetails() {
		return hotelBookingDetailsProxyController.listBookingDetails();
	}

	@GetMapping("/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}")
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city, @PathVariable String roomtype,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate) {
		return hotelBookingDetailsProxyController.checkRoomAvailability(city, roomtype, fromDate, ToDate);
	}

	@GetMapping("/getbyid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> getBookingDetails(@PathVariable long bookingid) {
		return hotelBookingDetailsProxyController.getBookingDetails(bookingid);
	}

	@GetMapping("/getbyusername/{username}")
	public ResponseEntity<List<HotelBookingDetails>> getBookingDetailsbyusername(@PathVariable String username) {
		return hotelBookingDetailsProxyController.getBookingDetailsbyusername(username);
	}
	
	@PutMapping("/paymentstatuschangebybid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> paymentstatuschange(@PathVariable long bookingid) {
		return hotelBookingDetailsProxyController.paymentstatuschange(bookingid);
	}

	@PostMapping("/bookroom/{username}")
	public ResponseEntity<HotelBookingDetails> bookroom(@PathVariable String username, @RequestBody HotelBookingDetails bd) {
		return hotelBookingDetailsProxyController.bookroom(username, bd);
	}

	@DeleteMapping("/deletebyid/{bookingid}")
	public ResponseEntity<String> remove(@PathVariable long bookingid) {
		return hotelBookingDetailsProxyController.remove(bookingid);
	}
	
	@PostMapping("/addguests/{bookingid}")
	public ResponseEntity<HotelBookingDetails> addGuest(@PathVariable long bookingid,@RequestBody List<HotelGuest> guest) {
		return hotelBookingDetailsProxyController.addGuest(bookingid, guest);
	}
}
