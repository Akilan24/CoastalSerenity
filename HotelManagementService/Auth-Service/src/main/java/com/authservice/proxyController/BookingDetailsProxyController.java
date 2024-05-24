package com.authservice.proxyController;

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

import com.authservice.ProxyEntity.BookingDetails;
import com.authservice.ProxyEntity.HotelRooms;

@FeignClient(name = "BOOKINGDETAILS-SERVICE", url = "http://localhost:8084/BookingDetails")
public interface BookingDetailsProxyController {

	@GetMapping("/getall")
	public ResponseEntity<List<BookingDetails>> listBookingDetails();

	@GetMapping("/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}")
	public ResponseEntity<List<HotelRooms>> checkRoomAvailability(@PathVariable String city, @PathVariable String roomtype,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date ToDate);

	@GetMapping("/getbyid/{bookingid}")
	public ResponseEntity<BookingDetails> getBookingDetails(@PathVariable long bookingid);

	@PutMapping("/paymentstatuschangebybid/{bookingid}")
	public ResponseEntity<BookingDetails> paymentstatuschange(@PathVariable long bookingid);

	@PostMapping("/bookroom/{username}")
	public ResponseEntity<BookingDetails> bookroom(@PathVariable String username, @RequestBody BookingDetails bd);

	@DeleteMapping("/deletebyid/{bookingid}")
	public ResponseEntity<String> remove(@PathVariable long bookingid);
}
