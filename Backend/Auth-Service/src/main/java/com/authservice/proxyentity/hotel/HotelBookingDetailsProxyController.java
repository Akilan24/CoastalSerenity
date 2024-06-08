package com.authservice.proxyentity.hotel;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.proxyentity.hotel.HotelBookingDetails;
import com.authservice.proxyentity.hotel.HotelGuest;
import com.authservice.proxyentity.hotel.HotelRooms;

@FeignClient(name = "HOTEL-SERVICE", url = "http://localhost:8082/Hotel/HotelBookingDetails")
public interface HotelBookingDetailsProxyController {

	@GetMapping("/getall")
	public ResponseEntity<List<HotelBookingDetails>> listBookingDetails();

	@GetMapping("/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}")
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city, @PathVariable String roomtype,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate) ;

	@GetMapping("/getbyid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> getBookingDetails(@PathVariable long bookingid);

	@GetMapping("/getbyusername/{username}")
	public ResponseEntity<List<HotelBookingDetails>> getBookingDetailsbyusername(@PathVariable String username);
	
	@PutMapping("/paymentstatuschangebybid/{bookingid}")
	public ResponseEntity<HotelBookingDetails> paymentstatuschange(@PathVariable long bookingid) ;

	@PostMapping("/bookroom/{username}")
	public ResponseEntity<HotelBookingDetails> bookroom(@PathVariable String username, @RequestBody HotelBookingDetails bd) ;

	@DeleteMapping("/deletebyid/{bookingid}")
	public ResponseEntity<String> remove(@PathVariable long bookingid) ;
	
	@PostMapping("/addguests/{bookingid}")
	public ResponseEntity<HotelBookingDetails> addGuest(@PathVariable long bookingid,@RequestBody List<HotelGuest> guest);
}
