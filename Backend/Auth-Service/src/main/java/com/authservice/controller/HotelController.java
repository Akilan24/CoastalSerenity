package com.authservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

import com.authservice.proxyentity.hotel.Hotel;
import com.authservice.proxyentity.hotel.HotelBookingDetails;
import com.authservice.proxyentity.hotel.HotelGuest;
import com.authservice.proxyentity.hotel.HotelProxyController;
import com.authservice.proxyentity.hotel.HotelRooms;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/CS/Hotel")
@CrossOrigin("http://localhost:5173")
public class HotelController {

	@Autowired
	HotelProxyController hotelProxyController;

	@GetMapping("/getallhotel")
	public ResponseEntity<List<Hotel>> getHotels() {
		return hotelProxyController.getHotels();
	}

	@GetMapping("/getallhotelcitynames")
	public ResponseEntity<Set<String>> getHotelCityNames() {
		return hotelProxyController.getHotelCityNames();
	}

	@GetMapping("/getallhotelnamesbycity/{city}")
	public ResponseEntity<List<String>> getHotelNamesByCity(@PathVariable String city) {
		return hotelProxyController.getHotelNamesByCity(city);
	}

	@GetMapping("/gethotelbyid/{id}")
	public ResponseEntity<Hotel> gethotelbyid(@PathVariable Long id) {
		return hotelProxyController.gethotelbyid(id);
	}

	@GetMapping("/gethotelbyhotelname/{hotelname}")
	public ResponseEntity<Hotel> gethotelbyhotelname(@PathVariable String hotelname) {
		return hotelProxyController.gethotelbyhotelname(hotelname);
	}

	@GetMapping("/gethotelbycityname/{cityname}")
	public ResponseEntity<List<Hotel>> gethotelbycityname(@PathVariable String cityname) {
		return hotelProxyController.gethotelbycityname(cityname);
	}

	@PostMapping("/addhotel")
	public ResponseEntity<Hotel> addhotel(@RequestBody @Valid Hotel htl) throws Exception {
		return hotelProxyController.addhotel(htl);
	}

	@PutMapping("/updatehotel")
	public ResponseEntity<Hotel> updatehotel(@RequestBody @Valid Hotel ht) {
		return hotelProxyController.updatehotel(ht);

	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deletehotel(@PathVariable Long id) {
		return hotelProxyController.deletehotel(id);
	}

	@GetMapping("/HotelBookingDetails/getall")
	public ResponseEntity<List<HotelBookingDetails>> listBookingDetails() {
		return hotelProxyController.listBookingDetails();
	}

	@GetMapping("/HotelBookingDetails/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}")
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city,
			@PathVariable String roomtype, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate) {
		return hotelProxyController.checkRoomAvailability(city, roomtype, fromDate, ToDate);
	}

	@GetMapping("/HotelBookingDetails/getbyid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> getBookingDetails(@PathVariable long bookingid) {
		return hotelProxyController.getBookingDetails(bookingid);
	}

	@GetMapping("/HotelBookingDetails/getbyusername/{username}/{hotelName}")
	public ResponseEntity<HotelBookingDetails> getBookingDetailsbyusernameandhotelname(@PathVariable String username,
			@PathVariable String hotelName) {
		return hotelProxyController.getBookingDetailsbyusernameandhotelname(username, hotelName);
	}

	@GetMapping("/HotelBookingDetails/getbyusername/{username}")
	public ResponseEntity<List<HotelBookingDetails>> getBookingDetailsbyusername(@PathVariable String username) {
		return hotelProxyController.getBookingDetailsbyusername(username);
	}

	@PutMapping("/HotelBookingDetails/paymentstatuschangebybid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> paymentstatuschange(@PathVariable long bookingid) {
		return hotelProxyController.paymentstatuschange(bookingid);
	}

	@PostMapping("/HotelBookingDetails/bookroom/{username}")
	public ResponseEntity<HotelBookingDetails> bookroom(@PathVariable String username,
			@RequestBody HotelBookingDetails bd) {
		return hotelProxyController.bookroom(username, bd);
	}

	@DeleteMapping("/HotelBookingDetails/deletebyid/{bookingid}")
	public ResponseEntity<String> remove(@PathVariable long bookingid) {
		return hotelProxyController.remove(bookingid);
	}

	@PostMapping("/HotelBookingDetails/addguests/{bookingid}")
	public ResponseEntity<HotelBookingDetails> addGuest(@PathVariable long bookingid,
			@RequestBody List<HotelGuest> guest) {
		return hotelProxyController.addGuest(bookingid, guest);
	}
}
