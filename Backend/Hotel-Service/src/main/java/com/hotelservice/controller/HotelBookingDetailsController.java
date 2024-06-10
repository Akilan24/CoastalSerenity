package com.hotelservice.controller;

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

import com.hotelservice.entity.HotelBookingDetails;
import com.hotelservice.entity.HotelGuest;
import com.hotelservice.entity.HotelRooms;
import com.hotelservice.service.HotelBookingDetailsService;

@RestController
@RequestMapping("/Hotel/HotelBookingDetails")
public class HotelBookingDetailsController {

	@Autowired
	HotelBookingDetailsService service;

	@GetMapping("/getall")
	public ResponseEntity<List<HotelBookingDetails>> listBookingDetails() {
		return new ResponseEntity<>(service.showAllBookingDetails(), HttpStatus.OK);
	}

	@GetMapping("/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}")
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city,
			@PathVariable String roomtype, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate) {
		return new ResponseEntity<>(service.AvailableRoom(roomtype, city, fromDate, ToDate), HttpStatus.OK);
	}

	@GetMapping("/getbyid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> getBookingDetails(@PathVariable long bookingid) {
		return new ResponseEntity<>(service.showBookingDetailsbyId(bookingid), HttpStatus.OK);
	}

	@GetMapping("/getbyusernameandhotelname/{username}/{hotelName}")
	public ResponseEntity<HotelBookingDetails> getBookingDetailsbyusernameandhotelname(@PathVariable String username,@PathVariable String hotelName) {
		return new ResponseEntity<>(service.showBookingDetailsbyUserNameAndHotelName(username,hotelName), HttpStatus.OK);
	}

	@GetMapping("/getbyusernameandhotelname/{username}")
	public ResponseEntity<List<HotelBookingDetails>> getBookingDetailsbyusername(@PathVariable String username) {
		return new ResponseEntity<>(service.showBookingDetailsbyUserName(username), HttpStatus.OK);
	}
	
	@PutMapping("/paymentstatuschangebybid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> paymentstatuschange(@PathVariable long bookingid) {
		return new ResponseEntity<>(service.paymentstatuschange(bookingid), HttpStatus.OK);
	}

	@PostMapping("/bookroom/{username}")
	public ResponseEntity<HotelBookingDetails> bookroom(@PathVariable String username,
			@RequestBody HotelBookingDetails bd) {
		return new ResponseEntity<>(service.BookRoom(username, bd), HttpStatus.OK);
	}

	@DeleteMapping("/deletebyid/{bookingid}")
	public ResponseEntity<String> remove(@PathVariable long bookingid) {
		return new ResponseEntity<>(service.removeBookingDetails(bookingid), HttpStatus.OK);
	}

	@PostMapping("/addguests/{bookingid}")
	public ResponseEntity<HotelBookingDetails> addGuest(@PathVariable long bookingid,
			@RequestBody List<HotelGuest> guest) {
		return new ResponseEntity<>(service.addGuest(bookingid, guest), HttpStatus.OK);
	}
	
	@PutMapping("/resetstatus/{id}")
	public ResponseEntity<HotelBookingDetails> resetstatus(@PathVariable long id) {
		return new ResponseEntity<>(service.resetStatus(id), HttpStatus.OK);
	}
}
